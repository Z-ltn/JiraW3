package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {
    protected String url;
    protected String title;

    public PageBase(String url, WebDriver driver) {
        this.url = url;
        PageFactory.initElements(driver, this);
    }

    public void getPage(WebDriver driver) {
        driver.get(getUrl());
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getUrl() {
        return url;
    }

    public void closePage(WebDriver driver) {
        driver.close();
    }
}
