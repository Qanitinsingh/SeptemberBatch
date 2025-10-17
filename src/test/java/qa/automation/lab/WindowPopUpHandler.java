package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class WindowPopUpHandler {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testing.qaautomationlabs.com/window-popup-modal.php");

        // ✅ Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 0)
    public void successModal() {
        driver.findElement(By.cssSelector(".btn-success")).click();

        WebElement modalHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modal-success h4"))
        );
        System.out.println("Modal Title for success modal: " + modalHeader.getText());

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-success .btn"))
        );
        closeBtn.click();
    }

    @Test(priority = 1)
    public void infoModal() {
        driver.findElement(By.cssSelector(".btn-info")).click();

        WebElement modalHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modal-info h4"))
        );
        System.out.println("Modal Title for info modal: " + modalHeader.getText());

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-info .btn"))
        );
        closeBtn.click();
    }

    @Test(priority = 2)
    public void primaryModal() {
        driver.findElement(By.cssSelector(".btn-primary")).click();

        WebElement modalHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modal-primary h4"))
        );
        System.out.println("Modal Title for primary modal: " + modalHeader.getText());

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-primary .btn"))
        );
        closeBtn.click();
    }

    @Test(priority = 3)
    public void errorModal() {
        driver.findElement(By.cssSelector(".btn-danger")).click();

        WebElement modalHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modal-danger h4"))
        );
        System.out.println("Modal Title for error modal: " + modalHeader.getText());

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-danger .btn"))
        );
        closeBtn.click();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Quitting the driver");
        driver.quit();
    }
}
