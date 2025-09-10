package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LearnShadowDom extends BaseClass {

    @Test(priority = 1)
    public void verifyShadowDomText() throws InterruptedException {
        driver.get("https://testing.qaautomationlabs.com/shadow-dom.php");
        Thread.sleep(2000);
        // Step 1: Locate shadow host
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow-host"));

        // Step 2: Get shadow root
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Step 3: Find element inside shadow root
        WebElement shadowElement = shadowRoot.findElement(By.className("box"));

        // Step 4: Extract text
        String shadowText = shadowElement.getText();
        System.out.println("Shadow DOM text: " + shadowText);

        // Step 5: Assertion
        Assert.assertEquals(shadowText, "Hello from Shadow DOM!");
    }
}
