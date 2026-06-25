package com.dbs.banking.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(TransferPage.class);

    // Locators - CORRECTED
    private By amountField = By.id("amount"); // ✅ Correct
    private By fromAccountDropdown = By.id("fromAccountId"); // ✅ Correct
    private By toAccountDropdown = By.id("toAccountId"); // ✅ Correct (was text input, now dropdown!)
    private By submitButton = By.xpath("//input[@type='submit'][@value='Transfer']");
    private By successMessage = By.id("showResult");
    private By errorMessage = By.xpath("//p[@class='error']");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void selectFromAccount(String accountId) {
        logger.info("Selecting from account: {}", accountId);
        selectDropdownByValue(fromAccountDropdown, accountId);
    }


    public void selectToAccount(String accountId) {
        logger.info("Selecting to account: {}", accountId);
        selectDropdownByValue(toAccountDropdown, accountId);
    }

    public void enterAmount(double amount) {
        logger.info("Entering amount: {}", amount);

        // Wait for field to be clickable
        WebElement element = waitForClickableElement(amountField);

        // Small pause
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Clear and type
        element.clear();
        element.sendKeys(String.valueOf(amount));
        logger.info("Amount entered: {}", amount);
    }

    public void submitTransfer() {
        logger.info("Submitting transfer");
        click(submitButton);

        // Wait for form submission to process
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Transfer submitted and processing");
    }

    public boolean isTransferSuccessful() {
        return waitForElement(successMessage).isDisplayed();
    }

    public String getErrorMessage() {
        if (isErrorDisplayed()) {
            return getText(errorMessage);
        }
        return "";
    }

    public boolean isErrorDisplayed() {
        try {
            return waitForElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void performTransfer(String fromAccount, String toAccount, double amount) {
        logger.info("Performing transfer from {} to {} amount {}", fromAccount, toAccount, amount);
        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        enterAmount(amount);
        submitTransfer();
    }
}