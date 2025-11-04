package alphabetaops;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import qa.automation.lab.BaseClass;

import java.time.Duration;


public class ShadowText extends BaseClass {

    @Test
    public void handleShadowTextBox() {
        driver.get("https://alphabetaops.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By menuLocator = By.cssSelector(".mb-2:nth-of-type(3)");
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuLocator));
        menu.click();

        By firstItemLocator = By.cssSelector(".mb-2:nth-of-type(3) ul li.list-group-item:first-child");
        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(firstItemLocator));
        firstItem.click();

        By linkLocator = By.cssSelector(".mb-2:nth-of-type(3) ul.list-group li.list-group-item:first-child a");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkLocator));
        link.click();

        // Step 2: Wait for iframe to be available
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.content-iframe")));
        // --- Step 1: Locate the custom shadow host element ---
        WebElement shadowHost = driver.findElement(By.cssSelector("my-shadow-component"));

        // --- Step 2: Use JavaScript to access shadow root and input inside it ---
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowInput = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('#shadowInput')", shadowHost);

        // --- Step 3: Interact with the element ---
        shadowInput.sendKeys("Text inside Shadow DOM!");

        // Optional: Verify the value entered
        String value = (String) js.executeScript("return arguments[0].value", shadowInput);
        System.out.println("Entered text: " + value);


    }
}

