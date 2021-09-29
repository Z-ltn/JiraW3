package com.codecool.jira.login;


import com.codecool.jira.MainTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.login.LogIn;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends MainTest {
    LogIn login;

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void login_fromDashboard(String user) {
        String expected = System.getProperty(user);

        login = new LogIn(driver);
        login.login(System.getProperty(user), System.getProperty("PASSWORD"));

        assertTrue(login.validateLogin(expected));
    }

    @Test
    public void login_emptyCredentials() {
        String expected = "Sorry, your username and password are incorrect - please try again.";

        login = new LogIn(driver);
        login.openURL(login.getBaseURL() + "/login.jsp");
        login.loginUsingEmptyCredentials();

        assertTrue(login.validateErrorMessage(expected));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void login_wrongPassword(String user) {
        String expected = "Sorry, your username and password are incorrect - please try again.";

        login = new LogIn(driver);
        login.openURL(login.getBaseURL() + "/login.jsp");
        login.login(System.getProperty(user),"cica");

        assertTrue(login.validateWrongPassword(expected));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void login_successfulFromLoginPage(String user) {
        String expected = System.getProperty(user);

        login = new LogIn(driver);
        login.openURL(login.getBaseURL() + "/login.jsp");
        login.login(System.getProperty(user), System.getProperty("PASSWORD"));

        assertTrue(login.validateLogin(expected));
    }

    @Disabled("Captcha cannot be automated")
    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void login_captchaAfter3rdTry(String user) {
        String expected = "Sorry, your userid is required to answer a CAPTCHA question correctly.";

        login = new LogIn(driver);
        login.openURL(login.getBaseURL() + "/login.jsp");
        login.captcha(user);

        assertTrue(login.validateCaptcha(expected));
    }
}
