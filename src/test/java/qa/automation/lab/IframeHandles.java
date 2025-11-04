package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class IframeHandles extends BaseClass {

    @BeforeMethod
    public void loadIframePage() {
        try {
            driver.get("https://testing.qaautomationlabs.com/iframe.php");
            System.out.println("Navigated to radio button page.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception in loadRadioButtonPage: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void HandleIFrameOne() {
        try {
            driver.switchTo().frame(0);

            List<WebElement> buttons = driver.findElements(By.cssSelector(".p-4 .btn"));
            buttons.get(0).click();

            String successMessage = driver.findElement(By.cssSelector(".card-header #message")).getText();
            Assert.assertEquals(successMessage, "You havee clicked on iframe 1 button");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

            driver.switchTo().defaultContent();

        } catch (Exception e) {

        }

    }

    @Test(priority = 2)
    public void handleIFrameTwo() {
        try {
            driver.switchTo().frame(1);
            List<WebElement> buttons = driver.findElements(By.cssSelector(".p-4 .btn"));
            buttons.get(0).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
            String successMessage = driver.findElement(By.cssSelector(".card-header #message")).getText();
            Assert.assertEquals(successMessage, "You have clicked on iframe 2 button");

            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Exception in selectMultiSelectDropdown: " + e.getMessage());
        }
    }

}
