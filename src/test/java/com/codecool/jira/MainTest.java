package com.codecool.jira;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Dotenv dotenv;
    //protected static Set<String> users = new HashSet<>(Arrays.asList("USER1", "USER2", "USER3", "USER4"));

    @BeforeEach
    protected void setUp() {
        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", "/Users/popesz/Downloads/chromedriver");
        }
        driver = Util.getDriver();
        dotenv = Dotenv.load();
        driver.manage().window().maximize();
        wait = Util.wait(driver, 10);
    }

    @AfterEach
    protected void tearDown() {
        driver.close();
    }
}