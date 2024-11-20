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

public class AccountCreationConfirmationMessage {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Account Creation Confirmation Message", "Verify that the system displays a confirmation message with the new account ID upon successful account creation.");

        // Set up SoftAssert
        softAssert = new SoftAssert();
    
}

    @Test
    public void testAccountCreationConfirmationMessage() {
        try {
            // Step 1: Navigate to the account creation page
            driver.get("http://example.com/account-creation");
            test.log(Status.INFO, "Navigated to the account creation page");

            // Step 2: Enter complete and correct details in the form
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            nameField.sendKeys("John Doe");
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("john.doe@example.com");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("SecurePassword123");
            test.log(Status.INFO, "Entered complete and correct details in the form");

            // Step 3: Submit the form
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            submitButton.click();
            test.log(Status.INFO, "Submitted the form");

            // Step 4: Verify that the system displays a confirmation message with the new account ID
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            String confirmationText = confirmationMessage.getText();
            softAssert.assertTrue(confirmationText.contains("Account ID"), "Confirmation message does not contain 'Account ID'");
            test.log(Status.INFO, "Verified that the system displays a confirmation message with the new account ID");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testAccountCreationConfirmationMessage");
        
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


This script includes the following:
1. WebDriver and WebDriverWait setup.
2. Extent Reports setup for logging and reporting.
3. Soft assertions using TestNG's SoftAssert.
4. Exception handling and screenshot capture on test failure.
5. Detailed logging for each test step.