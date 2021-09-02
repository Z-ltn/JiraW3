package page_object_model.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

public class AllProjects extends PageBase {
    @FindBy(id = "project-filter-text")
    private WebElement search;
    @FindBy(xpath = "//div[@class='jira-adbox jira-adbox-medium no-project-results']/descendant::h2")
    private WebElement noResult;

    public AllProjects(WebDriver driver) {
        super(driver);
    }

    public void searchForProject(String summary) {
        search.sendKeys(summary);
    }

    public String getMessage() {
        return noResult.getText();
    }

    public void waitForNothing() {
        Util.wait(driver,3).until(ExpectedConditions.visibilityOf(noResult));
    }
}
