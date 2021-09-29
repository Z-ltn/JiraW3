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
    @FindBy(id="up-d-username")@CacheLookup private WebElement loggedInUser;
    @FindBy(className="aui-message-error")@CacheLookup private WebElement incorrectMessage;


    public LogIn(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean validateLogin(String expected) {
        Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(avatarPicture));
        openPage(driver, getBaseURL() + "/secure/ViewProfile.jspa");
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

    public void captcha(String user) {
        sendMessage(username, user);
        for (int i=0; i<4; i++) {
            clickOn(logInButton);
        }
    }
    
    public boolean validateCaptcha(String expected) {
        return expected.equals(getText(incorrectMessage));
    }
}
