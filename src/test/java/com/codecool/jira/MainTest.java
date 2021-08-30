package com.codecool.jira;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected Dotenv dotenv = Dotenv.load();

    @BeforeEach
    protected void setUp() {
        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", "/Users/popesz/Downloads/chromedriver");
        }
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    protected void tearDown() {
        logout();
        driver.close();
    }

    protected void login() {
        Dotenv dotenv = Dotenv.load();
        driver.get(" https://jira-auto.codecool.metastage.net/login.jsp");
        driver.findElement(By.id("login-form-username")).sendKeys(dotenv.get("JIRA_USERNAME"));
        driver.findElement(By.id("login-form-password")).sendKeys(dotenv.get("JIRA_PASSWORD"));
        driver.findElement(By.id("login-form-submit")).click();
    }

    protected void logout() {
        driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]")).click();
        driver.findElement(By.id("log_out")).click();
    }
}