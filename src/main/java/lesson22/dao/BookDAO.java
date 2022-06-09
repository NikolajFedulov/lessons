package lesson22.dao;

import lesson22.model.Book;

public interface BookDAO {

    Book setBook(Book book);
    Book getBook(int bookID);
    Book updateBook(Book book);
    boolean removeBook(int bookID);

}
