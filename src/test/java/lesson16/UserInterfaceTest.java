package lesson16;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class UserInterfaceTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void start() {
    }

    @Test
    public void add() {
    }

    @Test
    public void del() {
    }

    @Test
    public void view() {
    }

    @Test
    public void find() {
        String text = "text";
        String expected = "Please enter text to search >Sorry, nothing found\r\n";
        String expected2 = "Please enter text to search >Sorry, nothing found\r\n" +
                "Please enter text to search >id:0, author: Ernest Hemingway, title: The Old Man and the Sea\r\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(manager, scanner);

        userInterface.find();
        Assert.assertEquals(expected, outputStream.toString());

        String author = "Ernest Hemingway";
        String title = "The Old Man and the Sea";
        String find = "Old Man";
        inputStream = new ByteArrayInputStream(find.getBytes());
        System.setIn(inputStream);

        Manager manager2 = new Manager();
        Scanner scanner2 = new Scanner(System.in);
        UserInterface userInterface2 = new UserInterface(manager2, scanner2);
        manager2.add(author, title);

        userInterface2.find();
        Assert.assertEquals(expected2, outputStream.toString());

        System.setIn(System.in);
        System.setOut(null);
    }
}