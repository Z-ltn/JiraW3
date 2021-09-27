package com.codecool.jira.components_with_glass;

import com.codecool.jira.MainTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page_object_model.glass.Components;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentsWithGlassTest extends MainTest {
    Components components;
    String componentName;
    String editComponentName;

    @Test
    public void componentsGeneralCase() {
        componentName = generateRandomString(6);
        editComponentName = generateRandomString(6);
        components = new Components(driver);

        components.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        components.createComponent(componentName, "Unassigned");
        components.editComponent(editComponentName);
        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertTrue(components.getGlassComponentNames().contains(editComponentName));

        components.deleteComponent(editComponentName);
    }

    @ParameterizedTest
    @CsvSource({"Project lead,Admin,Unassigned"})
    public void componentsProjectDefaultAssigneeCase(String defaultAssignee, String expected1, String expected2) {
        components = new Components(driver);

        components.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        components.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/roles");
        components.editDefaults(defaultAssignee);
        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertEquals(expected1, components.getDefaultAssigneeRole());

        components.openURL("https://jira-auto.codecool.metastage.net/plugins/servlet/project-config/PP/roles");
        components.editDefaults(expected2);
        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertEquals(expected2, components.getDefaultAssigneeRole());
    }
}
