package com.codecool.jira.glass.versions;

import com.codecool.jira.MainTest;
import org.junit.jupiter.api.Test;
import page_object_model.Dashboard;
import page_object_model.glass.GlassDocumentation;
import page_object_model.glass.GlassVersion;
import page_object_model.glass.GlassIssueAndVersionConnected;
import page_object_model.projects.OpenIssue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VersionTest extends MainTest {
    GlassVersion glassVersion;
    GlassDocumentation glassDocumentation;
    String versionName;
    String editVersionName;

    @Test
    public void glass_addNewVersion() {
        versionName = generateRandomString(6);
        glassVersion = new GlassVersion(driver);
        glassVersion.login(System.getProperty("USER3"),System.getProperty("PASSWORD"));
        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersionWithWrongReleaseDate(versionName);

        assertEquals("The start date cannot be after the release date.", glassVersion.getErrorMessage());

        glassVersion.changeReleaseDate();
        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL(glassVersion.getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExistsByStatus(versionName, "Unreleased"));

        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion(versionName, false);
    }

    @Test
    public void glass_releaseVersion() {
        versionName = generateRandomString(6);
        glassVersion = new GlassVersion(driver);
        glassVersion.login(System.getProperty("USER3"),System.getProperty("PASSWORD"));
        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion(versionName);
        glassVersion.releaseVersion(versionName);

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL(glassVersion.getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExistsByStatus(versionName, "Released"));

        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion(versionName, false);
    }

    @Test
    public void glass_editVersion() {
        versionName = generateRandomString(6);
        editVersionName = generateRandomString(6);
        glassVersion = new GlassVersion(driver);
        glassVersion.login(System.getProperty("USER3"),System.getProperty("PASSWORD"));
        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion(versionName);
        glassVersion.editVersion(versionName, editVersionName);

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL(glassVersion.getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExistsByStatus(editVersionName, "Unreleased"));

        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion(editVersionName, false);
    }

    @Test
    public void glass_archiveVersion() {
        versionName = generateRandomString(6);
        glassVersion = new GlassVersion(driver);
        glassVersion.login(System.getProperty("USER3"),System.getProperty("PASSWORD"));
        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion(versionName);
        glassVersion.archiveVersion(versionName);

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL(glassVersion.getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExistsByStatus(versionName, "Archived"));

        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion(versionName, true);
    }

    @Test
    public void version_connectIssueWithVersion() {
        versionName = generateRandomString(6);
        glassVersion = new GlassVersion(driver);
        glassVersion.login(System.getProperty("USER3"),System.getProperty("PASSWORD"));
        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion(versionName);

        Dashboard dashboard = new Dashboard(driver);
        dashboard.openURL(glassVersion.getBaseURL() + "/secure/Dashboard.jspa");
        dashboard.createIssueForGlass("Use It To Practice Project (PP)", "Task", "version_connection_test", false, versionName);

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL(glassVersion.getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");
        glassDocumentation.openVersionPage(versionName);
        glassDocumentation.changeTab();

        GlassIssueAndVersionConnected glassIssueAndVersionConnected = new GlassIssueAndVersionConnected(driver);
        String issueID = glassIssueAndVersionConnected.getIssueID("version_connection_test");

        assertTrue(glassIssueAndVersionConnected.isIssueConnected("version_connection_test"));

        glassVersion.openURL(glassVersion.getBaseURL() + "/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion(versionName, false);

        OpenIssue openIssue = new OpenIssue(driver);
        openIssue.openURL(glassVersion.getBaseURL() + "/projects/PP/issues/" + issueID);
        openIssue.deleteIssue(issueID);
    }
}
