package qa.automation.lab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ClickLeftMenu {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            driver.get("https://testing.qaautomationlabs.com/index.php");

            // Find all left menu items
            List<WebElement> menuItems = driver.findElements(By.cssSelector(".nav.flex-column li a"));

            for (int i = 0; i < menuItems.size(); i++) {
                // Re-find menu items after each navigation to avoid stale element
                menuItems = driver.findElements(By.cssSelector(".nav.flex-column a"));
                WebElement menuItem = menuItems.get(i);
                String title = driver.getTitle();
System.out.println(title);
                menuItem.click();


            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}