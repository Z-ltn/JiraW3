package page_object_model.project_settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectSettingsPermissions extends ProjectSettingsSummary{
    @FindBy(xpath = "//tr[@data-permission-key='BROWSE_PROJECTS']/descendant::td/descendant::dl/descendant::dd")
    WebElement browseProject;
    @FindBy(xpath = "//tr[@data-permission-key='CREATE_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    WebElement createIssue;
    @FindBy(xpath = "//tr[@data-permission-key='EDIT_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    WebElement editIssue;

    public ProjectSettingsPermissions(WebDriver driver) {
        super(driver);
    }
}
