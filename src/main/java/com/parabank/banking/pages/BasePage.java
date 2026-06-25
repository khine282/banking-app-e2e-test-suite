package com.parabank.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

        // ✅ GLOBAL SLOW MODE
    private static final boolean SLOW_MODE = true;
    private static final long SLOW_MODE_PAUSE = 1500;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ Pause helper
    private void pause() {
        if (SLOW_MODE) {
            try {
                Thread.sleep(SLOW_MODE_PAUSE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public WebElement waitForElement(By locator) {
        logger.debug("Waiting for element: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForClickableElement(By locator) {
        logger.debug("Waiting for clickable element: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    

    public void click(By locator) {
    logger.debug("Clicking element: {}", locator);
    try {
        waitForClickableElement(locator).click();
    } catch (Exception e) {
        logger.warn("Click failed, retrying with pause");
        try {
            Thread.sleep(500);
            waitForClickableElement(locator).click();
        } catch (Exception ex) {
            throw new RuntimeException("Could not click element: " + locator, ex);
        }
    }
    pause();  // ✅ ADD THIS LINE
}

    public void type(By locator, String text) {
        logger.debug("Typing in element: {}, text: {}", locator, text);
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
        pause();
    }

    public String getText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        return waitForElement(locator).getText();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }

    public void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        driver.get(url);
          pause();
    }

    public void selectDropdownByValue(By locator, String value) {
    logger.debug("Selecting dropdown value: {} from {}", value, locator);
    WebElement element = waitForClickableElement(locator);
    
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
    Select select = new Select(element);
    
    try {
        select.selectByVisibleText(value);
        logger.debug("Selected by visible text: {}", value);
    } catch (Exception e1) {
        logger.warn("Could not select by visible text, trying by value");
        try {
            select.selectByValue(value);
            logger.debug("Selected by value: {}", value);
        } catch (Exception e2) {
            logger.warn("Could not select by value, trying by index");
            select.selectByIndex(0);
            logger.debug("Selected by index: 0");
        }
    }
    pause();  // ✅ ADD THIS LINE
}

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
