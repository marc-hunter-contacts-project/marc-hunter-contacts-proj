package Methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddDataFile {

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
}
