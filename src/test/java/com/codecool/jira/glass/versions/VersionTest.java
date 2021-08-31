package com.codecool.jira.glass.versions;

import com.codecool.jira.MainTest;
import org.junit.jupiter.api.Test;
import page_object_model.glass.GlassDocumentation;
import page_object_model.glass.GlassVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VersionTest extends MainTest {
    GlassVersion glassVersion;
    GlassDocumentation glassDocumentation;


    @Test
    public void glass_addNewVersion() {
        glassVersion = new GlassVersion(driver);
        glassVersion.login(dotenv.get("USER3"),dotenv.get("PASSWORD"));
        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersionWithWrongReleaseDate();

        assertEquals("The start date cannot be after the release date.", glassVersion.getErrorMessage());

        glassVersion.changeReleaseDate();
        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExists("test"));

        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion("test", false);
    }

    @Test
    public void glass_releaseVersion() {
        glassVersion = new GlassVersion(driver);
        glassVersion.login(dotenv.get("USER3"),dotenv.get("PASSWORD"));
        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion("test");
        glassVersion.releaseVersion("test");

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExistsByStatus("test", "RELEASED"));

        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion("test", false);
    }

    @Test
    public void glass_editVersion() {
        glassVersion = new GlassVersion(driver);
        glassVersion.login(dotenv.get("USER3"),dotenv.get("PASSWORD"));
        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.addNewVersion("test");
        glassVersion.editVersion("test", "test_edited");

        glassDocumentation = new GlassDocumentation(driver);
        glassDocumentation.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(glassDocumentation.doesTheVersionExists("test_edited"));

        glassVersion.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/administer-versions");
        glassVersion.deleteVersion("test_edited", false);
    }
}
