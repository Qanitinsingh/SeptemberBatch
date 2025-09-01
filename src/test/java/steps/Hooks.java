package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            logger.info("ChromeDriver initialized, window maximized, and cookies cleared.");
        } catch (Exception e) {
            logger.error("Exception during WebDriver setup: {}", e.getMessage(), e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            if (scenario.isFailed()) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                logger.error("Scenario '{}' failed. Screenshot captured.", scenario.getName());
            } else {
                logger.info("Scenario '{}' passed.", scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Exception during screenshot capture or scenario teardown: {}", e.getMessage(), e);
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("ChromeDriver quit successfully.");
            }
        }
    }
}
