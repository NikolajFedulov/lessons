package lesson18;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Commands command;
        Scanner scanner = new Scanner(System.in);
        LibrarySerializer librarySerializer = new LibrarySerializer();
        LibraryManager libraryManager = new LibraryManager();
        libraryManager = librarySerializer.deserialize(libraryManager);
        LibraryUI libraryUI = new LibraryUI(libraryManager, scanner);
        do {
            command = libraryUI.command();
            switch (command){
                case ADD:       libraryUI.add();    break;
                case DELETE:    libraryUI.del();    break;
                case VIEW:      libraryUI.view();   break;
                case FIND:      libraryUI.find();   break;
                default: if (command!=Commands.EXIT) System.out.println("! No such command exists !");
            }
        }while (command!=Commands.EXIT);
        scanner.close();
        librarySerializer.serialize(libraryManager);
    }
}
