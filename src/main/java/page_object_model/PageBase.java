package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

import static keyword.Keyword.*;

public abstract class PageBase {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup protected WebElement username;
    @FindBy(id="login-form-password")@CacheLookup protected WebElement password;
    @FindBy(xpath="//input[@value='Log In']")@CacheLookup protected WebElement logInButton;
    @FindBy(id="header-details-user-fullname") public WebElement avatarPicture;
    @FindBy(id="log_out") protected WebElement logOutButton;
    @FindBy(id= "footer-logo")@CacheLookup private WebElement footerLogo;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);}

    public void openURL(String url) {
        openPage(driver, url);
    }

    public void login(String username, String password) {
        Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(footerLogo));

        sendMessage(this.username, username);
        sendMessage(this.password, password);
        clickOn(logInButton);
    }

    public void logout() {
        clickOn(avatarPicture);
        clickOn(logOutButton);
    }
}
