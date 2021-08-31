package page_object_model.project_settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectSettingsUsersAndRoles extends ProjectSettingsSummary{
    @FindBy(xpath = "//span[@class='sc-dNLxif egkfiB'][text()='Project lead']/following-sibling::a")
    WebElement projectLead;
    @FindBy(xpath = "//span[@class='sc-dNLxif egkfiB'][text()='Default Assignee']/following-sibling::span")
    WebElement defaultAssignee;

    public ProjectSettingsUsersAndRoles(WebDriver driver) {
        super(driver);
    }
}
