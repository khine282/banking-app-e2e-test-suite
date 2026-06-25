package com.parabank.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    // Locators - UPDATED
    private By usernameField = By.name("username");      
    private By passwordField = By.name("password");      // ✅ Changed from By.id
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.className("error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        logger.info("Logging in with username: {}", username);
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        logger.info("Login clicked");
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        if (isErrorDisplayed()) {
            return getText(errorMessage);
        }
        return "";
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }
}
