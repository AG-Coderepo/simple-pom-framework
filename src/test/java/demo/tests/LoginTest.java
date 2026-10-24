package demo.tests;

import demo.pages.LoginPage;
import java.net.URI;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    /**
     * Runs before each test method and opens visible Chrome on this laptop.
     * Maximizes the window and allows up to 30 seconds for a page to load.
     */
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Opens the demo login page and submits its public sample credentials.
     * Verifies both the successful-login message and the secure page URL.
     * Credentials remain hardcoded for Stage 1; properties are a later stage.
     */
    @Test
    public void validUserCanLogin() {
        LoginPage page = new LoginPage(driver);
        page.open();
        // Public credentials published by this demo website.
        page.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(page.getSuccessMessage().contains("You logged into a secure area!"));
        Assert.assertEquals(URI.create(driver.getCurrentUrl()).getPath(), "/secure");
    }

    /**
     * Runs after each test, including failed tests, and closes the browser session.
     * Checks for null so cleanup also works when setup could not create a driver.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
