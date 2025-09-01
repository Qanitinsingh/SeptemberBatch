package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LearnCheckBoxs extends BaseClass {

    @BeforeMethod
    public void loadCheckBoxPage() {
        try {
            driver.get("https://testing.qaautomationlabs.com/checkbox.php");
            System.out.println("Navigated to checkbox page.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception in loadCheckBoxPage: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void singleCheckBox() {
        try {
            WebElement singleCheck = driver.findElement(By.id("myCheckbox"));
            singleCheck.click();
            System.out.println("Single checkbox clicked successfully.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception in singleCheckBox: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void disabledCheckBox() {
        try {
            List<WebElement> checkboxes = driver.findElements(By.cssSelector(".card:nth-of-type(3) input"));
            for (int i = 0; i < checkboxes.size(); i++) {
                boolean isEnabled = checkboxes.get(i).isEnabled();
                if (i < 2) {
                    assert isEnabled : "Checkbox " + (i + 1) + " should be enabled";
                    checkboxes.get(i).click();
                } else {
                    assert !isEnabled : "Checkbox " + (i + 1) + " should be disabled";
                }
                System.out.println("Checkbox " + (i + 1) + " enabled status: " + isEnabled);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Exception in disabledCheckBox: " + e.getMessage());
        }
    }
    @Test(priority = 3)
    public void multipleCheckBox() {
        try {
                List<WebElement> checkBox = driver.findElements(By.cssSelector(".card:last-child .myCheckbox"));
                for (WebElement box : checkBox) {
                    box.click();
                    Thread.sleep(1000);
                }
        } catch (Exception e) {
            System.out.println("Exception in multipleCheckBox: " + e.getMessage());
        }

    }
}