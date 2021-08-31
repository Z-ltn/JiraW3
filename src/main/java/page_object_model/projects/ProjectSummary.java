package page_object_model.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

public class ProjectSummary extends PageBase {
    @FindBy(xpath = "//div[@class='aui-item project-title']/descendant::a")
    private WebElement title;
    @FindBy(xpath = "//dd[@class='project-meta-value'][1]/descendant::a")
    private WebElement lead;
    @FindBy(xpath = "//dd[@class='project-meta-value'][2]")
    private WebElement key;

    public ProjectSummary(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getLead() {
        return lead;
    }

    public WebElement getKey() {
        return key;
    }
}
