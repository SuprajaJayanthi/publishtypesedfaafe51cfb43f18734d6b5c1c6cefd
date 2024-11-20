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

public class Validateemailformat {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Validate Email Format Test", "Ensure the application validates the email format correctly.");
        softAssert = new SoftAssert();
    
}

    @Test
    public void validateEmailFormat() {
        try {
            // Step 1: Open the application
            driver.get("http://application-url.com");
            test.log(Status.INFO, "Opened the application");

            // Step 2: Navigate to the user profile creation page
            WebElement profileCreationLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("profileCreationLink")));
            profileCreationLink.click();
            test.log(Status.INFO, "Navigated to the user profile creation page");

            // Step 3: Enter an invalid email format (e.g., 'user@domain')
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("user@domain");
            test.log(Status.INFO, "Entered an invalid email format");

            // Step 4: Attempt to save the details
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveButton")));
            saveButton.click();
            test.log(Status.INFO, "Attempted to save the details");

            // Expected Result: The application should display an error message indicating an invalid email format
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailErrorMessage")));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "Invalid email format";
            softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match");
            test.log(Status.INFO, "Validated the error message for invalid email format");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "validateEmailFormat");
        
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


### Explanation:
1. **Setup**: The setUp method initializes the WebDriver, WebDriverWait, ExtentReports, and SoftAssert.
2. **Test Method**: The validateEmailFormat method contains the test steps:
   - Open the application.
   - Navigate to the user profile creation page.
   - Enter an invalid email format.
   - Attempt to save the details.
   - Validate the error message.
3. **Exception Handling**: If an exception occurs, it logs the failure and captures a screenshot.
4. **Tear Down**: The tearDown method closes the browser and generates the Extent Report.
5. **Screenshot Capture**: The captureScreenshot method captures and attaches screenshots to the Extent Report upon test failure.
6. **Soft Assertions**: Soft assertions are used to allow the test to continue executing even after encountering failures.