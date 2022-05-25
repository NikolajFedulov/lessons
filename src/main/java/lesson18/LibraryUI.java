package lesson18;

import java.util.*;

public class LibraryUI {

    private final LibraryManager libraryManager;
    private final Scanner scanner;

    public LibraryUI(LibraryManager libraryManager, Scanner scanner){
        this.libraryManager = libraryManager;
        this.scanner = scanner;
    }

    private void contextMenu(Commands[] commands){
        System.out.println("Available commands: " + availableCommands(commands));
        System.out.print("Please enter your command >");
    }

    private String availableCommands(Commands[] commands){
        String separator = ", ";
        String cmdAtString = Arrays.stream(commands)
                .map(commands1 -> commands1.value)
                .reduce("", (s, commands1) -> s + commands1 + separator);
        return new StringBuilder(cmdAtString)
                .delete(cmdAtString.length()-separator.length(), cmdAtString.length())
                .toString();
    }

    public Commands command(){
        Commands[] commands = {
                Commands.ADD,
                Commands.DELETE,
                Commands.FIND,
                Commands.VIEW,
                Commands.EXIT
        };
        contextMenu(commands);
        String command = scanner.nextLine();
        for (Commands cmd: commands){
            if (cmd.value.equalsIgnoreCase(command)) return cmd;
        }
        return Commands.NO_CMD;
    }

    public void add(){
        Commands[] commands = {
                Commands.YES,
                Commands.NO
        };
        Book book = new Book();
        String confirmation;
        System.out.print("Please enter the author the book >");
        book.setAuthor(scanner.nextLine());
        System.out.print("Please enter the title the book >");
        book.setTitle(scanner.nextLine());
        do {
            System.out.printf("Add \"%s. %s\" to the library?\n", book.getAuthor(), book.getTitle());
            contextMenu(commands);
            confirmation = scanner.nextLine();
        }while (!(confirmation.equalsIgnoreCase(Commands.YES.value) || confirmation.equalsIgnoreCase(Commands.NO.value)));
        if (confirmation.equalsIgnoreCase(Commands.YES.value)) {
            if (libraryManager.add(book)){
                System.out.println("The book has been successfully added to the library");
            }
            else {
                System.out.println("This book has already been added to the library");
            }
        }
    }

    public void del(){
        Commands[] commands = {
                Commands.YES,
                Commands.NO
        };
        String confirmation;
        System.out.print("Please enter the ID of the book to be removed from the library >");
        Integer id = Integer.parseInt(scanner.nextLine());
        Book book = libraryManager.del(id);
        if (book != null){
            do {
                System.out.printf("Do you really want to delete [%s]?\n", id + ": " + book.getAuthor() + ". " + book.getTitle());
                contextMenu(commands);
                confirmation = scanner.nextLine();
            }while(!(confirmation.equalsIgnoreCase(Commands.YES.value) || confirmation.equalsIgnoreCase(Commands.NO.value)));
            if (confirmation.equalsIgnoreCase(Commands.YES.value)){
                System.out.println("Book successfully deleted");
            }
            else {
                libraryManager.add(book);
            }
        }
        else{
            System.out.println("Sorry, but there is no book with the specified ID in the library");
        }
    }

    public void view(){
        Map<Integer, Book> map = libraryManager.view();
        if (!map.isEmpty()){
            map.forEach((key, value) -> System.out.println(key + ": " + value.getAuthor() + ". " + value.getTitle()));
        }
        else {
            System.out.println("Sorry, but the library is empty");
        }
    }

    public void find(){
        System.out.print("Please enter text to search >");
        String find = scanner.nextLine();
        Map<Integer, Book> map = libraryManager.find(find);
        if (!map.isEmpty()){
            map.forEach((key, value) -> System.out.println(key + ": " + value.getAuthor() + ". " + value.getTitle()));
        }
        else {
            System.out.println("Sorry, nothing found");
        }
    }
}
