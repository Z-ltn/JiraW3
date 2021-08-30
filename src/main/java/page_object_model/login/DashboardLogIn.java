package page_object_model.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static keyword.Keyword.*;

public class DashboardLogIn {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(xpath = "//input[@value='Log In']") @CacheLookup private WebElement logInButton;

    public DashboardLogIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        openPage(driver, "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

    public void logIn(String username, String password) {
        sendMessage(this.username, username);
        sendMessage(this.password, password);
        clickOn(logInButton);
    }
}
