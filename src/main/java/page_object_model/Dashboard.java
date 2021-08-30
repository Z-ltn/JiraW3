package page_object_model;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

public class Dashboard extends PageBase{
    @FindBy(id = "header-details-user-fullname")
    private WebElement avatarPicture;
    @FindBy(id = "log_out")
    private WebElement logOutButton;
    @FindBy(id = "create_link")
    private WebElement createIssue;
    @FindBy(id = "project-field")
    private WebElement projectField;
    @FindBy(id = "issuetype-field")
    private WebElement issueType;
    @FindBy(id = "summary")
    private WebElement summaryName;
    @FindBy(id = "create-issue-submit")
    private WebElement createButton;
    @FindBy(id = "qf-create-another")
    private WebElement another;

    public Dashboard(WebDriver driver) {
        super("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa", driver);
    }

    public WebElement getAvatarPicture() {
        return avatarPicture;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getCreateIssue() {
        return createIssue;
    }

    public WebElement getProjectField() {
        return projectField;
    }

    public WebElement getIssueType() {
        return issueType;
    }

    public WebElement getSummaryName() {
        return summaryName;
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getAnother() {
        return another;
    }

    public void logOut() {
        getAvatarPicture().click();
        getLogOutButton().click();
    }

    private void singleIssue(String projectName, String issueType, String summaryName) {
        getCreateIssue().click();
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(getProjectField()));
        getProjectField().click();
        getProjectField().sendKeys(Keys.BACK_SPACE);
        getProjectField().sendKeys(projectName);
        getProjectField().sendKeys(Keys.RETURN);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(getIssueType()));
        getIssueType().click();
        getIssueType().sendKeys(issueType);
        getIssueType().sendKeys(Keys.RETURN);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(getSummaryName()));
        getSummaryName().click();
        getSummaryName().sendKeys(summaryName);
    }

    public void createIssue(String projectName, String issueType, String summaryName, boolean another) {
        singleIssue(projectName, issueType, summaryName);
        if (another) getAnother().click();
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(getCreateButton()));
        getCreateButton().click();
    }
}
