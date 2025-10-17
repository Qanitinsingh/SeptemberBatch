package qa.automation.lab;

import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LearnJavaScriptAlerts extends BaseClass{

    public static void main(String[] args) throws InterruptedException {

       WebDriver driver= new ChromeDriver();
       driver.get("https://testing.qaautomationlabs.com/javaScript-alert.php");
       driver.manage().window().maximize();

       // click and accept alert
        List<WebElement> buttons= driver.findElements(By.cssSelector("div.card-tools .row .col-4 .btn"));
        buttons.get(0).click();
System.out.println("First Alert clicked");
        driver.switchTo().alert().accept();
        holdon();

        buttons.get(1).click();
        System.out.println("Show and confirm Alert clicked");
        driver.switchTo().alert().dismiss();
        holdon();

        buttons.get(2).click();
        System.out.println("Show prompt Alert clicked");
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        holdon();

       driver.quit();


    }

    private static void holdon() throws InterruptedException
    {
        Thread.sleep(1000);
    }
}
