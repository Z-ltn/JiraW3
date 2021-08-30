package keyword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Keyword {
    public static final int TIME = 3;

    public static void openPage(WebDriver driver, String url) {
        driver.get(url);
    }

    public static String currentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static void clickElement(WebElement webElement) {
        webElement.click();
    }

    public static void writeText(WebElement webElement, String text) {
        webElement.sendKeys(text);
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
}
