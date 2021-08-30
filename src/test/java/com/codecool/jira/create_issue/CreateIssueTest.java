package com.codecool.jira.create_issue;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.Dashboard;
import page_object_model.login.LogIn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateIssueTest extends MainTest {
    LogIn login;
    Dashboard dashboard;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue.csv", numLinesToSkip = 1)
    public void createIssue(String user, String projectName, boolean hasAccess) {
        login = new LogIn(driver);
        dashboard = new Dashboard(driver);

        login.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        login.login(dotenv.get(user), dotenv.get("PASSWORD"));

        dashboard.createIssueOnlyProject(projectName);
        assertEquals(hasAccess, dashboard.validateProjectPicture());
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
