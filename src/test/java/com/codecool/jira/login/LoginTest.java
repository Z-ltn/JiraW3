package com.codecool.jira.login;


import com.codecool.jira.MainTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import page_object_model.login.DashboardLogIn;
import page_object_model.login.LogIn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends MainTest {

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @ValueSource(strings = {"user7", "user8", "user9", "user10"})
    public void login_fromDashboard(String user) {
        String expected = dotenv.get(user);

        LogIn login = new LogIn(driver);
        login.logIn(dotenv.get(user),dotenv.get("PASSWORD"));

        assertTrue(login.validateLogin(expected));
    }

    @Test
    public void login_emptyCredentials() {
        String expected = "Sorry, your username and password are incorrect - please try again.";

        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-submit\"]")).click();

        assertEquals(expected, driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form\"]/div[1]/div[1]/p")).getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"user7", "user8", "user9", "user10"})
    public void login_wrongPassword(String user) {
        String expected = "Sorry, your username and password are incorrect - please try again.";

        LogIn login = new LogIn(driver);
        login.logIn(dotenv.get(user),dotenv.get("cica"));

        assertTrue(login.validateWrongPassword(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user7", "user8", "user9", "user10"})
    public void login_successfulFromLoginPage(String user) {
        String expected = dotenv.get(user);

        DashboardLogIn dashboardLogin = new DashboardLogIn(driver);
        LogIn login = new LogIn(driver);
        dashboardLogin.logIn(user,dotenv.get("PASSWORD"));

        assertTrue(login.validateLogin(expected));
    }

    //    @Test
    public void login_captchaAfter3rdTry() {
        String expected = "Sorry, your userid is required to answer a CAPTCHA question correctly.";

        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-username\"]")).sendKeys(dotenv.get("JIRA_USERNAME"));
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-submit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-submit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-submit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form-submit\"]")).click();

        assertEquals(expected, driver.findElement(By.xpath("//*[@id=\"com.codecool.jira.login-form\"]/div[1]/div[1]/p")).getText());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
