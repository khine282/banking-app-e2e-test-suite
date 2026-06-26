package com.parabank.banking;

import com.parabank.banking.pages.LoginPage;
import com.parabank.banking.pages.DashboardPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class LoginTests extends BaseTest {

    @Test(description = "TC_001: Successful login with valid credentials")
    public void testSuccessfulLogin() {
        logger.info("Starting: Successful login test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("john", "demo");
        
        DashboardPage dashboard = new DashboardPage(driver);
        assertThat(dashboard.isDashboardDisplayed()).isTrue();
        logger.info("TC_001 PASSED: Successful login");
    }

    @Test(description = "TC_006: Invalid credentials error handling", enabled = false) // Disabled due to parabank issue
    public void testInvalidCredentials() {
        logger.info("Starting: Invalid credentials test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("invalid", "wrong");
        
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        assertThat(loginPage.getErrorMessage()).isNotEmpty();
        logger.info("TC_006 PASSED: Invalid credentials error displayed");
    }

    @Test(description = "TC_007: Empty username validation")
    public void testEmptyUsername() {
        logger.info("Starting: Empty username test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("", "password");
        
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        logger.info("TC_007 PASSED: Empty username validation");
    }

    @Test(description = "TC_008: Empty password validation")
    public void testEmptyPassword() {
        logger.info("Starting: Empty password test");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://parabank.parasoft.com");
        loginPage.login("username", "");
        
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        logger.info("TC_008 PASSED: Empty password validation");
    }
}
