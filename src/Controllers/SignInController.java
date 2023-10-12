package src.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class SignInController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        File fxmlFile = new File("src/Controllers/SignIn.fxml");
        Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());
        stage.setTitle("FCC Plus");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void launchApplication(String[] args) {
        launch(args);
    }
}
