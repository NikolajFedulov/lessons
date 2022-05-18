package lesson16;

import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface implements Commands {

    Manager manager;
    Scanner scanner;

    public UserInterface(Manager manager, Scanner scanner){
        this.manager = manager;
        this.scanner = scanner;
    }

    private void contextMenu(String availableCommands){
        System.out.println("Available commands: " + availableCommands);
        System.out.print("Please enter your command >");
    }

    public void start(){
        String availableCommands = String.format("%s, %s, %s, %s, %s",
            Commands.ADD, Commands.DELETE, Commands.FIND, Commands.VIEW, Commands.EXIT);
        contextMenu(availableCommands);
    }

    public void add(){
        String confirmation;
        String availableCommands = String.format("%s, %s", Commands.YES, Commands.NO);
        System.out.print("Please enter the author the book >");
        String author = scanner.nextLine();
        System.out.print("Please enter the title the book >");
        String title = scanner.nextLine();
        do {
            System.out.printf("Add \"%s. %s\" to the library?\n", author, title);
            contextMenu(availableCommands);
            confirmation = scanner.nextLine();
        }while (!(confirmation.equalsIgnoreCase(YES) || confirmation.equalsIgnoreCase(NO)));
        if (confirmation.equalsIgnoreCase(YES)) {
            if (manager.add(author, title)){
                System.out.println("The book has been successfully added to the library");
            }
            else {
                System.out.println("This book has already been added to the library");
            }
        }
    }

    public void del(){
        String confirmation;
        String availableCommands = String.format("%s, %s", Commands.YES, Commands.NO);;
        System.out.println("Please enter the ID of the book to be removed from the library >");
        Integer id = Integer.parseInt(scanner.nextLine());
        String bookToDelete = manager.findID(id);
        if (bookToDelete!=null){
            do {
                System.out.printf("Do you really want to delete [%s]?\n", bookToDelete);
                contextMenu(availableCommands);
                confirmation = scanner.nextLine();
            }while(!(confirmation.equalsIgnoreCase(YES) || confirmation.equalsIgnoreCase(NO)));
            if (confirmation.equalsIgnoreCase(YES)){
                if (manager.del(id)) {
                    System.out.println("Book successfully deleted");
                }
                else {
                    System.out.println("Sorry, but the book cannot be deleted.");
                }
            }
        }
        else{
            System.out.println("Sorry, but there is no book with the specified ID in the library");
        }
    }

    public void view(){
        if (manager.view()){
            Stream<String> stream = manager.booksToDisplay.stream();
            stream.forEach(System.out::println);
        }
        else {
            System.out.println("Sorry, but the library is empty");
        }
    }

    public void find(){
        System.out.print("Please enter text to search >");
        String find = scanner.nextLine();
        if (manager.find(find)){
            Stream<String> stream = manager.booksToDisplay.stream();
            stream.forEach(System.out::println);
        }
        else {
            System.out.println("Sorry, nothing found");
        }
    }

//    public String view(){
//        String availableCommands = MSG_COMMANDS_VIEW_P_N;
//        String command = null;
//        int lineAtPage = 5;
//        int page = 0;
//        if (manager.view()){
//            do {
//                Stream<String> stream = manager.booksToDisplay.stream();
//                int finalPage = page;
//                stream.filter(s -> manager.booksToDisplay.indexOf(s) >= finalPage *lineAtPage && manager.booksToDisplay.indexOf(s) < (finalPage +1)*lineAtPage).
//                        forEach(System.out::println);
//                boolean hasNext = (page+1)*lineAtPage < manager.booksToDisplay.size();
//                boolean hasPrevious = page > 0;
//                if (hasNext && !hasPrevious) availableCommands = MSG_COMMANDS_VIEW_N;
//                if (hasNext && hasPrevious) availableCommands = MSG_COMMANDS_VIEW_P_N;
//                if (!hasNext && hasPrevious) availableCommands = MSG_COMMANDS_VIEW_P;
//                if (!hasNext && !hasPrevious) availableCommands = MSG_COMMANDS_VIEW;
//                contextMenu(availableCommands);
//                command = scanner.nextLine();
//                if (hasNext && command.equalsIgnoreCase(NEXT)) page++;
//                if (hasPrevious && command.equalsIgnoreCase(PREVIOUS)) page--;
//                if (MSG_COMMANDS_VIEW.contains(command.toLowerCase())) break;
//            }while (command.equalsIgnoreCase(NEXT) || command.equalsIgnoreCase(PREVIOUS));
//        }
//        else {
//            System.out.println(MSG_VIEW_FAILED);
//        }
//        return command;
//    }
}
