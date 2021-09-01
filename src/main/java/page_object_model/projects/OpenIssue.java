package page_object_model.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

import static keyword.Keyword.*;

public class OpenIssue extends PageBase {
    @FindBy(id = "opsbar-operations_more") private WebElement moreButton;
    @FindBy(id = "delete-issue") private WebElement deleteButton;
    @FindBy(id = "delete-issue-submit") private WebElement deleteSubmitButton;

    public OpenIssue(WebDriver driver) {
        super(driver);
    }

    public void deleteIssue(String issueID) {
        clickOn(moreButton);
        clickOn(deleteButton);
        clickOn(deleteSubmitButton);
    }
}
