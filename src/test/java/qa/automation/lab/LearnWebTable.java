package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LearnWebTable{

    public static WebDriver driver;
    public static void main(String[] args) {
        driver= new ChromeDriver();
        driver.get("https://testing.qaautomationlabs.com/web-table.php");
        driver.manage().window().maximize();

        List<WebElement> rows = driver.findElements(org.openqa.selenium.By.cssSelector("tbody tr"));
String data= rows.get(0).getText();
        System.out.println("Data in first row: "+data);

        String[] parts = data.split(" ");

        // Combine middle parts (index 1 and 2)
        String name = parts[1] + " " + parts[2];
        System.out.println(name); // Output: John Doe

driver.findElement(By.id("searchInput")).sendKeys(name);





        driver.quit();

    }
}
