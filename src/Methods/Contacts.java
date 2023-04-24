package Methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contacts {
    public static Scanner scanner = new Scanner(System.in);

    public static List<Path> addFile() {
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
        List<Path> pathList = new ArrayList<>();
        pathList.add(dataDirectory);
        pathList.add(dataFile);
        return pathList;
    }

    public static void menu() {
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
        menuChoice(userChoice);
    }

    public static void menuChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> viewAll();//View contacts;
            case 2 -> addContact();
            case 3 -> contactByName();
            case 4 -> deleteContactbyName();
            case 5 -> System.out.println("BYE");
            default -> {
                System.out.println("Invalid Choice");
                menu();
            }
        }
    }

    public static void viewAll(){
        Path dataDirectory = addFile().get(0);
        Path dataFile = addFile().get(1);
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
//            System.out.println(contactsListFromFile);
            for(String line:contactsListFromFile) {
                System.out.println(line);;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void addContact (){
        Path dataDirectory = addFile().get(0);
        Path dataFile = addFile().get(1);

        String contactInfo = "Add a contact/ firstLast,#########";
        System.out.println(contactInfo);
        String contactName = scanner.nextLine();
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            Files.write(dataFile, contactsListFromFile);
            List<String> newContact = Arrays.asList(contactName);
            Files.write(
                    dataFile,
                    newContact,
                    StandardOpenOption.APPEND
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
        String[] newGuy = contactName.split(",");
        String name = newGuy[0];
        String number = newGuy[1];
        System.out.printf("You added %s with a phone number of %s", name, number);
    }

    public static void contactByName(){
        Path dataDirectory = addFile().get(0);
        Path dataFile = addFile().get(1);

        System.out.println("Search contact by name");
        String userInput = scanner.nextLine();
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            for(String line:contactsListFromFile) {
                String[] contactInfo = line.split(",");
                if(contactInfo[0].equalsIgnoreCase(userInput)){
                    System.out.println(line);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void deleteContactbyName(){
        Path dataDirectory = addFile().get(0);
        Path dataFile = addFile().get(1);

        System.out.println("Search contact by name");
        String userInput = scanner.nextLine();
        List<String> newList = new ArrayList<>();
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            for(String line:contactsListFromFile) {
                String[] contactInfo = line.split(",");
                if(contactInfo[0].equalsIgnoreCase(userInput)){
                    continue;
                } else {
                    newList.add(line);
                }
            }
            Files.write(
                    dataFile,
                    newList
            );
        } catch(IOException e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        deleteContactbyName();

// Create a list from the array using Arrays.asList()

// Access and print the first contact in the list

    }
}