package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MainTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    String gridURL;
    MutableCapabilities browserType;

    @BeforeEach
    protected void setUp() throws MalformedURLException {
        gridURL = "https://" + System.getProperty("gridUser") + ":" + System.getProperty("PASSWORD") + "@seleniumhub.codecool.metastage.net/wd/hub";
        browserType = System.getProperty("browser").equals("firefox") ? new FirefoxOptions() : new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridURL), browserType);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = Util.wait(driver, 30);
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