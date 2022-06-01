package lesson18;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryManagerTest {

    private final String[][] books = {
            {"Antoine de Saint-Exupery", "The Little Prince"},  // Little: index = 0
            {"Daniel Defoe", "Robinson Crusoe"},
            {"Mikhail Bulgakov", "Master and Margarita"},
            {"Lewis Carroll", "Alice's Adventures in Wonderland and Through the Looking Glass"},
            {"Mary Shelley", "Frankenstein"},
            {"Louisa May Alcott", "Little Women"}               // Little: index = 5
    };
    LibraryManager libraryManager = new LibraryManager();

    @Before
    public void setUp() {
        for (String[] book: books){
            Book book1 = new Book();
            book1.setAuthor(book[0]);
            book1.setTitle(book[1]);
            libraryManager.add(book1);
        }
    }

    @Test
    public void add() {
        Book book = new Book();
        book.setAuthor("L. M. Montgomery");
        book.setTitle("Anne of Green Gables");
        Assert.assertTrue(libraryManager.add(book));
        Assert.assertFalse(libraryManager.add(book));
    }

    @Test
    public void del() {
        String expected = books[0][0] + " " + books[0][1];
        Book book = libraryManager.del(0);
        String actual = book.getAuthor() + " " + book.getTitle();
        Assert.assertEquals(expected, actual);
        Assert.assertNull(libraryManager.del(10000));
    }

    @Test
    public void find() {
        Map<Integer, Book> map = libraryManager.find("Little");
        String expected =
                books[0][0] + books[0][1] +
                books[5][0] + books[5][1];
        String actual =
                map.get(0).getAuthor() + map.get(0).getTitle() +
                map.get(5).getAuthor() + map.get(5).getTitle();
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(libraryManager.find("bvygughu").isEmpty());
    }

    @Test
    public void view() {
        Map<Integer, Book> map = libraryManager.view();
        String expected = Arrays.stream(books)
                .flatMap(Arrays::stream)
                .reduce("", ((s, s2) -> s + s2));
        String actual = map.values().stream()
                .map(card -> card.getAuthor() + card.getTitle())
                .collect(Collectors.joining());
        Assert.assertEquals(expected, actual);
    }
}