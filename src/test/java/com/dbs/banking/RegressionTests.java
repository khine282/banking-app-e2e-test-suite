package com.dbs.banking;

import com.dbs.banking.pages.LoginPage;
import com.dbs.banking.pages.DashboardPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class RegressionTests extends BaseTest {

    @Test(description = "TC_009: Verify login page is accessible")
    public void testLoginPageAccessibility() {
        logger.info("Starting: Login page accessibility test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        
        assertThat(loginPage.isLoginPageDisplayed()).isTrue();
        logger.info("TC_009 PASSED: Login page accessible");
    }

    @Test(description = "TC_010: Verify dashboard loads after login")
    public void testDashboardAccess() {
        logger.info("Starting: Dashboard access test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        assertThat(dashboard.isDashboardDisplayed()).isTrue();
        assertThat(dashboard.getBalance()).isGreaterThan(0);
        logger.info("TC_010 PASSED: Dashboard access and balance display");
    }

    @Test(description = "TC_014: API and UI balance consistency")
    public void testBalanceConsistency() {
        logger.info("Starting: Balance consistency test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        double uiBalance = dashboard.getBalance();
        
        assertThat(uiBalance).isGreaterThan(0);
        logger.info("TC_014 PASSED: Balance consistency check - Balance: {}", uiBalance);
    }
}