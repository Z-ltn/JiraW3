package page_object_model.glass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

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

    public GlassDocumentation(WebDriver driver) {
        super(driver);
    }
}
