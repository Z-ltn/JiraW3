package com.codecool.jira.browse_issue;

import com.codecool.jira.MainTest;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;
import page_object_model.browseIssue.IssuePage;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class BrowseIssueTest extends MainTest {
    IssuePage issuePage;

    @BeforeEach
    public void setupBeforeEach(){
        issuePage = new IssuePage(driver);
    }

    @Test
    public void  testBrowseIssue(){
        issuePage.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));
        String expectedName = "DO_NOT_DELETE";
        String expectedType = "Story";
        String expectedTicketID = "MTP-767";


        issuePage.openURL("https://jira-auto.codecool.metastage.net/browse/MTP-767");
        assertEquals(expectedName,issuePage.getSummaryNameText());
        assertEquals(expectedType,issuePage.getIssueTypeText());
        assertEquals(expectedTicketID,issuePage.getIssueTypeKeyText());

    }

    @Test
    public void browseIssue_nonExistentIssue() {
        issuePage.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));
        String expected = "No issues were found to match your search\n" +
                "Try modifying your search criteria or creating a new issue.";

        String testText = "testText";
        issuePage.quickSearch(testText);
        assertEquals(expected, issuePage.getNoIssueErrorText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browse_issue_data.csv", numLinesToSkip = 1)
    public void browseIssue(String userName, String expected, String projectUrl) {

        issuePage.login(dotenv.get(userName), dotenv.get("PASSWORD"));

            issuePage.openURL(projectUrl);
            String actual = "";
            try {
                actual = issuePage.getIssueTypeKeyText();
            } catch (NoSuchElementException ignored) {
            }
            assertEquals(expected, actual);
        }

    @AfterEach
    public void tearDownAfterEach(){
    }


}
