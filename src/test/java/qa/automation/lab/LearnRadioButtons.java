 package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LearnRadioButtons extends BaseClass {

    @BeforeMethod
    public void loadRadioButtonPage() {
        try {
            driver.get("https://testing.qaautomationlabs.com/radio-button.php");
            System.out.println("Navigated to radio button page.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception in loadRadioButtonPage: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void selectEachGenderAndShow() {
        try {
            // Test Male selection
            driver.findElement(By.cssSelector("input[type='radio'][name='gender'][value='Male']")).click();
            List<WebElement> buttons = driver.findElements(By.cssSelector(".btn"));
            buttons.get(0).click();
            List<WebElement> result = driver.findElements(By.className("text-danger"));
            String maleText = result.get(0).getText();
            System.out.println("Male Result: " + maleText);
            Assert.assertTrue(maleText.contains("Male"), "Result should contain 'Male'");

            // Test Female selection
            driver.findElement(By.xpath("//input[@type='radio' and @name='gender' and @value='Female']")).click();
            buttons.get(0).click();
            String femaleText = result.get(0).getText();
            System.out.println("Female Result: " + femaleText);
            Assert.assertTrue(femaleText.contains("Female"), "Result should contain 'Female'");
        } catch (Exception e) {
            System.out.println("Exception in selectEachGenderAndShow: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void checkRadioEnabledStatus() {
        try {
            WebElement radioButtonone=driver.findElement(By.xpath("//input[@type='radio' and @value='Radio Button 1']"));
            radioButtonone.click();

            WebElement radioButtontwo=driver.findElement(By.xpath("//input[@type='radio' and @value='Radio Button 2']"));
            radioButtontwo.click();
            WebElement disableRadioButton=driver.findElement(By.xpath("//input[@type='radio' and @value='Disabled Radio Button']"));
            boolean isEnabled=disableRadioButton.isEnabled();
            if(isEnabled){
                disableRadioButton.click();
            }
            System.out.println("Disabled Radio Button is disabled cant click: "+isEnabled);

        } catch (Exception e) {
            System.out.println("Exception in checkRadioEnabledStatus: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void selectGenderAndAgeAndShowValues() {
        try {
            WebElement femaleRadio = driver.findElement(By.xpath("//input[@name='gender1' and @value='Female']"));
            femaleRadio.click();
            WebElement ageRadio = driver.findElement(By.xpath("//input[@name='age1' and @value='18-35']"));
            ageRadio.click();
            driver.findElement(By.xpath("//button[text()='Show Selected Values']")).click();
            Thread.sleep(1000);
            WebElement result = driver.findElement(By.id("result3"));
            String text = result.getText();
            System.out.println("Selected values: " + text);
            Assert.assertTrue(text.contains("Female") && text.contains("18-35"), "Result should show selected gender and age group");
        } catch (Exception e) {
            System.out.println("Exception in selectGenderAndAgeAndShowValues: " + e.getMessage());
        }
    }
}