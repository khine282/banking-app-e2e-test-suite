package com.dbs.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    // Locators
    private By accountBalance = By.id("balance");
    private By transferLink = By.linkText("Transfer Funds");
    private By welcomeMessage = By.xpath("//h1[contains(text(), 'Accounts')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public double getBalance() {
        logger.info("Getting account balance");
        String balanceText = getText(accountBalance).replaceAll("[^0-9.]", "");
        try {
            return Double.parseDouble(balanceText);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse balance: {}", balanceText);
            return 0.0;
        }
    }

    public void clickTransfer() {
        logger.info("Clicking Transfer Funds link");
        click(transferLink);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(welcomeMessage);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}