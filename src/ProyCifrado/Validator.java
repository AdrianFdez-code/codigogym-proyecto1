package ProyCifrado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public boolean validKey(int key, char[] alphabet) {
        // Key check
        return true;
    }

    //public static boolean fileExists(Path filePath) {
    public static void fileExists(Path filePath) throws IOException {
        // Check if the file exists
        if (!Files.exists(filePath)) {
            throw new IOException("No existe archivo");
            //return false;
        }
    }
}