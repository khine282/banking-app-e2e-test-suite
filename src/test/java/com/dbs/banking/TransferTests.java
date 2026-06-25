package com.dbs.banking;

import com.dbs.banking.pages.LoginPage;
import com.dbs.banking.BaseTest;
import com.dbs.banking.pages.DashboardPage;
import com.dbs.banking.pages.TransferPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TransferTests extends BaseTest {

    @Test(description = "TC_002: Successful fund transfer")
    public void testSuccessfulTransfer() {
        logger.info("Starting: Successful fund transfer test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        assertThat(dashboard.isDashboardDisplayed()).isTrue();
        
        dashboard.clickTransferFunds();
        
        TransferPage transferPage = new TransferPage(driver);
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.enterAmount(100);
        transferPage.submitTransfer();
        
        assertThat(transferPage.isTransferSuccessful()).isTrue();
        logger.info("TC_002 PASSED: Successful transfer");
    }

    @Test(description = "TC_003: Insufficient balance error")
    public void testInsufficientBalance() {
        logger.info("Starting: Insufficient balance test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickTransferFunds();
        
        TransferPage transferPage = new TransferPage(driver);
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.enterAmount(999999);
        transferPage.submitTransfer();
        
        assertThat(transferPage.isErrorDisplayed()).isTrue();
        logger.info("TC_003 PASSED: Insufficient balance error");
    }

    @Test(description = "TC_004: Invalid recipient account")
    public void testInvalidRecipient() {
        logger.info("Starting: Invalid recipient test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickTransferFunds();
        
        TransferPage transferPage = new TransferPage(driver);
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.enterAmount(100);
        transferPage.submitTransfer();
        
        assertThat(transferPage.isErrorDisplayed()).isTrue();
        logger.info("TC_004 PASSED: Invalid recipient error");
    }

    @Test(description = "TC_005: Decimal amount transfer")
    public void testDecimalAmountTransfer() {
        logger.info("Starting: Decimal amount transfer test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickTransferFunds();
        
        TransferPage transferPage = new TransferPage(driver);
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.enterAmount(10.50);
        transferPage.submitTransfer();
        
        assertThat(transferPage.isTransferSuccessful()).isTrue();
        logger.info("TC_005 PASSED: Decimal amount transfer");
    }

    @Test(description = "TC_012: Verify transfer confirmation details")
    public void testTransferConfirmation() {
        logger.info("Starting: Transfer confirmation test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickTransfer();
        
        TransferPage transferPage = new TransferPage(driver);
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.enterAmount(100);
        transferPage.submitTransfer();
        
        assertThat(transferPage.isTransferSuccessful()).isTrue();
        logger.info("TC_012 PASSED: Transfer confirmation");
    }
}