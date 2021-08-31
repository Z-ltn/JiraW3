package com.codecool.jira.components_with_glass;

import com.codecool.jira.MainTest;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page_object_model.glass.Components;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentsWithGlassTest extends MainTest {
    Components components;

    @Before
    public void setup() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvSource({"ComponentTest,Unassigned,ComponentTestEdited"})
    public void componentsGeneralCase(String componentName, String assigneeName, String editComponentName) {
        components = new Components(driver);

        components.openURL("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        components.login(dotenv.get("USER1"), dotenv.get("PASSWORD"));

        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        components.createComponent(componentName, assigneeName);
        components.editComponent(editComponentName);
        components.openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.codecanvas.glass:glass");

        assertEquals(editComponentName, components.getGlassComponentName());

        components.deleteComponent();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }
}
