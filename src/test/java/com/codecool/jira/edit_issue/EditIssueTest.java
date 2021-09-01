package com.codecool.jira.edit_issue;

import com.codecool.jira.MainTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.Dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditIssueTest extends MainTest {
    Dashboard dashboard;

    @ParameterizedTest
    @CsvFileSource(resources = "/edit_issue.csv")
    public void editIssue(String user, String url) {
        dashboard = new Dashboard(driver);

        dashboard.login(dotenv.get(user), dotenv.get("PASSWORD"));

        dashboard.openURL(url);
        assertEquals("editTest_edited", dashboard.editIssue("editTest_edited"));
        dashboard.editIssue("editTest");
    }
}
