package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static keyword.Keyword.clickElement;
import static keyword.Keyword.writeText;

public class LogIn {
    WebDriver driver;
    @FindBy(id="login-form-username")@CacheLookup private WebElement username;
    @FindBy(id="login-form-password")@CacheLookup private WebElement password;
    @FindBy(id="login-form-submit")@CacheLookup private WebElement logInButton;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logIn(String username, String password) {
        writeText(this.username, username);
        writeText(this.password, password);
        clickElement(logInButton);
    }
}
