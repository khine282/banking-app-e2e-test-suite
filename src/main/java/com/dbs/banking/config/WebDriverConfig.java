package com.dbs.banking.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);

    public static WebDriver getDriver() {
        logger.info("Initializing WebDriver");
        
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        
        logger.info("WebDriver initialized successfully");
        return driver;
    }

    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }
}