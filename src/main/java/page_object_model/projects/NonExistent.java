package page_object_model.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

public class NonExistent extends PageBase {
    @FindBy(className = "projects-error-page-heading")
    private WebElement h1;
    @FindBy(xpath = "//div[@class='projects-error-page-content']/descendant::p[1]")
    private WebElement p;

    public NonExistent(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        return h1.getText() + " " + p.getText();
    }
}
