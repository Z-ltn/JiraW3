package com.codecool.jira.permissions_with_glass;

import com.codecool.jira.MainTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import page_object_model.glass.GlassPermissions;
import page_object_model.project_settings.PermissionRoles;
import page_object_model.project_settings.ProjectSettingsPermissions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PermissionsWithGlassTest extends MainTest {
    GlassPermissions glassPermissions;
    ProjectSettingsPermissions projectPermissions;

    @ParameterizedTest
    @EnumSource(PermissionRoles.class)
    public void browseProjectPermission_shouldBeTheSameInGlass(PermissionRoles role) {
        glassPermissions = new GlassPermissions(driver);
        projectPermissions = new ProjectSettingsPermissions(driver);
        projectPermissions.login(dotenv.get("USER2"), dotenv.get("PASSWORD"));
        String expected = projectPermissions.getProjectPermission(role.getElement(driver));

        glassPermissions.navigateToPermissions();

        assertTrue(glassPermissions.arePermissionsTheSame(role.getTitle(), expected));
    }
}
