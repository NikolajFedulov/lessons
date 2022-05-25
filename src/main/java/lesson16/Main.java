package lesson16;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            manager = (Manager) objectInputStream.readObject();
        }
        catch (IOException e) {
            System.out.println("File save.ser does not exist");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(manager, scanner);

        String command;
        do {
            userInterface.start();
            command = scanner.nextLine();
            switch (command) {
                case Commands.ADD: userInterface.add(); break;
                case Commands.DELETE: userInterface.del(); break;
                case Commands.VIEW: userInterface.view(); break;
                case Commands.FIND: userInterface.find(); break;
                default: if (!command.equalsIgnoreCase(Commands.EXIT)) System.out.println("This command does not exist.");
            }
        }while (!command.equalsIgnoreCase(Commands.EXIT));

        try {
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/save.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(manager);
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println("Failed to create file save.ser");
        }

    }
}
