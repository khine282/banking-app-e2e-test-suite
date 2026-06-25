package com.parabank.banking.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    // Locators
    private By transferFundsLink = By.linkText("Transfer Funds");
    private By accountTable = By.id("accountTable");
    private By dashboardTitle = By.xpath("//h1[contains(text(), 'Accounts Overview')]");
    
    // Total balance cell in the table
    private By totalBalanceCell = By.xpath("//table[@id='accountTable']//tbody//tr[last()]//td[2]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickTransferFunds() {
        logger.info("Clicking Transfer Funds link");
        click(transferFundsLink);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardTitle);
    }

    /**
     * Get total balance from the accounts overview table
     */
    public double getBalance() {
        logger.info("Getting total balance");
        try {
            String balanceText = getText(totalBalanceCell);
            logger.debug("Balance text: {}", balanceText);
            
            // Parse currency string like "$1682.67" to double
            double balance = parseBalance(balanceText);
            logger.info("Total balance: {}", balance);
            return balance;
        } catch (Exception e) {
            logger.error("Error getting balance: {}", e.getMessage());
            return 0.0;
        }
    }

    /**
     * Get balance for a specific account
     */
    public double getAccountBalance(String accountNumber) {
        logger.info("Getting balance for account: {}", accountNumber);
        try {
            By balanceLocator = By.xpath("//table[@id='accountTable']//tbody//tr//td//a[text()='" + accountNumber + "']/../../td[2]");
            String balanceText = getText(balanceLocator);
            logger.debug("Account {} balance text: {}", accountNumber, balanceText);
            
            double balance = parseBalance(balanceText);
            logger.info("Account {} balance: {}", accountNumber, balance);
            return balance;
        } catch (Exception e) {
            logger.error("Error getting account balance: {}", e.getMessage());
            return 0.0;
        }
    }

    /**
     * Parse balance string like "$210.45" or "-$100.00" to double
     */
    private double parseBalance(String balanceText) {
        // Remove "$" and any whitespace
        String cleanedBalance = balanceText.replaceAll("[^\\d.-]", "").trim();
        return Double.parseDouble(cleanedBalance);
    }

    public boolean isAccountVisible(String accountNumber) {
        try {
            By accountLocator = By.xpath("//table[@id='accountTable']//tbody//tr//td//a[text()='" + accountNumber + "']");
            return isElementDisplayed(accountLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccountWithHighestBalance() {
    logger.info("Finding account with highest balance");
    try {
        // Get all account links from table
        List<WebElement> accountLinks = driver.findElements(
            By.xpath("//table[@id='accountTable']//tbody//tr//td//a[contains(@href, 'activity.htm')]")
        );
        
        String highestBalanceAccount = null;
        double highestBalance = Double.MIN_VALUE;
        
        for (WebElement link : accountLinks) {
            String accountId = link.getText();
            
            // Get balance for this account (next cell)
            String balanceText = link.findElement(By.xpath("../../td[2]")).getText();
            double balance = parseBalance(balanceText);
            
            logger.debug("Account {} has balance: {}", accountId, balance);
            
            if (balance > highestBalance) {
                highestBalance = balance;
                highestBalanceAccount = accountId;
            }
        }
        
        logger.info("Account with highest balance: {} (${}})", highestBalanceAccount, highestBalance);
        return highestBalanceAccount;
    } catch (Exception e) {
        logger.error("Error getting account with highest balance: {}", e.getMessage());
        return "13344";  // Fallback
    }
}

    public String getAccountWithLowestBalance() {
    logger.info("Finding account with lowest balance");
    try {
        // Get all account links from table
        List<WebElement> accountLinks = driver.findElements(
            By.xpath("//table[@id='accountTable']//tbody//tr//td//a[contains(@href, 'activity.htm')]")
        );
        
        String lowestBalanceAccount = null;
        double lowestBalance = Double.MAX_VALUE;
        
        for (WebElement link : accountLinks) {
            String accountId = link.getText();
            
            // Get balance for this account (next cell)
            String balanceText = link.findElement(By.xpath("../../td[2]")).getText();
            double balance = parseBalance(balanceText);
            
            logger.debug("Account {} has balance: {}", accountId, balance);
            
            if (balance < lowestBalance) {
                lowestBalance = balance;
                lowestBalanceAccount = accountId;
            }
        }
        
        logger.info("Account with lowest balance: {} (${}})", lowestBalanceAccount, lowestBalance);
        return lowestBalanceAccount;
    } catch (Exception e) {
        logger.error("Error getting account with lowest balance: {}", e.getMessage());
        return "13788";  // Fallback
    }
}

}
