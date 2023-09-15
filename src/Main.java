package src;

import org.openqa.selenium.WebElement;
import src.Model.CurrentDevice;
import src.Service.BlackboardService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        CurrentDevice tester = new CurrentDevice();
        BlackboardService testing = new BlackboardService("firefox");

    }
}
