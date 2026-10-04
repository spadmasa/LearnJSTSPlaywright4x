package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidLoginTest extends BaseTest {

    @Test(priority = 1)
    public void testValidLoginSubmissionWithRememberMe() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded());

            loginPage.selectRememberMe(true);
            Assert.assertTrue(loginPage.isRememberMeSelected());

            loginPage.doLogin("automation.user@enterprise-domain.com", "ValidSecureP@ssw0rd2026!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isUrlRedirectedOrProcessing = wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("salesforce.com"),
                    ExpectedConditions.urlContains("lightning.force.com")
            ));
            Assert.assertTrue(isUrlRedirectedOrProcessing);
        } catch (Exception e) {
            Assert.fail("Valid login execution failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 2)
    public void testLoginFormElementsIntegrity() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded());
            Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed());

            loginPage.selectRememberMe(true);
            Assert.assertTrue(loginPage.isRememberMeSelected());

            loginPage.selectRememberMe(false);
            Assert.assertFalse(loginPage.isRememberMeSelected());
        } catch (Exception e) {
            Assert.fail("Login form element verification failed: " + e.getMessage(), e);
        }
    }
}
