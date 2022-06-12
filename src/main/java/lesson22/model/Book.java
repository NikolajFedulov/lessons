package lesson22.model;

public class Book {

    private int bookID;
    private String author;
    private String title;
    private String publisher;
    private int yearOfPublishing;

    public Book(int bookID, String author, String title, String publisher, int yearOfPublishing) {
        this.bookID = bookID;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.yearOfPublishing = yearOfPublishing;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return author.equalsIgnoreCase(book.getAuthor()) &&
                title.equalsIgnoreCase(book.getTitle()) &&
                publisher.equalsIgnoreCase(book.getPublisher()) &&
                yearOfPublishing == book.getYearOfPublishing();
    }

    public int getBookID() {
        return bookID;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

//    public void setBookID(int bookID) {
//        this.bookID = bookID;
//    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
}
