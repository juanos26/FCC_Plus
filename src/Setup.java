import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


public class Setup extends Application {

    private boolean acceptedTerms = false;
    private boolean alwaysUseSelectedFiles = false;
    private File dataFile = new File("data.txt");


    public static void GiveTerms() {
        launch(new String[]{});
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the choice dialog and set its properties
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", "Accept", "Decline");
        dialog.setTitle("Terms of Service");
        dialog.setHeaderText("By using this software, you agree to the following terms:");
        dialog.setContentText("1. You will use this software only for legal purposes.\n" +
                "2. You will not attempt to reverse-engineer or modify the software.\n" +
                "3. You will not share your login credentials with anyone else.\n" +
                "4. This program or its creator will not be held liable for any damages.\n" +
                "5. You understand that this program is extremely experimental.\n" +
                "6. You may only use this program if terms are accepted.");

        // Show the choice dialog and wait for the user's response
        Optional<String> result = dialog.showAndWait();

        // Handle the user's response
        result.ifPresent(choice -> {
            if (choice.equals("Accept")) {
                // Perform actions for when the user accepts the terms of service
                System.out.println("User has accepted the terms of service");
                acceptedTerms = true;
                checkForFiles();
            } else if (choice.equals("Decline")) {
                // Perform actions for when the user declines the terms of service
                System.out.println("User declined the terms of service");
                acceptedTerms = false;
            }
        });
    }

    public void checkForFiles() {

        File file = new File("data.txt");
        if (file.exists() && file.isFile()) {
            System.out.println("Found text file: " + file.getName());
        } else {
            System.out.println("Text file not found.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Not Found");
            alert.setHeaderText(null);
            alert.setContentText("The data file could not be found.");

            ButtonType createButton = new ButtonType("Create New Data File");
            ButtonType importButton = new ButtonType("Import Existing Data File");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(createButton, importButton, cancelButton);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == createButton) {
                    System.out.println("Creating new data.text file in root program directory");

                    try {
                        FileWriter writer = new FileWriter("data.txt");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (buttonType == importButton) {
                    System.out.println("Import existing data file.");

                    // Create a new file chooser
                    FileChooser fileChooser = new FileChooser();

                    // Set the title of the file chooser dialog
                    fileChooser.setTitle("Choose File");

                    // Set the initial directory of the file chooser to the user's home directory
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

                    // Show the file chooser dialog and wait for the user to choose a file
                    File selectedFile = fileChooser.showOpenDialog(null);

                    // If the user selected a file, check that it is named "data.txt" and copy it to the src folder of the project
                    if (selectedFile != null) {
                        // Get the name of the selected file
                        String selectedFileName = selectedFile.getName();

                        // If the file is named "data.txt", copy it to the src folder of the project
                        if (selectedFileName.equals("data.txt")) {
                            try {
                                // Get the path to the src folder of the project
                                String srcFolderPath = "/";
                                String projectPath = System.getProperty("user.dir");
                                String fullSrcFolderPath = projectPath + "/" + srcFolderPath;

                                // Create a new file with the name "data.txt" in the src folder of the project
                                File copiedFile = new File(fullSrcFolderPath + "data.txt");

                                // Copy the contents of the selected file to the copied file
                                Files.copy(selectedFile.toPath(), copiedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                // Print a message to the console indicating that the file was successfully copied
                                System.out.println("File successfully copied to " + copiedFile.getPath());
                            } catch (IOException e) {
                                // If an error occurred while copying the file, print an error message to the console
                                System.err.println("Error copying file: " + e.getMessage());
                            }
                        } else {
                            // If the file is not named "data.txt", display an error message to the user
                            Alert alertForFile = new Alert(Alert.AlertType.ERROR);
                            alertForFile.setTitle("File Error");
                            alertForFile.setHeaderText("File not compatible");
                            alertForFile.setContentText("The selected file must be named 'data.txt'");
                            alertForFile.showAndWait();
                        }
                    }

                } else {
                    System.out.println("Cancel button clicked.");
                }
            });
        }
    }
}