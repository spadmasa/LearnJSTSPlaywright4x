package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test(priority = 1)
    public void testInvalidCredentialsDisplaysError() {
        try {
            loginPage.doLogin("invalid.user@salesforce-test.com", "WrongPassword123!");

            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
            String error = loginPage.getErrorMessage();
            Assert.assertTrue(error.contains("Please check your username and password. If you still can't log in, contact your Salesforce administrator.")
                    || error.contains("Please check your username and password"));
        } catch (Exception e) {
            Assert.fail("Invalid credentials test failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 2)
    public void testEmptyPasswordDisplaysError() {
        try {
            loginPage.enterUsername("automation.tester@salesforce-test.com");
            loginPage.enterPassword("");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
            String error = loginPage.getErrorMessage();
            Assert.assertTrue(error.contains("Please enter your password.") || error.contains("Please enter your password"));
        } catch (Exception e) {
            Assert.fail("Empty password test failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 3)
    public void testEmptyUsernameAndEmptyPasswordSubmission() {
        try {
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
            String error = loginPage.getErrorMessage();
            Assert.assertTrue(error.contains("Please enter your password.") || error.contains("Please check your username and password"));
        } catch (Exception e) {
            Assert.fail("Empty credentials submission test failed: " + e.getMessage(), e);
        }
    }
}
