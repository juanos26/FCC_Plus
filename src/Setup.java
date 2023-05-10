import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Setup extends Application {

    boolean previousFileExists = false;
    boolean acceptedTerms = false;


    public static void GiveTerms(){
        launch(new String[] {});
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
// Create the text for the terms of service
        Text termsText = new Text("Terms of Service:\n\n" +
                "By using this software, you agree to the following terms:\n" +
                "1. You will use this software only for legal purposes.\n" +
                "2. You will not attempt to reverse-engineer or modify the software.\n" +
                "3. You will not share your login credentials with anyone else.\n");

        // Create the accept and decline checkboxes
        CheckBox acceptBox = new CheckBox("I accept the terms of service");
        CheckBox declineBox = new CheckBox("I do not accept the terms of service");

        // Create the accept button and set its action
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(event -> {
            if (acceptBox.isSelected()) {
                // TODO: Perform actions for when the user accepts the terms of service
                primaryStage.close();
            }
        });

        // Create the decline button and set its action
        Button declineButton = new Button("Decline");
        declineButton.setOnAction(event -> {
            if (declineBox.isSelected()) {
                // TODO: Perform actions for when the user declines the terms of service
                primaryStage.close();
            }
        });

        // Add the nodes to a VBox
        VBox root = new VBox(10, termsText, acceptBox, declineBox, acceptButton, declineButton);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terms of Service");
        primaryStage.show();
    }
}
