package com.dbs.banking;

import com.dbs.banking.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("========== Starting Test ==========");
        driver = WebDriverConfig.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: {}", testResult.getName());
            takeScreenshot(testResult.getName());
        } else if (testResult.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test PASSED: {}", testResult.getName());
        }
        
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
        logger.info("========== Test Complete ==========");
    }

    protected void takeScreenshot(String testName) {
        try {
            String screenshotDir = "target/screenshots";
            Files.createDirectories(Paths.get(screenshotDir));

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = screenshotDir + "/" + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(screenshot.toPath(), Paths.get(filePath));
            
            logger.info("Screenshot saved: {}", filePath);
        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }
}