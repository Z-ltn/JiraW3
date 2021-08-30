package com.codecool.jira.create_issue;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page_object_model.login.LogIn;

public class CreateIssueTest extends MainTest {
    LogIn login;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issues.csv", numLinesToSkip = 1)
    public void createIssue() {

    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
