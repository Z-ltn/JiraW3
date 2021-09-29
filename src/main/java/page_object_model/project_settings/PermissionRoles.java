package page_object_model.project_settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum PermissionRoles {
    browseProjects("Browse Projects", "BROWSE_PROJECTS"),
    createIssues("Create Issues", "CREATE_ISSUES"),
    editIssues("Edit Issues", "EDIT_ISSUES"),
    deleteIssues("Delete Issues", "DELETE_ISSUES"),
    viewGlassDocumentation("View Glass Documentation", "GLASS_VIEW"),
    resolveIssues("Resolve Issues", "RESOLVE_ISSUES"),
    workOnIssues("Work On Issues", "WORK_ON_ISSUES");
    private final String title;
    private String xpath;
    private final String key;
    private final String baseURL = System.getProperty("baseURL");

    PermissionRoles(String title, String key) {
        this.title = title;
        this.key = key;
        this.xpath = "//tr[@data-permission-key='" + key + "']/descendant::td/descendant::dl/descendant::dd";
    }

    public WebElement getElement(WebDriver driver) {
        driver.get(baseURL + "/plugins/servlet/project-config/PP/permissions");
        return driver.findElement(By.xpath(xpath));
    }

    public String getTitle() {
        return title;
    }
}
