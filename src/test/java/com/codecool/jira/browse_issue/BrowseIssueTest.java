package com.codecool.jira.browse_issue;

import com.codecool.jira.MainTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import page_object_model.browseIssue.IssuePage;
import page_object_model.login.LogIn;
import util.Util;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BrowseIssueTest extends MainTest {
    LogIn login;
    IssuePage issuePage;

    @Before
    public void setup(){
        super.setUp();
        login = new LogIn(driver);
        issuePage = new IssuePage(driver);
        System.out.println("setup");
        login.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        login.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(issuePage.avatarPicture));
    }

    @Test
    public void  testBrowseIssue(){
        String expectedName = "DO_NOT_DELETE";
        String expectedType = "Story";
        String expectedTicketID = "MTP-767";


        issuePage.openURL("https://jira-auto.codecool.metastage.net/browse/MTP-767");

        Assert.assertEquals(expectedName,issuePage.getSummaryNameText());
        Assert.assertEquals(expectedType,issuePage.getIssueTypeText());
        Assert.assertEquals(expectedTicketID,issuePage.getIssueTypeKeyText());

    }

    @Test
    public void browseIssue_nonExistentIssue() {
        String expected = "No issues were found to match your search\n" +
                "Try modifying your search criteria or creating a new issue.";

        String testText = "testText";
        issuePage.quickSearch(testText);
        assertEquals(expected, issuePage.getNoIssueErrorText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browse_issue_data.csv",numLinesToSkip = 1)
    public void browseIssue(String userName, String expected, String projectUrl) {
        login = new LogIn(driver);
        issuePage = new IssuePage(driver);
        login.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        login.login(dotenv.get(userName), dotenv.get("PASSWORD"));
        Util.wait(driver, 3).until(ExpectedConditions.visibilityOf(issuePage.avatarPicture));

        issuePage.openURL(projectUrl);
        String actual = "";
        try {actual = issuePage.getIssueTypeKeyText();}
        catch (NoSuchElementException ignored) {}
        assertEquals(expected, actual);

    }



    @AfterEach
    public void tearDown(){
        System.out.println("after");
        super.tearDown();
    }


}
