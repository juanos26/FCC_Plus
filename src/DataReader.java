import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    private File fileToRead;
    private int encryptionKey;
    private String studnetID;
    private String password;

    public DataReader(File file) {
        //this.fileToRead = file;
    }

    public boolean credentialsGrabber() throws IOException {
        // Use a try-with-resources block to automatically close the BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            // Read the first three lines and check if they are not null
            String encryptionKey = reader.readLine();
            String user = reader.readLine();
            String password = reader.readLine();

            // Check if any of the lines are null
            if (encryptionKey != null && user != null && password != null) {


                return true; // File has at least 3 lines
            } else {
                return false; // File has less than 3 lines
            }
        } catch (IOException e) {
            System.out.println("File is corrupted or missing data");
            throw new RuntimeException(e);
        }
    }

    public String decrypter(String stringToDecrypt, int key) {
        StringBuilder decryptedString = new StringBuilder();

        for (int i = 0; i < stringToDecrypt.length(); i++) {
            char encryptedChar = stringToDecrypt.charAt(i);
            int encryptedCodePoint = (int) encryptedChar;

            int decryptedCodePoint = encryptedCodePoint - 1 - key;
            if (decryptedCodePoint < 32) {
                decryptedCodePoint = 126 - (32 - decryptedCodePoint) % 95;
            }
            char decryptedChar = (char) decryptedCodePoint;

            decryptedString.append(decryptedChar);
        }

        return decryptedString.toString();
    }

    public String encrypter(String stringToEncrypt, int key) {
        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < stringToEncrypt.length(); i++) {
            char originalChar = stringToEncrypt.charAt(i);
            int originalCodePoint = (int) originalChar;

            int encryptedCodePoint = originalCodePoint + 1 + key;
            if (encryptedCodePoint > 126) {
                encryptedCodePoint = 32 + (encryptedCodePoint - 126) % 95;
            }
            char encryptedChar = (char) encryptedCodePoint;

            encryptedString.append(encryptedChar);
        }

        return encryptedString.toString();
    }

}


