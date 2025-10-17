package qa.automation.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;

public class FileDownloadTest {

    public WebDriver driver ;
    public  String downloadDir;
    @BeforeClass
    public void setup() {
         driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String downloadDir = System.getProperty("user.home") + "/Downloads";

        // Set download behavior
        options.addArguments("download.default_directory=" + downloadDir);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void verifyFileDownload() throws Exception {
        String fileName = "TestFile"; // whatever you want to enter
        String expectedFileName = fileName + ".txt";

        // 1. Launch the URL
        driver.get("https://testing.qaautomationlabs.com/file-download.php");

        // 2. Enter file name
        driver.findElement(By.id("textInput")).clear();
        driver.findElement(By.id("textInput")).sendKeys(fileName);

        // 3. Click on Generate button
        driver.findElement(By.cssSelector(".card-tools button")).click();

        // 4. Click on the Download link
        driver.findElement(By.cssSelector("a#downloadLink")).click();

        // ------- Wait for the file to appear in Downloads -------
        File downloadedFile = waitForFile(downloadDir, expectedFileName, 10);
        Assert.assertNotNull(downloadedFile, "File was not downloaded!");

        // ------- Verify its content (the name is usually same or has text) -------
        try (BufferedReader br = new BufferedReader(new FileReader(downloadedFile))) {
            String content = br.readLine();
            // The downloaded file's text typically includes the name; adjust assertion as needed
            Assert.assertTrue(content.contains(fileName),
                    "File content does not contain the expected text: " + fileName);
        }
    }

    private File waitForFile(String dirPath, String fileName, int timeoutSeconds) throws InterruptedException {
        File dir = new File(dirPath);
        File file = new File(dirPath + File.separator + fileName);

        int waited = 0;
        while (waited < timeoutSeconds) {
            if (file.exists() && file.length() > 0) {
                return file;
            }
            Thread.sleep(1000);
            waited++;
        }
        return null; // not found in time
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
