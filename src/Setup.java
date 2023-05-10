import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Setup extends Application {

    boolean previousFileExists = false;
    boolean acceptedTerms = false;
    boolean alwaysUseSelectedFiles = false;


    public static void GiveTerms() {
        launch(new String[]{});
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the text for the terms of service
        Text termsText = new Text("Terms of Service:\n\n" +
                "By using this software, you agree to the following terms:\n" +
                "1. You will use this software only for legal purposes.\n" +
                "2. You will not attempt to reverse-engineer or modify the software.\n" +
                "3. You will not share your login credentials with anyone else.\n" +
                "4. This program or its creator will not be held liable for any damages.\n" +
                "5. You understand that this program is extremely experimental.\n" +
                "6. You may only use this program if terms are accepted.\n");

        // Create the accept button and set its action
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(event -> {
            // TODO: Perform actions for when the user accepts the terms of service
            System.out.println("User has accepted the terms of service");
            acceptedTerms = true;
            checkForFiles();
            primaryStage.close();
        });

        // Create the decline button and set its action
        Button declineButton = new Button("Decline");
        declineButton.setOnAction(event -> {
            // TODO: Perform actions for when the user declines the terms of service
            System.out.println("User declined the terms of service");
            acceptedTerms = false;
            primaryStage.close();
        });

        // Add the nodes to a VBox
        VBox root = new VBox(10, termsText, acceptButton, declineButton);
        root.setAlignment(Pos.CENTER); // Set the alignment to center

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terms of Service");
        primaryStage.show();
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
                } else {
                    System.out.println("Cancel button clicked.");
                }
            });
        }
    }
}
