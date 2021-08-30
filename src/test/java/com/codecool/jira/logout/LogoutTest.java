package com.codecool.jira.logout;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.login.LogIn;
import page_object_model.logout.Logout;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends MainTest {
    LogIn login;
    Logout logout;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void logoutTest(String user) {
        String expected = "If you think you shouldn't get this message, please contact your Jira administrators.";

        login = new LogIn(driver);
        logout = new Logout(driver);
        login.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        login.login(dotenv.get(user), dotenv.get("PASSWORD"));
        logout.logout();

        assertTrue(logout.validateLogout(expected));
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
