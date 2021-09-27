package com.codecool.jira;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Util;

import java.util.Objects;
import java.util.Random;
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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = Util.wait(driver, 10);
    }

    @AfterEach
    protected void tearDown() {
        driver.quit();
    }

    public static String generateRandomString(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
        }
}