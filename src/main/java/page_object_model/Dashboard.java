package page_object_model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

import static keyword.Keyword.*;

public class Dashboard extends PageBase {
    @FindBy(id="create_link") private WebElement createIssue;
    @FindBy(id="project-field") private WebElement projectField;
    @FindBy(id="issuetype-field") private WebElement issueType;
    @FindBy(id="summary") private WebElement summaryName;
    @FindBy(id="create-issue-submit") private WebElement createButton;
    @FindBy(id="qf-create-another") private WebElement another;
    @FindBy(xpath="//*[@id=\"project-single-select\"]/img") private WebElement projectPicture;
    @FindBy(xpath="//*[@id=\"aui-flag-container\"]/div/div/a") private WebElement issueLink;
    @FindBy(id="summary-val") private WebElement issuePageSummaryName;
    @FindBy(id="opsbar-operations_more") private WebElement moreButton;
    @FindBy(xpath = "//*[@id=\"delete-issue\"]/a") private WebElement deleteButton;
    @FindBy(id="delete-issue-submit") private WebElement deleteIssueSubmitButton;
    @FindBy(xpath="//*[@id=\"issue-content\"]/div/div/h1") private WebElement deletedIssueMessage;
    @FindBy(id="create-subtask") private WebElement createSubtaskButton;
    @FindBy(id="edit-issue") private WebElement editButton;
    @FindBy(id="edit-issue-submit") private WebElement editSubmitButton;
    @FindBy(linkText="testSubTask") private WebElement testSubTask;
    @FindBy(id="fixVersions-textarea") private WebElement fixVersions;

    public Dashboard(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        openPage(driver, "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

    private void singleIssue(String projectName, String issueType, String summaryName) {
        clickOn(createIssue);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(projectField));
        clickOn(projectField);
        sendKey(projectField, Keys.BACK_SPACE);
        sendMessage(projectField, projectName);
        sendKey(projectField, Keys.RETURN);
        try {
            Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(this.issueType));
        }
        catch (StaleElementReferenceException ignored) {
            Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(this.issueType));
        }
        clickOn(this.issueType);
        sendMessage(this.issueType, issueType);
        sendKey(this.issueType, Keys.RETURN);
        try {
            Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(this.summaryName));
        }
        catch (StaleElementReferenceException ignored) {
            Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(this.summaryName));
        }
        try {
            clickOn(this.summaryName);
        }
        catch (StaleElementReferenceException ignored) {
            clickOn(this.summaryName);
        }
        sendMessage(this.summaryName, summaryName);
    }

    public void createIssue(String projectName, String issueType, String summaryName, boolean another) {
        singleIssue(projectName, issueType, summaryName);
        if (another) clickOn(this.another);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(createButton));
        clickOn(createButton);
        Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(issueLink));
    }

    public void createIssueForGlass(String projectName, String issueType, String summaryName, boolean another, String versionName) {
        singleIssue(projectName, issueType, summaryName);
        if (another) clickOn(this.another);
        sendMessage(fixVersions, versionName);
        sendKey(fixVersions, Keys.RETURN);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(createButton));
        clickOn(createButton);
        Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(issueLink));
    }

    public String editIssue(String issueName) {
        try {
            Util.wait(driver, 2).until(ExpectedConditions.visibilityOf(editButton));
        }
        catch (TimeoutException ignored) {
            return "Edit button not found";
        }
        clickOn(editButton);
        clickOn(summaryName);
        clear(summaryName);
        sendMessage(summaryName, issueName);
        clickOn(editSubmitButton);
        reloadPage(driver);
        return getText(issuePageSummaryName);
    }

    public void createSubTask() {
        Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(issueLink));
        clickOn(issueLink);
        clickOn(moreButton);
        clickOn(createSubtaskButton);
        sendMessage(summaryName, "testSubTask");
        clickOn(createButton);
    }

    public void deleteIssue() {
        clickOn(moreButton);
        clickOn(deleteButton);
        clickOn(deleteIssueSubmitButton);
    }

    public void createIssueOnlyProject(String projectName) {
        clickOn(createIssue);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(projectField));
        clickOn(projectField);
        sendKey(projectField, Keys.BACK_SPACE);
        sendMessage(projectField, projectName);
        sendKey(projectField, Keys.RETURN);
    }

    public boolean getTestSubtaskIsDisplayed() {
        return testSubTask.isDisplayed();
    }

    public String getIssuePageSummaryName() {
        clickIssuePageSummaryName();
        return getText(issuePageSummaryName);
    }

    public void clickIssuePageSummaryName() {
        Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(issueLink));
        clickOn(issueLink);
    }

    public boolean getProjectPictureIsPresent() {
        try {
            Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(projectPicture));
        }
        catch (StaleElementReferenceException ignored) {
            getProjectPictureIsPresent();
        }
        catch (TimeoutException ignored) {
            return false;
        }
        return true;
    }

    public String getDeletedIssueMessage() {
        getIssuePageSummaryName();
        String currentUrl = getCurrentUrl(driver);
        deleteIssue();
        openURL(currentUrl);
        return getText(deletedIssueMessage);
    }
}
