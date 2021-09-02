package page_object_model.project_settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Util;

public class ProjectSettingsPermissions extends ProjectSettingsSummary{
    @FindBy(xpath = "//tr[@data-permission-key='BROWSE_PROJECTS']/descendant::td/descendant::dl/descendant::dd")
    public WebElement browseProjects;
    @FindBy(xpath = "//tr[@data-permission-key='CREATE_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    public WebElement createIssues;
    @FindBy(xpath = "//tr[@data-permission-key='EDIT_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    public WebElement editIssues;
    @FindBy(xpath = "//tr[@data-permission-key='VIEW_GLASS_DOCUMENTATION']/descendant::td/descendant::dl/descendant::dd")
    public WebElement deleteIssues;
    @FindBy(xpath = "//tr[@data-permission-key='DELETE_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    public WebElement viewGlassDocumentation;
    @FindBy(xpath = "//tr[@data-permission-key='RESOLVE_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    public WebElement resolveIssues;
    @FindBy(xpath = "//tr[@data-permission-key='WORK_ON_ISSUES']/descendant::td/descendant::dl/descendant::dd")
    public WebElement workOnIssues;

    public ProjectSettingsPermissions(WebDriver driver) {
        super(driver);
    }

    public String getProjectPermission(WebElement type) {
        Actions actions = new Actions(driver);
        actions.moveToElement(type);
        actions.perform();
        return type.getText();
    }
}
