    package src.Controllers;

    import javafx.application.Application;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.input.MouseEvent;
    import javafx.stage.Stage;
    import java.io.File;

    public class TermsAgreementController extends Application {

        private boolean isTermsAccepted = false;

        @Override
        public void start(Stage stage) throws Exception {
            File fxmlFile = new File("src/Controllers/TermsAgreement.fxml");
            Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());
            stage.setTitle("FCC Plus");
            stage.setScene(new Scene(root));
            stage.show();
        }



        public static void launchApplication(String[] args) {
            launch(args);
        }

        @FXML
        public void handleMouseEnter(MouseEvent event) {
            Node node = (Node) event.getSource();
            node.setOpacity(0.8);
        }

        @FXML
        public void handleMouseExit(MouseEvent event) {
            Node node = (Node) event.getSource();
            node.setOpacity(1.0);
        }

        @FXML
        public void acceptTermsWindow(MouseEvent event) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            isTermsAccepted=true;
            stage.close();
        }
        @FXML
        public void declineTermsWindow(MouseEvent event) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }

    }
