package lesson22.dao;

import lesson22.config.DatabaseConf;
import lesson22.model.Book;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{

    private final Connection connection;

    public  BookDAOImpl() {
        this.connection = new DatabaseConf().getDBConnection();
    }

    public BookDAOImpl(Connection sqlConnection) {
        this.connection = sqlConnection;
    }

    public int getSize(@NotNull Tables table) {
        String count = String.format("SELECT COUNT(*) FROM %s", table.value);
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        int size = 0;

        try {
            resultSet.next();
            size = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return size;
    }

    public List<Book> find(String find) {
        List<Book> bookList = new ArrayList<>();
        if (find != null && !find.equals("")) {
            String formatFind = "'%" + find + "%'";
            String select = String.format("SELECT * FROM %s WHERE CONCAT(%s, %s, %s) ILIKE %s",
                    Tables.BOOKS.value,
                    BookTableColumnNames.AUTHOR.value,
                    BookTableColumnNames.TITLE.value,
                    BookTableColumnNames.PUBLISHER.value,
                    formatFind
            );

            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(select);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            Book book;
            try {
                while (resultSet.next()) {
                    book = new Book(
                            resultSet.getInt(BookTableColumnNames.BOOK_ID.value),
                            resultSet.getString(BookTableColumnNames.AUTHOR.value),
                            resultSet.getString(BookTableColumnNames.TITLE.value),
                            resultSet.getString(BookTableColumnNames.PUBLISHER.value),
                            resultSet.getInt(BookTableColumnNames.YEAR_OF_PUBLISHING.value)
                    );
                    bookList.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }

    public boolean isExists(Book book){
        if (book != null) {
            String select = String.format("SELECT * FROM %s WHERE %s ILIKE ? AND %s ILIKE ? AND %s ILIKE ? AND %s = ?",
                    Tables.BOOKS.value,
                    BookTableColumnNames.AUTHOR.value,
                    BookTableColumnNames.TITLE.value,
                    BookTableColumnNames.PUBLISHER.value,
                    BookTableColumnNames.YEAR_OF_PUBLISHING.value
                    );

            int paramAuthor = 1;
            int paramTitle = 2;
            int paramPublisher = 3;
            int paramYear = 4;

            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(select);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                preparedStatement.setString(paramAuthor, book.getAuthor());
                preparedStatement.setString(paramTitle, book.getTitle());
                preparedStatement.setString(paramPublisher, book.getPublisher());
                preparedStatement.setInt(paramYear, book.getYearOfPublishing());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            Book book1;
            try {
                resultSet.next();
                book1 = new Book(
                        resultSet.getInt(BookTableColumnNames.BOOK_ID.value),
                        resultSet.getString(BookTableColumnNames.AUTHOR.value),
                        resultSet.getString(BookTableColumnNames.TITLE.value),
                        resultSet.getString(BookTableColumnNames.PUBLISHER.value),
                        resultSet.getInt(BookTableColumnNames.YEAR_OF_PUBLISHING.value)
                );
            } catch (SQLException e) {
                return false;
            }

            return book1.equals(book);
        }
        return false;
    }


    @Override
    public Book setBook(Book book) {
        if (book != null) {
            if (!isExists(book)) {
                String insert = String.format("INSERT INTO public.books(%s, %s, %s, %s) ",
                        BookTableColumnNames.AUTHOR.value,
                        BookTableColumnNames.TITLE.value,
                        BookTableColumnNames.PUBLISHER.value,
                        BookTableColumnNames.YEAR_OF_PUBLISHING.value
                ) +
                        String.format("VALUES ('%s', '%s', '%s', %s)",
                                book.getAuthor(),
                                book.getTitle(),
                                book.getPublisher(),
                                book.getYearOfPublishing()
                        );
                PreparedStatement preparedStatement = null;

                try {
                    preparedStatement = connection.prepareStatement(insert);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                finally {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }

                String bookID = String.format("(SELECT MAX(%s) FROM %s)", BookTableColumnNames.BOOK_ID.value, Tables.BOOKS.value);

                return getBook(bookID);
            }
        }
        return null;
    }

    private Book getBook(String bookID) {
        String select = String.format("SELECT * FROM %S WHERE %s =%s", Tables.BOOKS.value, BookTableColumnNames.BOOK_ID.value, bookID);
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        Book book = null;

        try {
            resultSet.next();
            book = new Book(
                    resultSet.getInt(BookTableColumnNames.BOOK_ID.value),
                    resultSet.getString(BookTableColumnNames.AUTHOR.value),
                    resultSet.getString(BookTableColumnNames.TITLE.value),
                    resultSet.getString(BookTableColumnNames.PUBLISHER.value),
                    resultSet.getInt(BookTableColumnNames.YEAR_OF_PUBLISHING.value)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public Book getBook(int bookID) {
        if (bookID > 0) {
            String strBookID = Integer.toString(bookID);
            return getBook(strBookID);
        }
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        if (book != null) {
            String update = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                    Tables.BOOKS.value,
                    BookTableColumnNames.AUTHOR.value,
                    BookTableColumnNames.TITLE.value,
                    BookTableColumnNames.PUBLISHER.value,
                    BookTableColumnNames.YEAR_OF_PUBLISHING.value,
                    BookTableColumnNames.BOOK_ID.value
            );

            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(update);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int paramOfAuthor = 1;
            int paramOfTitle = 2;
            int paramOfPublisher = 3;
            int paramOfYearOfPublishing = 4;
            int paramOfBookID = 5;

            try {
                preparedStatement.setString(paramOfAuthor, book.getAuthor());
                preparedStatement.setString(paramOfTitle, book.getTitle());
                preparedStatement.setString(paramOfPublisher, book.getPublisher());
                preparedStatement.setInt(paramOfYearOfPublishing, book.getYearOfPublishing());
                preparedStatement.setInt(paramOfBookID, book.getBookID());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            return getBook(book.getBookID());
        }
        return null;
    }

    @Override
    public boolean removeBook(int bookID) {
        if (bookID > 0) {
            String delete = String.format("DELETE FROM %s WHERE %s=?", Tables.BOOKS.value, BookTableColumnNames.BOOK_ID.value);

            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(delete);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int paramOfBookID = 1;

            try {
                preparedStatement.setInt(paramOfBookID, bookID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int result = 0;
            try {
                result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            return result == 1;
        }
        return false;
    }
}
