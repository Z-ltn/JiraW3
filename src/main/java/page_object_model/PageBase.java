package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static keyword.Keyword.*;

public abstract class PageBase {
    protected WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup protected WebElement username;
    @FindBy(id="login-form-password")@CacheLookup protected WebElement password;
    @FindBy(xpath="//input[@value='Log In']")@CacheLookup protected WebElement logInButton;
    @FindBy(id="header-details-user-fullname") protected WebElement avatarPicture;
    @FindBy(id="log_out") protected WebElement logOutButton;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);}

    public void openURL(String url) {
        openPage(driver, url);
    }

    public void login(String username, String password) {
        openPage(driver, "https://jira-auto.codecool.metastage.net/login.jsp");
        sendMessage(this.username, username);
        sendMessage(this.password, password);
        clickOn(logInButton);
    }

    public void logout() {
        clickOn(avatarPicture);
        clickOn(logOutButton);
    }

    protected void refresh() {
        refreshPage(driver);
    }
}
