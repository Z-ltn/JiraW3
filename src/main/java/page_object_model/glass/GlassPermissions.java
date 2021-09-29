package page_object_model.glass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GlassPermissions extends GlassDocumentation {
    @FindBy(xpath = "//tr[@class='permtr']")
    List<WebElement> rows;
    @FindBy(className = "glass-true-icon")
    List<WebElement> ticks;

    public GlassPermissions(WebDriver driver) {
        super(driver);
    }

    public boolean arePermissionsTheSame(String type, String role) {
        String col = String.valueOf(getColumnIndex(role));
        return (boolean) js.executeScript("var targets = document.getElementsByClassName('title');" +
                "var target; for (tar of targets) {if (tar.textContent.includes(\'" + type + "\')) target = tar.parentElement.parentElement.parentElement;}" +
                "return target.children[\'" + col + "\'].childElementCount > 0;");
    }

    private String getColumnIndex(String role) {
        return (String) js.executeScript("var theads = [];" +
                "var ths = document.querySelectorAll('.rotate');" +
                "for (let i=0;i<ths.length;i++) {if (i<4) theads.push(ths[i]);}" +
                "for (let j=0;j<theads.length;j++) {" +
                "    if (theads[j].firstElementChild.firstElementChild.textContent.includes(\'" + role + "\')) return (j+1).toString();" +
                "}");
    }

    public void navigateToPermissions() {
        openURL(getBaseURL() + "/projects/PP?selectedItem=com.codecanvas.glass:glass");
        permissions.click();
    }
}
