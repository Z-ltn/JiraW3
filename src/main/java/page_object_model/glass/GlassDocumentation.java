package page_object_model.glass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

import java.util.List;

import static keyword.Keyword.*;

public class GlassDocumentation extends PageBase {
    @FindBy(id = "aui-uid-1")
    protected WebElement components;
    @FindBy(id = "aui-uid-2")
    protected WebElement versions;
    @FindBy(xpath = "//a[@class='header-nav-item'][text()='Permissions']")
    protected WebElement permissions;
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

    public boolean doesTheVersionExistsByStatus(String versionName, String status) {
        clickOn(versions);
        boolean isFound = false;
        for (WebElement version : versionsTable) {
            if (getText(version).equals(versionName)) {
                if (getText(version.findElement(By.xpath("//span[.='"+ status + "']"))).equals(status.toUpperCase())) {
                    isFound = true;
                }
            }
        }
        return isFound;
    }

    public void openVersionPage(String versionName) {
        clickOn(versions);
        for (WebElement version : versionsTable) {
            if (getText(version).equals(versionName)) {
                clickOn(version.findElement(By.linkText(versionName)));
            }
        }
    }

    public void changeTab() {
        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }
}
