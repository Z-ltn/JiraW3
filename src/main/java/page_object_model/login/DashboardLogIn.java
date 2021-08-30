package page_object_model.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

public class DashboardLogIn extends PageBase{
    @FindBy(id="login-form-username")@CacheLookup
    private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(id="login")@CacheLookup private WebElement logInButton;

    public DashboardLogIn(WebDriver driver) {
        super("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa", driver);
    }

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLogInButton() {
        return logInButton;
    }

    public void setUsername(String username) {
        getUsername().sendKeys(username);
    }

    public void setPassword(String password) {
        getPassword().sendKeys(password);
    }

    public void logIn(String username, String password) {
        setUsername(username);
        setPassword(password);
        getLogInButton().click();
    }
}
