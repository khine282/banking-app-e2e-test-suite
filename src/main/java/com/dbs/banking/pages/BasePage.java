package com.dbs.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
        waitForClickableElement(locator).click();
    }

    public void type(By locator, String text) {
        logger.debug("Typing in element: {}, text: {}", locator, text);
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
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
    }

    public void selectDropdownByValue(By locator, String value) {
    logger.debug("Selecting dropdown value: {} from {}", value, locator);
    WebElement element = waitForClickableElement(locator);
    
    try {
        Thread.sleep(300);  // Wait for options to load
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
    Select select = new Select(element);
    select.selectByValue(value);
}

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}