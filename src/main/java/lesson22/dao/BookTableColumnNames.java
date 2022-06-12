package lesson22.dao;

public enum BookTableColumnNames {

    BOOK_ID ("book_id"),
    AUTHOR("author"),
    TITLE("title"),
    PUBLISHER("publisher"),
    YEAR_OF_PUBLISHING("year_of_publishing");

    public final String value;

    BookTableColumnNames(String value) {
        this.value = value;
    }
}
