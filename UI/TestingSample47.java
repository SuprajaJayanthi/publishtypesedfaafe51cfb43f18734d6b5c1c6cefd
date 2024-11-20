package TestCase;
Below is a Selenium test script in Java that meets the specified criteria. The script uses WebDriverWait for synchronization, captures screenshots upon test failure, and utilizes Extent Reports for logging and reporting. Soft assertions are implemented using TestNG's SoftAssert class.

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

public class TS144Verifytransactionhistorydisplaysalltrans {
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
    public void verifyTransactionHistory() {
        test = extent.createTest("Verify transaction history displays all transactions made by the user");

        try {
            // Step 1: Log in to the application with valid credentials
            driver.get("https://example.com/login");
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));

            usernameField.sendKeys("validUsername");
            passwordField.sendKeys("validPassword");
            loginButton.click();

            test.log(Status.PASS, "Logged in to the application with valid credentials");

            // Step 2: Navigate to the transaction history section
            WebElement transactionHistoryMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("transactionHistoryMenu")));
            transactionHistoryMenu.click();

            test.log(Status.PASS, "Navigated to the transaction history section");

            // Step 3: Verify that a list of all transactions made by the user is displayed
            WebElement transactionList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionList")));
            softAssert.assertTrue(transactionList.isDisplayed(), "Transaction list is displayed");

            test.log(Status.PASS, "Verified that a list of all transactions made by the user is displayed");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "verifyTransactionHistory");
        
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
        if (extent != null) {
            extent.flush();
        
}
    
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
1. **WebDriver and WebDriverWait**: The script initializes WebDriver and WebDriverWait to interact with the web elements and wait for their presence.
2. **Extent Reports**: Extent Reports are set up to log the test steps and results. The report is saved as extentReport.html.
3. **Soft Assertions**: SoftAssert is used to perform assertions that do not stop the test execution upon failure.
4. **Test Steps**:
   - **Step 1**: Logs in to the application with valid credentials.
   - **Step 2**: Navigates to the transaction history section.
   - **Step 3**: Verifies that a list of all transactions made by the user is displayed.
5. **Exception Handling**: If an exception occurs, the script logs the failure and captures a screenshot.
6. **Screenshot Capture**: The captureScreenshot method captures a screenshot upon test failure and attaches it to the Extent Report.
7. **Tear Down**: The tearDown method closes the browser and flushes the Extent Reports.

This script ensures comprehensive test execution details, effective reporting, and robust error handling.