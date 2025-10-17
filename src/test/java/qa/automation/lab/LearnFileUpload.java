package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.nio.file.Paths;

public class LearnFileUpload extends BaseClass {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://testing.qaautomationlabs.com/file-upload.php");

        driver.manage().window().maximize();

        // Reveal input if it's hidden
        WebElement fileInput = driver.findElement(By.cssSelector("#drop-area input[type='file']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);

// Now upload
        File file = Paths.get("src/test/resources/TestData/File.png").toFile();
        fileInput.sendKeys(file.getAbsolutePath());
Thread.sleep(1000);
        System.out.println("File has been uploded");
driver.quit();

    }
}
