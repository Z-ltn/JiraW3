package com.codecool.jira.edit_issue;

import com.codecool.jira.MainTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.Dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EditIssueTest extends MainTest {
    Dashboard dashboard;

    @ParameterizedTest
    @CsvFileSource(resources = "/edit_issue.csv")
    public void editIssue(String user, String url) {
        dashboard = new Dashboard(driver);

        dashboard.login(System.getProperty(user), System.getProperty("password"));

        dashboard.openURL(url);
        if (!dashboard.canEditIssue()){
            fail( "The user has no permission to edit, or open the issue");
        }
        dashboard.editIssue("editTest_edited");
        assertEquals("editTest_edited", dashboard.getEditedSummaryName());
        dashboard.editIssue("editTest");
    }
}
