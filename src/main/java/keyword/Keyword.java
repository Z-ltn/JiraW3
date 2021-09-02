package keyword;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Keyword {
    public static final int TIME = 3;

    public static void openPage(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void closePage(WebDriver driver) {
        driver.close();
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static String currentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void clickOn(WebElement webElement) {
        webElement.click();
    }

    public static void sendMessage(WebElement webElement, String message) {
        webElement.sendKeys(message);
    }

    public static void sendKey(WebElement webElement, Keys key) {
        webElement.sendKeys(key);
    }

    public static String getText(WebElement webElement) {
        return webElement.getText();
    }

    public static String getValue(WebElement webElement) {
        return webElement.getAttribute("value");
    }

    public static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public static void select(Select select, String value) {
        select.selectByValue(value);
    }

    public static String getCurrentUrl(WebDriver driver) {return driver.getCurrentUrl();}
}
