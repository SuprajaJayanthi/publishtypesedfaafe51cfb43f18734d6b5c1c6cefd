package TestCase;
Here is a Selenium test script in Java that meets the specified criteria:

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

public class Verifyaccountcreationconfirmationmessage {
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

        // Set up ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void verifyAccountCreationConfirmationMessage() {
        test = extent.createTest("Verify account creation confirmation message", "Test the confirmation message displayed after successful account creation.");

        try {
            // Step 1: Navigate to the account creation page
            driver.get("http://example.com/account-creation");
            test.log(Status.INFO, "Navigated to account creation page");

            // Step 2: Enter a valid email address
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("valid.email@example.com");
            test.log(Status.INFO, "Entered valid email address");

            // Step 3: Enter all other required details
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            nameField.sendKeys("John Doe");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("SecurePassword123");
            test.log(Status.INFO, "Entered all other required details");

            // Step 4: Submit the account creation form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the account creation form");

            // Verify the confirmation message with the new account ID
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            String messageText = confirmationMessage.getText();
            softAssert.assertTrue(messageText.contains("Account created successfully"), "Confirmation message does not contain expected text");
            softAssert.assertTrue(messageText.contains("Account ID:"), "Confirmation message does not contain Account ID");
            test.log(Status.PASS, "Verified the confirmation message with the new account ID");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyAccountCreationConfirmationMessage");
        
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
        // Flush the ExtentReports
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
1. **WebDriver and WebDriverWait**: The script initializes the WebDriver and WebDriverWait to handle browser interactions and waits.
2. **ExtentReports**: ExtentReports is used for logging and reporting. It provides detailed test execution reports.
3. **SoftAssert**: Soft assertions are used to allow the test to continue executing even after encountering failures.
4. **Test Steps**: Each test step is implemented with WebDriverWait to ensure synchronization with the application's state.
5. **Exception Handling**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging.
6. **TearDown**: The tearDown method closes the browser and flushes the ExtentReports.

This script ensures comprehensive test execution details, effective reporting, and robust exception handling.