package page_object_model.logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

import static keyword.Keyword.TIME;
import static keyword.Keyword.getText;

public class Logout extends PageBase{
   @FindBy(xpath="//*[@id=\"login-form\"]/div[1]/div[1]/p[2]") protected WebElement warningMessage;

    public Logout(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validateLogout(String expected) {
        openURL("https://jira-auto.codecool.metastage.net/browse/MTP");
        Util.wait(driver,TIME).until(ExpectedConditions.visibilityOf(warningMessage));
        return expected.equals(getText(warningMessage));
    }
}
