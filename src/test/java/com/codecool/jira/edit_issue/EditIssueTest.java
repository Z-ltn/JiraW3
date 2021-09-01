package com.codecool.jira.edit_issue;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.Dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditIssueTest extends MainTest {
    Dashboard dashboard;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/edit_issue.csv")
    public void editIssue(String user, String projectName, String summaryName) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get(user), dotenv.get("PASSWORD"));

        dashboard.createIssue(projectName, "Task", summaryName, false);
        dashboard.clickIssuePageSummaryName();
        assertEquals(summaryName + "_edited", dashboard.editIssue());
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
