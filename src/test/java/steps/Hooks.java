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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            if (scenario.isFailed()) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

                // Create folder if it doesn't exist
                File screenshotDir = new File(System.getProperty("user.dir") + File.separator + "ScreenShots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdirs();
                }

                // Capture screenshot as file
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File(screenshotDir, screenshotName);
                Files.copy(srcFile.toPath(), destFile.toPath());

                // Attach screenshot to report also
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", screenshotName);

                logger.error("Scenario '{}' failed. Screenshot saved at: {}", scenario.getName(), destFile.getAbsolutePath());
            } else {
                logger.info("Scenario '{}' passed.", scenario.getName());
            }
        } catch (IOException e) {
            logger.error("Exception while saving screenshot: {}", e.getMessage(), e);
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
