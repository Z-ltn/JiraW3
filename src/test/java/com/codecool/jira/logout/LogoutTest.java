package com.codecool.jira.logout;

import com.codecool.jira.MainTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.logout.Logout;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends MainTest {
    Logout logout;


    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void logoutTest(String user) {
        String expected = "If you think you shouldn't get this message, please contact your Jira administrators.";

        logout = new Logout(driver);
        logout.login(System.getProperty(user), System.getProperty("password"));
        logout.logout();

        assertTrue(logout.validateLogout(expected));
    }
}
