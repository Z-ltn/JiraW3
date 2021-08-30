package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static keyword.Keyword.*;

public abstract class PageBase {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(xpath="//input[@value='Log In']")@CacheLookup private WebElement logInButton;
    @FindBy(id="header-details-user-fullname") private WebElement avatarPicture;
    @FindBy(id="log_out") private WebElement logOutButton;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);}

    public void openURL(String url) {
        openPage(driver, url);
    }

    public void login(String username, String password) {
        sendMessage(this.username, username);
        sendMessage(this.password, password);
        clickOn(logInButton);
    }

    public void logout() {
        clickOn(avatarPicture);
        clickOn(logOutButton);
    }

}
