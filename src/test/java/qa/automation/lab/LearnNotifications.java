package qa.automation.lab;

import java.util.List;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class LearnNotifications extends BaseClass{

    List<String> expectedClasses = new ArrayList<>();

    @BeforeClass
    public void setUpExpectedClasses() {
        // Store expected alert classes instead of text
        expectedClasses.add("alert-success");
        expectedClasses.add("alert-info");
        expectedClasses.add("alert-primary");
        expectedClasses.add("alert-danger");
    }

    @Test(priority = 1)
    public void verifySuccessMessage() throws InterruptedException {
        driver.get("https://testing.qaautomationlabs.com/notifications.php");
        Thread.sleep(1000);

        WebElement btn = driver.findElement(By.className("btn-success"));
        btn.click();
        Thread.sleep(500);

        WebElement alert = driver.findElement(By.id("toastsContainerTopRight"));
        String actualClass = alert.getAttribute("class"); // e.g. "alert alert-success"
        System.out.println("Success Alert Class: " + actualClass);

        Assert.assertTrue(actualClass.contains(expectedClasses.get(0)));
    }

    @Test(priority = 2)
    public void verifyInfoMessage() throws InterruptedException {
        WebElement btn = driver.findElement(By.xpath("//button[text()='Info Message']"));
        btn.click();
        Thread.sleep(500);

        WebElement alert = driver.findElement(By.className("btn-info"));
        String actualClass = alert.getAttribute("class");
        System.out.println("Info Alert Class: " + actualClass);

        Assert.assertTrue(actualClass.contains(expectedClasses.get(1)));
    }

    @Test(priority = 3)
    public void verifyPrimaryMessage() throws InterruptedException {
        WebElement btn = driver.findElement(By.xpath("//button[text()='Primary Message']"));
        btn.click();
        Thread.sleep(500);

        WebElement alert = driver.findElement(By.className("btn-primary"));
        String actualClass = alert.getAttribute("class");
        System.out.println("Primary Alert Class: " + actualClass);

        Assert.assertTrue(actualClass.contains(expectedClasses.get(2)));
    }

    @Test(priority = 4)
    public void verifyErrorMessage() throws InterruptedException {
        WebElement btn = driver.findElement(By.className("btn-danger"));
        btn.click();
        Thread.sleep(500);

        WebElement alert = driver.findElement(By.cssSelector(".alert"));
        String actualClass = alert.getAttribute("class");
        System.out.println("Error Alert Class: " + actualClass);

        Assert.assertTrue(actualClass.contains(expectedClasses.get(3)));
    }
}
