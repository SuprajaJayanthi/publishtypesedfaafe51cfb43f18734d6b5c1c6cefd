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

public class TS101Verifyerrormessagewhensettinganinvalid {
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
    public void verifyInvalidFileName() {
        test = extent.createTest("Verify error message when setting an invalid file name", "Test to ensure that the system provides an appropriate error message when an invalid file name is entered.");

        try {
            // Step 1: Navigate to the journal settings page
            driver.get("http://example.com/journal-settings");
            test.log(Status.INFO, "Navigated to the journal settings page");

            // Step 2: Enter an invalid file name in the file name input field
            WebElement fileNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileNameInput")));
            fileNameInput.sendKeys("invalid*file?name");
            test.log(Status.INFO, "Entered an invalid file name");

            // Step 3: Click on the 'Set File' button
            WebElement setFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("setFileButton")));
            setFileButton.click();
            test.log(Status.INFO, "Clicked on the 'Set File' button");

            // Verify the expected result
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "The file name is invalid.";
            softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch");
            test.log(Status.INFO, "Verified the error message");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyInvalidFileName");
        
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
            test.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
        
}
    
}

}


This script includes:
1. WebDriver and WebDriverWait setup.
2. ExtentReports for logging and reporting.
3. Soft assertions using SoftAssert.
4. Exception handling and screenshot capture on test failure.
5. Detailed logging for each step of the test case.