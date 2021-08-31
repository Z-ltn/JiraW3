package page_object_model.project_settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

public class ProjectSettingsSummary extends PageBase {
    @FindBy(className = "project-issuetypes-teaser")
    WebElement issueTypesConatiner; //li elements containing a tags which's title and text content is what we need
    @FindBy(id = "view_project_roles")
    WebElement usersAndRoles;
    @FindBy(id = "view_project_permissions")
    WebElement permissions;

    public ProjectSettingsSummary(WebDriver driver) {
        super(driver);
    }
}
