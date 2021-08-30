package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
    public static ChromeDriver getDriver() {
        return new ChromeDriver();
    }

    public static WebDriverWait wait(WebDriver driver, int time) {
        return new WebDriverWait(driver, time);
    }
}
