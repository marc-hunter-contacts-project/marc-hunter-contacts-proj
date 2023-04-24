package Methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contacts {
    public static Scanner scanner = new Scanner(System.in);

    public static void addFile() {
        String directory = "data";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            try {
                Files.createDirectories(dataDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(dataFile)) {
            try {
                Files.createFile(dataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void menu(){
        System.out.print("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):
                                           
                """);
        String userChoiceStr = scanner.nextLine();
        int userChoice = Integer.parseInt(userChoiceStr);
    }

    public static void menuChoice(int userChoice) {
        addFile();
        switch (userChoice) {
            case 1 -> ;
            case 2 -> addContact();
            case 3 -> ;
            case 4 -> ;
            case 5 -> System.out.println("BYE");
            default -> {
                System.out.println("Invalid Choice");
                menu();
            }
        }
    }

    public static void addContact(){
        List<String> contacts = new ArrayList<>();
        try {
            Files.write(dataf)
        }
    }






    public static void main(String[] args) {
        menu();
    }
}
