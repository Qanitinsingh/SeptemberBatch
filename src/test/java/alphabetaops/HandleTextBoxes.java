package alphabetaops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleTextBoxes {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://alphabetaops.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

// Wait explicitly until the element is clickable
        WebElement novice = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mb-2:first-child")));

        novice.click();

        WebElement textBox = wait.until(ExpectedConditions.
                elementToBeClickable(By.cssSelector(".mb-2:first-child ul.list-group li.list-group-item:first-child")));

        textBox.click();

        WebElement basicTextBox = wait.until(ExpectedConditions.
                elementToBeClickable(By.cssSelector(".mb-2:first-child ul.list-group li.list-group-item:first-child ul li")));
        basicTextBox.click();
// Switch to iframe

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.content-iframe")));

        driver.switchTo().frame(iframe);
        WebElement fullName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("plain-text")));
        fullName.sendKeys("Nitin");
        driver.quit();


    }

}
