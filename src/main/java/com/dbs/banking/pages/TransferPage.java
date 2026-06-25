package com.dbs.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(TransferPage.class);

    // Locators
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountField = By.id("toAccountId");
    private By amountField = By.id("amount");
    private By submitButton = By.xpath("//input[@value='Transfer']");
    private By successMessage = By.xpath("//div[@id='transfer_confirmation_table']");
    private By errorMessage = By.className("error");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void selectFromAccount(String accountId) {
        logger.info("Selecting from account: {}", accountId);
        WebElement dropdown = waitForElement(fromAccountDropdown);
        Select select = new Select(dropdown);
        select.selectByValue(accountId);
    }

    public void selectToAccount(String accountId) {
        logger.info("Entering to account: {}", accountId);
        type(toAccountField, accountId);
    }

    public void enterAmount(double amount) {
        logger.info("Entering amount: {}", amount);
        type(amountField, String.valueOf(amount));
    }

    public void submitTransfer() {
        logger.info("Submitting transfer");
        click(submitButton);
    }

    public boolean isTransferSuccessful() {
        return isElementDisplayed(successMessage);
    }

    public String getErrorMessage() {
        if (isErrorDisplayed()) {
            return getText(errorMessage);
        }
        return "";
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public void performTransfer(String fromAccount, String toAccount, double amount) {
        logger.info("Performing transfer from {} to {} amount {}", fromAccount, toAccount, amount);
        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        enterAmount(amount);
        submitTransfer();
    }
}