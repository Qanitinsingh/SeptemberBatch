package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestOrangeCRM {

    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
             driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[src='/web/images/ohrm_branding.png?v=1721393199309']")));

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.className("orangehrm-login-button")).click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".oxd-topbar-header-title h6")));


        String dashboardTitle = driver.findElement(By.cssSelector(".oxd-topbar-header-title h6")).getText();
        if (dashboardTitle.equals("Dashboard")) {
            System.out.println("Dashboard page is  displayed.");

        } else {
            System.out.println("Dashboard page is not displayed.");
        }
if (driver != null)
        driver.quit();
System.out.println("Driver closed.");

    }
}
