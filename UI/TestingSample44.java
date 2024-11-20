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

public class RequestAccountDetailsSuccessfully {
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
    public void requestAccountDetailsSuccessfully() {
        test = extent.createTest("Request Account Details Successfully", "Verify that the system allows the customer to request their account details and displays them correctly.");

        try {
            // Step 1: Log in to the application
            driver.get("http://example.com/login");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("testuser@example.com");
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("password123");
            WebElement loginButton = driver.findElement(By.id("loginButton"));
            loginButton.click();
            test.log(Status.PASS, "Logged in to the application successfully.");

            // Step 2: Navigate to the account details section
            WebElement accountDetailsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountDetailsLink")));
            accountDetailsLink.click();
            test.log(Status.PASS, "Navigated to the account details section.");

            // Step 3: Request to view account details
            WebElement viewDetailsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewDetailsButton")));
            viewDetailsButton.click();
            test.log(Status.PASS, "Requested to view account details.");

            // Step 4: Verify that the account details are displayed correctly
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            WebElement verificationStatus = driver.findElement(By.id("verificationStatus"));
            WebElement paymentInfo = driver.findElement(By.id("paymentInfo"));

            softAssert.assertEquals(email.getText(), "testuser@example.com", "Email does not match.");
            softAssert.assertEquals(verificationStatus.getText(), "Verified", "Verification status does not match.");
            softAssert.assertEquals(paymentInfo.getText(), "Valid Payment Info", "Payment information does not match.");

            test.log(Status.PASS, "Account details are displayed correctly.");
        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "requestAccountDetailsSuccessfully");
        
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

        // Write the test results to the report
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
1. **WebDriver and WebDriverWait**: The script initializes WebDriver and WebDriverWait to handle browser interactions and wait for elements to be visible.
2. **ExtentReports**: ExtentReports is used for logging and reporting. It creates a detailed HTML report of the test execution.
3. **SoftAssert**: Soft assertions are used to allow the test to continue executing even after encountering failures.
4. **Test Steps**: The script follows the test steps provided:
   - Log in to the application.
   - Navigate to the account details section.
   - Request to view account details.
   - Verify that the account details are displayed correctly.
5. **Exception Handling and Screenshots**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging and reporting.
6. **TearDown**: The tearDown method closes the browser and writes the test results to the report.