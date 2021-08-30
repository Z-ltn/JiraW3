package page_object_model;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

import static keyword.Keyword.*;

public class Dashboard {
    WebDriver driver;
    @FindBy(id = "header-details-user-fullname") private WebElement avatarPicture;
    @FindBy(id = "log_out")  private WebElement logOutButton;
    @FindBy(id = "create_link") private WebElement createIssue;
    @FindBy(id = "project-field") private WebElement projectField;
    @FindBy(id = "issuetype-field") private WebElement issueType;
    @FindBy(id = "summary") private WebElement summaryName;
    @FindBy(id = "create-issue-submit") private WebElement createButton;
    @FindBy(id = "qf-create-another") private WebElement another;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        openPage(driver, "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

    public void logOut() {
        clickOn(avatarPicture);
        clickOn(logOutButton);
    }

    private void singleIssue(String projectName, String issueType, String summaryName) {
        clickOn(createIssue);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(projectField));
        clickOn(projectField);
        sendKey(projectField, Keys.BACK_SPACE);
        sendMessage(projectField, projectName);
        sendKey(projectField, Keys.RETURN);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(this.issueType));
        clickOn(this.issueType);
        sendMessage(this.issueType, issueType);
        sendKey(this.issueType, Keys.RETURN);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(this.summaryName));
        clickOn(this.summaryName);
        sendMessage(this.summaryName, summaryName);
    }

    public void createIssue(String projectName, String issueType, String summaryName, boolean another) {
        singleIssue(projectName, issueType, summaryName);
        if (another) clickOn(this.another);
        Util.wait(Util.getDriver(), 10).until(ExpectedConditions.visibilityOf(createButton));
        clickOn(createButton);
    }
}
