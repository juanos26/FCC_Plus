package src;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import src.Model.CurrentDevice;
import src.Model.Student;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        /*
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.youtube.com/");
         */


        CurrentDevice tester = new CurrentDevice();


    }
}
