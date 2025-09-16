package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ClickLeftMenu {


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://testing.qaautomationlabs.com/index.php");
        driver.manage().window().maximize();


        // Will click on left menu one by one
        List<WebElement> leftMenu = driver.findElements(By.cssSelector("ul.nav-sidebar li.nav-item"));
        for (int i = 0; i < leftMenu.size(); i++) {
            leftMenu = driver.findElements(By.cssSelector("ul.nav-sidebar li.nav-item")); // re-locate
            WebElement menuItem = leftMenu.get(i);
            String menuText = menuItem.getText();
            System.out.println("Clicking on menu item: " + menuText);
            menuItem.click();
        }

        driver.quit();
    }
}
