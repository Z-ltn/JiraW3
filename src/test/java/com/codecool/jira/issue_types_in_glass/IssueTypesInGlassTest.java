package com.codecool.jira.issue_types_in_glass;

import com.codecool.jira.MainTest;
import org.junit.jupiter.api.Test;
import page_object_model.glass.GlassIssueTypes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueTypesInGlassTest extends MainTest {
    GlassIssueTypes glassIssueTypes;

    @Test
    public void issueTypesInGlass() {
        glassIssueTypes = new GlassIssueTypes(driver);
        glassIssueTypes.login(dotenv.get("USER4"), dotenv.get("PASSWORD"));
        glassIssueTypes.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/summary");
        List<String> types = glassIssueTypes.getTypes();
        glassIssueTypes.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");
        List<String> actual = glassIssueTypes.getActualTypes();
        assertEquals(types, actual);
    }
}
