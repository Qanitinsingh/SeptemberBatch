package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LearnTestNG {

public static WebDriver driver;

@Test
    public static void launchBrowser() {
    driver= new ChromeDriver();

    driver.get("https://testing.qaautomationlabs.com/form.php");

    driver.quit();

    System.out.println("Execute me after quitting the browser" + driver);
    }




}
