package lesson18;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testEquals() {
        String author = "Antoine de Saint-Exupery";
        String title = "Little Prince";
        Book book1 = new Book();
        book1.setAuthor(author);
        book1.setTitle(title);
        Book book2 = new Book();
        book2.setAuthor(author);
        book2.setTitle(title);
        assertTrue(book1.equals(book2));
    }
}