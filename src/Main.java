package src;

import org.openqa.selenium.WebElement;
import src.Controllers.SignInController;
import src.Controllers.TermsAgreementController;
import src.Model.CurrentDevice;
import src.Service.BlackboardService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        CurrentDevice tester = new CurrentDevice();
        //BlackboardService testing = new BlackboardService("firefox");

        //TermsAgreementController.launchApplication(args);
        SignInController.launchApplication(args);
    }
}
