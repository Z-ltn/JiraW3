package com.codecool.jira.create_issue;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.Dashboard;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateIssueTest extends MainTest {
    Dashboard dashboard;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue.csv", numLinesToSkip = 1)
    public void createIssue(String user, String projectName) {
        dashboard = new Dashboard(driver);

        dashboard.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        dashboard.login(dotenv.get(user), dotenv.get("PASSWORD"));

        dashboard.createIssueOnlyProject(projectName);
        assertTrue(dashboard.validateProjectPicture());
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
