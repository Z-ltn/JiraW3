package com.codecool.jira.create_issue;

import com.codecool.jira.MainTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import page_object_model.Dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateIssueTest extends MainTest {
    Dashboard dashboard;

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue.csv", numLinesToSkip = 1)
    public void createIssue(String user, String projectName) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get(user), dotenv.get("PASSWORD"));

        dashboard.createIssueOnlyProject(projectName);
        assertTrue(dashboard.getProjectPictureIsPresent());
    }

    @ParameterizedTest
    @CsvSource({"Main Testing Project,Task,TaskTest"})
    public void createIssueGeneralCase(String projectName, String issueType, String summaryName) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        dashboard.createIssue(projectName, issueType, summaryName, false);
        assertEquals(summaryName, dashboard.getIssuePageSummaryName());
        dashboard.deleteIssue();
    }

    @ParameterizedTest
    @CsvSource({"Main Testing Project,Task,TaskTest,You can't view this issue"})
    public void deleteIssue(String projectName, String issueType, String summaryName, String expectedMessage) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        dashboard.createIssue(projectName, issueType, summaryName, false);
        assertEquals(expectedMessage, dashboard.getDeletedIssueMessage());
    }

    @ParameterizedTest
    @CsvSource({"Main Testing Project,Task,TaskTest"})
    public void createSubTask(String projectName, String issueType, String summaryName) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        dashboard.createIssue(projectName, issueType, summaryName, false);
        dashboard.createSubTask();
        assertTrue(dashboard.getTestSubtaskIsDisplayed());
        dashboard.deleteIssue();
    }
}
