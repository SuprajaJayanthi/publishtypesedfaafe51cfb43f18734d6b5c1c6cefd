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

public class TS120Verifysystemretrievesandstoresreturnand {
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
    public void verifyReturnAndCancelURLs() {
        test = extent.createTest("Verify system retrieves and stores return and cancel URLs");

        try {
            // Step 1: Initiate a PayPal payment
            driver.get("https://example.com/paypal-payment");
            test.log(Status.INFO, "Navigated to PayPal payment page");

            // Step 2: Provide valid return and cancel URLs
            WebElement returnUrlField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("returnUrl")));
            returnUrlField.sendKeys("https://example.com/return");
            test.log(Status.INFO, "Entered return URL");

            WebElement cancelUrlField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancelUrl")));
            cancelUrlField.sendKeys("https://example.com/cancel");
            test.log(Status.INFO, "Entered cancel URL");

            // Step 3: Submit the payment initiation form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitPayment")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the payment initiation form");

            // Step 4: Check the session data for stored return and cancel URLs
            WebElement sessionData = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sessionData")));
            String sessionDataText = sessionData.getText();
            test.log(Status.INFO, "Retrieved session data");

            // Validate the session data
            softAssert.assertTrue(sessionDataText.contains("https://example.com/return"), "Return URL is stored in session data");
            softAssert.assertTrue(sessionDataText.contains("https://example.com/cancel"), "Cancel URL is stored in session data");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyReturnAndCancelURLs");
        
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
        if (extent != null) {
            extent.flush();
        
}
    
}

    private void captureScreenshot(WebDriver driver, String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/" + screenshotName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + screenshotName + ".png");
        
} catch (IOException e) {
            test.log(Status.ERROR, "Failed to capture screenshot: " + e.getMessage());
        
}
    
}

}


This Selenium test script follows the given criteria and includes the following features:
1. WebDriverWait (ExplicitWait) is used before each element action to synchronize test execution with the application's state.
2. Screenshots are captured upon test failure and attached to Extent Reports for enhanced debugging and reporting.
3. Extent Reports are used for logging and reporting to provide comprehensive test execution details and organize test results effectively.
4. Soft assertions are implemented using TestNG's SoftAssert to allow the test to continue executing even after encountering failures, providing a comprehensive overview of all test results at the end of the test run.