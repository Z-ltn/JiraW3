package page_object_model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

import static keyword.Keyword.*;

public class Dashboard extends PageBase {
    WebDriver driver;
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
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(this.issueType));
        clickOn(this.issueType);
        sendMessage(this.issueType, issueType);
        sendKey(this.issueType, Keys.RETURN);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(this.summaryName));
        clickOn(this.summaryName);
        sendMessage(this.summaryName, summaryName);
    }

    public void createIssue(String projectName, String issueType, String summaryName, boolean another) {
        singleIssue(projectName, issueType, summaryName);
        if (another) clickOn(this.another);
        Util.wait(driver, 10).until(ExpectedConditions.visibilityOf(createButton));
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

    public boolean validateIssue(String expectedSummaryName) {
        Util.wait(driver, 10).until(ExpectedConditions.elementToBeClickable(issueLink));
        clickOn(issueLink);
        return expectedSummaryName.equals(getText(issuePageSummaryName));
    }

    public boolean validateProjectPicture() {
        try {
            Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(projectPicture));
        }
        catch (StaleElementReferenceException ignored) {
            validateProjectPicture();
        }
        catch (TimeoutException ignored) {
            return false;
        }
        return true;
    }
}
