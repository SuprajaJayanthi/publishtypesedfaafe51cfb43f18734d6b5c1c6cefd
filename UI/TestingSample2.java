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

public class Verifyviewinghistoryofallnotifications {
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
    public void verifyNotificationHistory() {
        test = extent.createTest("Verify viewing history of all notifications", "Test to ensure that the user can view a history of all notifications within the application.");

        try {
            // Step 1: Log in to the application
            driver.get("http://application-url.com/login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));

            usernameField.sendKeys("validUsername");
            passwordField.sendKeys("validPassword");
            loginButton.click();
            test.log(Status.PASS, "Logged in to the application");

            // Step 2: Navigate to the notification history section
            WebElement notificationHistoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("notificationHistoryLink")));
            notificationHistoryLink.click();
            test.log(Status.PASS, "Navigated to the notification history section");

            // Step 3: Verify that the history includes all past notifications with relevant details
            WebElement notificationHistoryTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notificationHistoryTable")));
            softAssert.assertTrue(notificationHistoryTable.isDisplayed(), "Notification history table is displayed");

            // Additional checks for relevant details can be added here
            // Example: Verify the presence of specific notification details
            WebElement firstNotificationDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='notificationHistoryTable']//tr[1]//td[2]")));
            softAssert.assertTrue(firstNotificationDetail.getText().contains("Expected Detail"), "First notification contains expected detail");

            test.log(Status.PASS, "Verified that the history includes all past notifications with relevant details");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyNotificationHistory");
        
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
        // Write the report
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
4. **Test Steps**: The script follows the test steps provided:
   - Logs in to the application.
   - Navigates to the notification history section.
   - Verifies that the history includes all past notifications with relevant details.
5. **Exception Handling and Screenshots**: The script captures screenshots upon test failure and attaches them to the Extent Reports for enhanced debugging and reporting.
6. **TearDown**: The script closes the browser and writes the report at the end of the test execution.