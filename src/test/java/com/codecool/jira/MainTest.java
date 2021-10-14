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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class MainTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    String gridURL;
    MutableCapabilities browserType;

    @BeforeEach
    protected void setUp() throws IOException {
        gridURL = "https://" + System.getProperty("gridUser") + ":" + System.getProperty("password") + "@seleniumhub.codecool.metastage.net/wd/hub";
        browserType = System.getProperty("browser").equals("firefox") ? new FirefoxOptions() : new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridURL), browserType);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = Util.wait(driver, 30);

        File properties = new File("properties.txt");
        FileWriter writer = new FileWriter("properties.txt");
        String props = System.getProperty("user1") + System.getProperty("user2") + System.getProperty("user3") +
                System.getProperty("user4") + System.getProperty("gridUser") + System.getProperty("gridURL") +
                System.getProperty("password") + System.getProperty("browser") + System.getProperty("baseURL") +
                System.getProperty("timeout");
        writer.write(props);
        writer.close();
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