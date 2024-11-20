package TestCase;
Below is a Selenium test script in Java that meets the specified criteria. The script uses WebDriverWait for synchronization, captures screenshots upon test failure, and utilizes Extent Reports for logging and reporting. Soft assertions are implemented using TestNG's soft assertions.

java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class NegativeTest-SavePreferencesWithoutSelection {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver and WebDriverWait
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        // Set up Extent Reports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void testSavePreferencesWithoutSelection() {
        test = extent.createTest("Negative Test - Save Preferences Without Selection", "Verify that the application handles the scenario where the customer tries to save preferences without making any selection.");

        try {
            // Step 1: Log in to the application
            driver.get("http://application-url.com/login");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            username.sendKeys("testuser");
            password.sendKeys("password");
            loginButton.click();
            test.log(Status.PASS, "Logged in to the application");

            // Step 2: Navigate to the preferences/settings page
            WebElement preferencesLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("preferencesLink")));
            preferencesLink.click();
            test.log(Status.PASS, "Navigated to the preferences/settings page");

            // Step 3: Do not select any preferences
            // No action needed as we are not selecting any preferences

            // Step 4: Attempt to save the preferences
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveButton")));
            saveButton.click();
            test.log(Status.PASS, "Attempted to save preferences without selection");

            // Verify the expected result
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            softAssert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
            softAssert.assertEquals(errorMessage.getText(), "No preferences were selected", "Error message text is incorrect");
            test.log(Status.PASS, "Verified that the application displays an error message indicating that no preferences were selected");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testSavePreferencesWithoutSelection");
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        
}
        // Flush the Extent Reports
        extent.flush();
    
}

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + screenshotName + ".png");
        
} catch (IOException e) {
            e.printStackTrace();
        
}
    
}

}


### Explanation:
1. **WebDriver and WebDriverWait**: The script initializes WebDriver and WebDriverWait to handle browser interactions and synchronization.
2. **Extent Reports**: Extent Reports are set up to log and report test execution details.
3. **Soft Assertions**: TestNG's SoftAssert is used to perform soft assertions, allowing the test to continue execution even after encountering failures.
4. **Test Steps**: The script follows the test steps provided:
   - Logs in to the application.
   - Navigates to the preferences/settings page.
   - Attempts to save preferences without making any selection.
   - Verifies that an error message is displayed.
5. **Exception Handling and Screenshots**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging.
6. **Tear Down**: The script closes the browser and flushes the Extent Reports after test execution.