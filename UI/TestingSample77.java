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

public class Verifyaccountcreationwithinvalidemailformat {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void testAccountCreationWithInvalidEmailFormat() {
        test = extent.createTest("Verify account creation with invalid email format", "Test the account creation process with an invalid email format.");

        try {
            // Step 1: Navigate to the account creation page
            driver.get("http://example.com/account-creation");
            test.log(Status.INFO, "Navigated to the account creation page");

            // Step 2: Enter an invalid email address (e.g., missing '@' or domain)
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("invalidemail.com");
            test.log(Status.INFO, "Entered an invalid email address");

            // Step 3: Enter all other required details
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("testuser");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("password123");
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword")));
            confirmPasswordField.sendKeys("password123");
            test.log(Status.INFO, "Entered all other required details");

            // Step 4: Submit the account creation form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the account creation form");

            // Expected Result: The system should display an error message indicating that the email format is invalid
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "Invalid email format";
            softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match");
            test.log(Status.INFO, "Verified the error message for invalid email format");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testAccountCreationWithInvalidEmailFormat");
        
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
1. **WebDriver and WebDriverWait**: The script initializes the WebDriver and WebDriverWait to handle browser interactions and wait for elements to be visible or clickable.
2. **ExtentReports**: ExtentReports is used for logging and reporting. It provides detailed test execution reports.
3. **SoftAssert**: Soft assertions are used to allow the test to continue executing even after encountering failures.
4. **Test Steps**: Each test step is implemented with WebDriverWait to ensure synchronization with the application's state.
5. **Exception Handling**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging.
6. **TearDown**: The tearDown method closes the browser and flushes the ExtentReports to generate the final report.

This script ensures that all specified criteria are met, providing a comprehensive and robust test automation solution.