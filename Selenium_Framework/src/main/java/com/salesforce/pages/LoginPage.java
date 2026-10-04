package com.salesforce.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@id='forgot_password_link']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
            usernameInput.clear();
            if (username != null) {
                usernameInput.sendKeys(username);
            }
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to enter username into field", e);
        }
    }

    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            passwordInput.clear();
            if (password != null) {
                passwordInput.sendKeys(password);
            }
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to enter password into field", e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to click login button", e);
        }
    }

    public void selectRememberMe(boolean shouldSelect) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
            boolean isCurrentSelected = rememberMeCheckbox.isSelected();
            if (shouldSelect != isCurrentSelected) {
                rememberMeCheckbox.click();
            }
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Failed to toggle Remember Me checkbox", e);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox));
            return rememberMeCheckbox.isSelected();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void doLoginWithRememberMe(String username, String password, boolean rememberMe) {
        enterUsername(username);
        enterPassword(password);
        selectRememberMe(rememberMe);
        clickLogin();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText().trim();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Error message element was not found or visible", e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(forgotPasswordLink)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(usernameInput)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
