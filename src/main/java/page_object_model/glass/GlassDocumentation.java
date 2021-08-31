package page_object_model.glass;

import keyword.Keyword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

import java.util.List;

import static keyword.Keyword.*;

public class GlassDocumentation extends PageBase {
    @FindBy(id = "aui-uid-1")
    private WebElement components;
    @FindBy(id = "aui-uid-2")
    private WebElement versions;
    @FindBy(xpath = "//a[@class='header-nav-item'][text()='Permissions']")
    private WebElement permissions;
    @FindBy(xpath = "//td[@class='glass-meta-label'][text()='Default Assignee']/following-sibling::td/descendant::span/descendant::a")
    private WebElement DefaultAssignee;
    @FindBy(xpath = "//td[@class='glass-meta-label'][text()='Lead']/following-sibling::td/descendant::span/descendant::a")
    private WebElement projectLead;
    @FindBy(xpath = "//td[@class='glass-meta-label'][text()='Issue Types']/following-sibling::td")
    private WebElement issueTypesContainer;
    @FindBy(className = "versions-table__name")
    private List<WebElement> versionsTable;

    public GlassDocumentation(WebDriver driver) {
        super(driver);
    }

    public boolean doesTheVersionExists(String versionName) {
        clickOn(versions);
        boolean isFound = false;
        for (WebElement version : versionsTable) {
            if (version.getText().equals(versionName)) {
                isFound = true;
            }
        }
        return isFound;
    }

    public boolean doesTheVersionExistsByStatus(String versionName, String status) {
        clickOn(versions);
        boolean isFound = false;
        for (WebElement version : versionsTable) {
            if (getText(version).equals(versionName)) {
                if (getText(version.findElement(By.className("versions-table__status"))).equals(status)) {
                    isFound = true;
                }
            }
        }
        return isFound;
    }
}
