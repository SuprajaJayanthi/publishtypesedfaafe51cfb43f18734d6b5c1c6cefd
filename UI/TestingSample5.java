package TestCase;
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

public class TS58VerifyErrorHandlingforInaccessibleOrder {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver and ExtentReports
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Verify Error Handling for Inaccessible Order History");
        softAssert = new SoftAssert();
    
}

    @Test
    public void testErrorHandlingForInaccessibleOrderHistory() {
        try {
            // Step 1: Log in to the application with valid customer credentials
            driver.get("http://application-url.com/login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("validUsername");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("validPassword");
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            loginButton.click();
            test.log(Status.INFO, "Logged in with valid customer credentials");

            // Step 2: Navigate to the account dashboard
            WebElement accountDashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountDashboard")));
            accountDashboard.click();
            test.log(Status.INFO, "Navigated to the account dashboard");

            // Step 3: Click on the 'Order History' section
            WebElement orderHistorySection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderHistory")));
            orderHistorySection.click();
            test.log(Status.INFO, "Clicked on the 'Order History' section");

            // Step 4: Simulate a scenario where the order history is inaccessible (e.g., server error)
            // This can be done by intercepting the network request or by navigating to a mock error page
            driver.get("http://application-url.com/orderHistoryError");
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "Order history is currently inaccessible.";
            softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch");
            test.log(Status.INFO, "Verified the error message for inaccessible order history");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            captureScreenshot(driver, "testErrorHandlingForInaccessibleOrderHistory");
        
} finally {
            softAssert.assertAll();
        
}
    
}

    @AfterClass
    public void tearDown() {
        // Close the browser and generate the report
        driver.quit();
        extent.flush();
    
}

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + screenshotName + ".png");
        
} catch (IOException e) {
            test.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
        
}
    
}

}


This script includes the following:
1. WebDriver setup and initialization.
2. ExtentReports setup for logging and reporting.
3. Soft assertions using SoftAssert from TestNG.
4. WebDriverWait for explicit waits before each element action.
5. Screenshot capture on test failure and attaching it to the Extent Report.
6. Comprehensive logging and reporting using Extent Reports.
7. Exception handling to ensure the test continues execution and logs failures appropriately.