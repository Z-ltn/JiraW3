package com.codecool.jira.browse_projects;

import com.codecool.jira.MainTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.projects.AllProjects;
import page_object_model.projects.NonExistent;
import page_object_model.projects.ProjectSummary;

import static keyword.Keyword.getText;
import static keyword.Keyword.openPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProjectsTest extends MainTest {
    ProjectSummary summaryPage;
    AllProjects search;
    NonExistent non;

    @ParameterizedTest
    @CsvFileSource(resources = "/projects.csv", numLinesToSkip = 1)
    public void browseProject_generalCase(String title, String lead, String key) {
        summaryPage = new ProjectSummary(driver);
        summaryPage.login(System.getProperty("USER1"), System.getProperty("PASSWORD"));
        summaryPage.openURL("https://jira-auto.codecool.metastage.net/projects/" + key + "/summary");
        wait.until(ExpectedConditions.visibilityOf(summaryPage.getTitle()));

        assertEquals(title, getText(summaryPage.getTitle()));
        assertEquals(lead, getText(summaryPage.getLead()));
        assertEquals(key, getText(summaryPage.getKey()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/non_existent_projects.csv")
    public void searchForNonExistentProject(String summaryName) {
        search = new AllProjects(driver);
        search.login(System.getProperty("USER2"), System.getProperty("PASSWORD"));
        search.openURL(search.getBaseURL() + "/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        search.searchForProject(summaryName);
        search.waitForNothing();

        assertEquals("No projects were found to match your search", search.getMessage());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/non_existent_projects.csv")
    public void browseForNonExistentProject(String key) {
        non = new NonExistent(driver);
        non.login(System.getProperty("USER2"), System.getProperty("PASSWORD"));
        non.openURL(non.getBaseURL() + "projects/" + key + "/summary");

        assertEquals("You can't view this project It may have been deleted or you don't have permission to view it.", non.getMessage());
    }
}
