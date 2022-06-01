package lesson18;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class LibrarySerializerTest {

    private final File path = new File("src/main/resources/library_save_test.json");
    private final ObjectMapper jsonMapper = new JsonMapper();
    private final LibrarySerializer librarySerializer = new LibrarySerializer(path);
    private final LibraryManager libraryManager = new LibraryManager();
    private final Book book = new Book();
    private LibraryManager libraryManagerTest = null;

    @Before
    public void setUp() {
        book.setAuthor("Lewis Carroll");
        book.setTitle("Alice's Adventures in Wonderland");
        libraryManager.add(book);
    }

    @Test
    public void serialize() {
        librarySerializer.serialize(libraryManager);
        try {
            libraryManagerTest = jsonMapper.readValue(path, LibraryManager.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, Book> map = libraryManagerTest.view();
        Book bookTest = map.get(0);
        assertEquals(book, bookTest);
    }

    @Test
    public void deserialize() {
        try {
            jsonMapper.writeValue(path, libraryManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        libraryManagerTest = librarySerializer.deserialize(libraryManagerTest);
        Map<Integer, Book> map = libraryManagerTest.view();
        Book bookTest = map.get(0);
        assertEquals(book, bookTest);
    }
}