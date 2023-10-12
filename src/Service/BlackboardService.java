package src.Service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;



public class BlackboardService {
    private WebDriver driver;

  public BlackboardService(String whatDriver){
      driver = WhatDriver(whatDriver);
      driver.get("https://frederick.blackboard.com/webapps/login/");
      ClickID("agree_button", driver);
      ClickClass("SSObutton", driver);
      Wait(3000);
      TextWrite(driver.findElement(By.id("i0116")),"input user here");
      ClickID("idSIButton9", driver);
      Wait(2000);
      TextWrite(driver.findElement(By.id("i0118")),"input password here");
      ClickID("idSIButton9", driver);
      Wait(2000);
      ClickID("idBtn_Back", driver);



  }

    private void GoBackToDashboard(){
        driver.get("https://frederick.blackboard.com/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_1_1");
    }

    private void GoToGrades(){
        driver.get("https://frederick.blackboard.com/webapps/bb-social-learning-BB609f3985beb5e/execute/mybb?cmd=display&toolId=MyGradesOnMyBb_____MyGradesTool&extraParams=override_stream=mygrades");
    }
    private WebDriver WhatDriver(String browser){
      if (browser.equals("firefox")){
          System.out.println("Starting Firefox Driver");
          WebDriverManager.firefoxdriver().setup();
          return new FirefoxDriver();
      } else if (browser.equals("safari")) {
          System.out.println("Starting Safari Driver");
          WebDriverManager.safaridriver().setup();
          return new SafariDriver();
      } else if (browser.equals("chrome")) {
          System.out.println("Starting Chrome Driver");
          WebDriverManager.chromedriver();
          return new ChromeDriver();
      }else {
          WebDriverManager.chromedriver().setup();
          return new ChromeDriver();
      }

    }
    private void ClickID (String whatToClick, WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(whatToClick)));
        element.click();
    }

    private void ClickClass(String whatToClick, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(whatToClick)));
        element.click();
    }

    private void TextWrite(WebElement whereToWrite, String whatToWrite){
      whereToWrite.sendKeys(whatToWrite);
    }

    private void Wait(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
