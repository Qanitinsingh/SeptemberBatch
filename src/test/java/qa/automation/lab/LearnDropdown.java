package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LearnDropdown extends BaseClass{


    @BeforeMethod
    public void loadDropDownPage() {
        try {
            driver.get("https://testing.qaautomationlabs.com/dropdown.php");
            System.out.println("Navigated to radio button page.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception in loadRadioButtonPage: " + e.getMessage());
        }
    }

@Test (priority = 1)
    public  void selectBasicDropdown(){
        try {

         driver.findElement(By.id("fruitDropdown")).click();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
            Select select=new Select(driver.findElement(By.id("fruitDropdown")));
            select.selectByVisibleText("Apple");
            System.out.println("Apple is selected");
        } catch (Exception e) {

        }

    }
    @Test(priority = 2)
    public void selectMultiSelectDropdown() {
        try {
            Select select = new Select(driver.findElement(By.id("countryDropdown")));
            select.selectByVisibleText("India");
            select.selectByVisibleText("USA");
            select.selectByVisibleText("UK");
            System.out.println("India, USA, and UK are selected in the multi-select dropdown.");
        } catch (Exception e) {
            System.out.println("Exception in selectMultiSelectDropdown: " + e.getMessage());
        }
    }

}
