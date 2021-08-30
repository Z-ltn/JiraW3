package page_object_model.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

import static keyword.Keyword.*;

public class LogIn extends PageBase {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(xpath="//input[@value='Log In']")@CacheLookup private WebElement logInButton;
    @FindBy(id="up-d-username")@CacheLookup private WebElement loggedInUser;
    @FindBy(className="aui-message-error")@CacheLookup private WebElement incorrectMessage;
    @FindBy(id="header-details-user-fullname")@CacheLookup private WebElement avatar;

    public LogIn(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean validateLogin(String expected) {
        Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(avatar));
        openPage(driver, "https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");
        return expected.equals(getText(loggedInUser));
    }

    public Boolean validateWrongPassword(String expected) {
        return expected.equals(getText(incorrectMessage));
    }

    public boolean validateErrorMessage(String expected) {
        return expected.equals(getText(incorrectMessage));
    }

    public void loginUsingEmptyCredentials() {
        clickOn(logInButton);
    }
}
