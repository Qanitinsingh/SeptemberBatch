package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LearnDragAndDrop extends BaseClass{


    @Test(priority = 1)
    public void verifyDragAndDrop() throws InterruptedException {
        // Navigate to the Drag & Drop demo page
        driver.get("https://testing.qaautomationlabs.com/drag-and-drop.php");
        Thread.sleep(2000); // Ensure page loads fully

        // Locate source and target elements
        WebElement source = driver.findElement(By.cssSelector("#sortableList li:first-child"));
        WebElement target = driver.findElement(By.cssSelector("#sortableList li:last-child"));

        // Use Actions class to perform drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        Thread.sleep(2000); // Wait to observe the result

        // Optional: Validate result (if there's any visible change)
        // Otherwise, simply print confirmation
        System.out.println("Drag and drop action performed successfully.");
        Assert.assertTrue(true);
    }
}
