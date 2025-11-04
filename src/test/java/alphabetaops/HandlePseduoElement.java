package alphabetaops;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import qa.automation.lab.BaseClass;

public class HandlePseduoElement {

    public static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://play1.automationcamp.ir/advanced.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementValue = js.executeScript("return window.getComputedStyle(document.querySelector('.star-rating'),'::after').getPropertyValue('content')").toString();
        System.out.println(elementValue);

        String modifiedValue = elementValue.replace("\"", "");
        System.out.println(modifiedValue);
        driver.findElement(By.id("txt_rating")).sendKeys(modifiedValue);
        driver.findElement(By.id("check_rating")).click();
        boolean isChecked = driver.findElement(By.id("validate_rating")).isDisplayed();
        if (isChecked) {
            System.out.println("Pseudo element value is validated successfully.");
        } else {
            System.out.println("Pseudo element value validation failed.");
        }

        driver.quit();

    }
}
