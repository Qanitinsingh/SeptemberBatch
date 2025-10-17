package page.methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page.objects.DemoObjects;
import steps.Hooks;

import java.time.Duration;


public class DemoMethods {

    private static final Logger logger = LoggerFactory.getLogger(DemoMethods.class);

    public static void enterUrl(String url, WebDriver driver) {
        try {
            logger.info("Navigating to URL: {}", url);
            driver.get(url);
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[src='/web/images/ohrm_branding.png?v=1721393199309']")));
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
        }
    }

    public static void performLogin(WebDriver driver, String username, String password) {
        try {
            logger.info("Attempting login with username: {}", username);
            driver.findElement(DemoObjects.USER_NAME).sendKeys(username);
            driver.findElement(DemoObjects.PASSWORD).sendKeys(password);
            logger.info("Login submitted for username: {}", username);
        } catch (Exception e) {
            logger.error("Login failed for username: {}", username, e);
            throw e;
        }
    }



    public static void enterUserName(WebDriver driver, String username) {
        try {
            logger.info("Attempting login with username: {}", username);
            driver.findElement(DemoObjects.USER_NAME).sendKeys(username);
            logger.info("Login submitted for username: {}", username);
        } catch (Exception e) {
            logger.error("Login failed for username: {}", username, e);
            throw e;
        }
    }

    public static void clickLogin(WebDriver driver) {
        try {
            logger.info("Attempting login  {}");
            driver.findElement(DemoObjects.LOGIN_BUTTON).click();
            logger.info("Login Clicked  {}");
        } catch (Exception e) {
            logger.error("Login failed for username: {}", e);

        }
    }


    public static void enterPassword(WebDriver driver,  String password) {
        try {
            logger.info("Attempting login with password: {}", password);

            driver.findElement(DemoObjects.PASSWORD).sendKeys(password);
            logger.info("Login submitted for password: {}", password);
        } catch (Exception e) {
            logger.error("Login failed for password: {}", password, e);
            throw e;
        }
    }

    public static void verifyDashboardPage(WebDriver driver) {
        try {
            driver.findElement(By.className("orangehrm-login-button")).click();
            Thread.sleep(2000);
            logger.info("Verifying dashboard page visibility.");
            boolean isDashboardVisible = !driver.findElement(By.cssSelector(".oxd-topbar-header-title h6")).isDisplayed();
            if (!isDashboardVisible) {
                logger.error("Dashboard page is not displayed.");
                throw new AssertionError("Dashboard page is not displayed.");
            }
            logger.info("Dashboard page is displayed.");
        } catch (Exception e) {
            logger.error("Error verifying dashboard page.", e);

        }
    }

}
