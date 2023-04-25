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

    // creates contacts.txt file
    public static Path addFile() {
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

        return dataFile;
    }

    // shows user the menu options and get their choice
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

    // runs correct method based on userInput
    public static void menuChoice(int userChoice) {
        Path dataFile = addFile();

        switch (userChoice) {
            case 1 -> viewAll(dataFile);//View contacts;
            case 2 -> addContact(dataFile);
            case 3 -> contactByName(dataFile);
            case 4 -> deleteContactByName(dataFile);
            case 5 -> System.out.println("BYE");
            default -> {
                System.out.println("Invalid Choice");
                menu();
            }
        }
    }

    // shows the entire contact list
    public static void viewAll(Path dataFile) {
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);

            for (String line : contactsListFromFile) {
                String[] formattedContacts = line.split(",");
                String name = formattedContacts[0];
                String number = formattedContacts[1];
                System.out.printf("%-15s | %-10s%n", name, number);

                if (formattedContacts[0].equals("Name") && formattedContacts[1].equals("Phone")){
                    System.out.println("--------------- | ----------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        menu();
    }

    // adds contact to contacts.txt
    public static void addContact(Path dataFile) {

        System.out.println("Add a contact: (Name,Phone Number)");
        String contactName = scanner.nextLine();
        String formattedInfo = formatNumber(contactName);

        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            Files.write(dataFile, contactsListFromFile);
            List<String> newContact = Arrays.asList(formattedInfo);
            Files.write(
                    dataFile,
                    newContact,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] newContact = formattedInfo.split(",");
        System.out.printf("You added \"%s\" with a phone number of \"%s\".%n%n", newContact[0], newContact[1]);

        menu();
    }

    // formats phone number with dashes
    public static String formatNumber(String contactInfo) {
        System.out.println(contactInfo);
        String[] contactInfoArr = contactInfo.split(",");
        contactInfoArr[1] = contactInfoArr[1].replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");   //123-456-7890
        String formattedInfo = contactInfoArr[0] + "," + contactInfoArr[1];
        return formattedInfo;
    }

    // shows contact info given a name
    public static void contactByName(Path dataFile) {
        System.out.println("Search contact by name:");
        String userInput = scanner.nextLine();
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            for (String line : contactsListFromFile) {
                String[] contactInfo = line.split(",");
                if (contactInfo[0].equalsIgnoreCase(userInput)) {
                    System.out.println(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu();
    }

    // deletes contact by inputted name
    public static void deleteContactByName(Path dataFile) {

        System.out.println("Enter a contact name to delete.");
        String userInput = scanner.nextLine();

        List<String> newList = new ArrayList<>();
        try {
            List<String> contactsListFromFile = Files.readAllLines(dataFile);
            for (String line : contactsListFromFile) {
                String[] contactInfo = line.split(",");
                if (contactInfo[0].equalsIgnoreCase(userInput)) {
                    continue;
                } else {
                    newList.add(line);
                }
            }
            Files.write(
                    dataFile,
                    newList
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu();
    }
}
