package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LogIn {
    public WebDriver driver = new ChromeDriver();
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(id="login-form-submit")@CacheLookup private WebElement logInButton;

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
