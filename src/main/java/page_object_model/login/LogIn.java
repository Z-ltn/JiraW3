package page_object_model.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static keyword.Keyword.*;

public class LogIn {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(xpath = "//input[@value='Log In']")@CacheLookup private WebElement logInButton;
    @FindBy(id="up-d-username")@CacheLookup private WebElement loggedInUser;
    @FindBy(className="aui-message-error")@CacheLookup private WebElement incorrectMessage;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        openPage(driver, "https://jira-auto.codecool.metastage.net/login.jsp");
    }
    public void logIn(String username, String password) {
        sendMessage(this.username, username);
        sendMessage(this.password, password);
        clickOn(logInButton);
    }

    public Boolean validateLogin(String expected) {
        openPage(driver, "https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");
        return expected.equals(getText(loggedInUser));
    }

    public Boolean validateWrongPassword(String expected) {
        return expected.equals(getText(incorrectMessage));
    }
}
