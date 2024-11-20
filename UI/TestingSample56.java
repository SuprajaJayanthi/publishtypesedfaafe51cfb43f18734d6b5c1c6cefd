package TestCase;
Below is a Selenium test script in Java that meets the specified criteria. The script uses WebDriverWait for synchronization, captures screenshots upon test failure, and utilizes Extent Reports for logging and reporting. Soft assertions are implemented using TestNG's soft assertions.

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

public class EditPreferences-UpdateLanguage {
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
    public void testEditPreferencesUpdateLanguage() {
        test = extent.createTest("Edit Preferences - Update Language", "Verify that the customer can edit and update their previously saved language preference.");

        try {
            // Step 1: Log in to the application
            driver.get("https://example.com/login");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("testuser");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("password");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
            test.log(Status.PASS, "Logged in to the application");

            // Step 2: Navigate to the preferences/settings page
            wait.until(ExpectedConditions.elementToBeClickable(By.id("settingsLink"))).click();
            test.log(Status.PASS, "Navigated to the preferences/settings page");

            // Step 3: Change the language to a different option
            WebElement languageDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("languageDropdown")));
            languageDropdown.click();
            WebElement newLanguageOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='newLanguage']")));
            newLanguageOption.click();
            test.log(Status.PASS, "Changed the language to a different option");

            // Step 4: Save the updated preferences
            wait.until(ExpectedConditions.elementToBeClickable(By.id("saveButton"))).click();
            test.log(Status.PASS, "Saved the updated preferences");

            // Step 5: Log out and log back in
            wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutButton"))).click();
            test.log(Status.PASS, "Logged out of the application");

            // Log back in
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("testuser");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("password");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
            test.log(Status.PASS, "Logged back in to the application");

            // Verify the updated language preference
            WebElement currentLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentLanguage")));
            softAssert.assertEquals(currentLanguage.getText(), "newLanguage", "Language preference was not updated correctly");
            test.log(Status.PASS, "Verified the updated language preference");

        
} catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            captureScreenshot(driver, "testEditPreferencesUpdateLanguage");
        
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


This script includes:
- WebDriver and WebDriverWait for synchronization.
- Extent Reports for logging and reporting.
- Soft assertions using TestNG's SoftAssert.
- Screenshot capture on test failure.
- Comprehensive logging and reporting for each step.