package lesson16;

import org.junit.Assert;
import org.junit.Test;


public class ManagerTest {

    @Test
    public void add() {
        Manager manager = new Manager();
        String author = "Antoine de Saint-Exupery";
        String title = "The Little Prince";
        Assert.assertTrue(manager.add(author,title));
        Assert.assertFalse(manager.add(author,title));
    }

    @Test
    public void del() {
        Manager manager = new Manager();
        String author = "Mikhail Bulgakov";
        String title = "The Master and Margarita";
        manager.add(author, title);     //at least one book will be in the library with id #0
        Assert.assertTrue(manager.del(0));
        Assert.assertFalse(manager.del(0));
    }

    @Test
    public void find() {
        Manager manager = new Manager();
        String author = "Lewis Carroll";
        String title = "Alice's Adventures in Wonderland";
        manager.add(author, title);
        Assert.assertTrue(manager.find("adventures"));
        Assert.assertTrue(manager.find("CARROLL"));
        Assert.assertFalse(manager.find("something"));
    }

    @Test
    public void findID() {
        Manager manager = new Manager();
        String author = "Herman Melville";
        String title = "Moby-Dick";
        manager.add(author, title);     //at least one book will be in the library with id #0
        Assert.assertNotNull(manager.findID(0));
        Assert.assertNull(manager.findID(100));
    }

    @Test
    public void view() {
        Manager manager = new Manager();
        Assert.assertFalse(manager.view());
        String author = "Sir Arthur Conan Doyle";
        String title = "The Adventures of Sherlock Holmes";
        manager.add(author, title);
        Assert.assertTrue(manager.view());
    }
}