package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
    public static WebDriver getDriver() {
        return new ChromeDriver();
    }
    public static WebDriverWait wait(WebDriver driver, int time) {
        return new WebDriverWait(driver, time);
    }
    public static void waitFor(WebElement element) {
        WebDriverWait wait = wait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
