package playwright.demo;

import com.microsoft.playwright.*;
import java.util.function.Consumer;

public class PlaywrightDifferentBrowsersParallel {
    public static void main(String[] args) {

        Consumer<String> runTestInBrowser = (browserName) -> {
            Playwright playwright = null;
            Browser browser = null;
            BrowserContext context = null;

            try {
                playwright = Playwright.create();

                browser = switch (browserName) {
                    case "chromium" -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    default -> throw new IllegalArgumentException("Invalid browser: " + browserName);
                };

                context = browser.newContext();
                Page page = context.newPage();

                page.navigate("https://alphabetaops.com/");
                page.setViewportSize(1920, 1080);

                page.locator(".mb-2:first-child").click();
                page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child").click();
                page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child ul li").click();

                FrameLocator iframe = page.frameLocator("iframe.content-iframe");
                iframe.locator("#plain-text").fill("Nitin");

                System.out.println("✅ Completed on: " + browserName.toUpperCase());

            } finally {
                if (context != null) context.close();
                if (browser != null) browser.close();
                if (playwright != null) playwright.close();

                System.out.println("Closed resources properly for: " + browserName.toUpperCase());
            }
        };

        Thread chromium = new Thread(() -> runTestInBrowser.accept("chromium"));
        Thread firefox  = new Thread(() -> runTestInBrowser.accept("firefox"));
        Thread webkit   = new Thread(() -> runTestInBrowser.accept("webkit"));

        chromium.start();
        firefox.start();
        webkit.start();
    }
}
