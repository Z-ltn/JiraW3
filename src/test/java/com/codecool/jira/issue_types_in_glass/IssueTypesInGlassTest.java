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
        glassIssueTypes.login(System.getProperty("USER4"), System.getProperty("PASSWORD"));

        List<String> types = glassIssueTypes.getTypes();
        List<String> actual = glassIssueTypes.getGlassTypes();

        assertEquals(types, actual);
    }
}
