package page_object_model.glass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import page_object_model.PageBase;

import java.util.List;

public class GlassIssueTypes extends PageBase {
    JavascriptExecutor js;

    public GlassIssueTypes(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
    }

    public List<String> getTypes() {
        return (List<String>) js.executeScript("let c = document.querySelector('#project-config-webpanel-summary-issuetypes > div.mod-content > div > ul'); let expected = []; for (let i=0;i<c.children.length;i++) {expected.push(c.children[i].firstElementChild.lastElementChild.textContent.trim().toLowerCase());} return expected;");
    }

    public List<String> getActualTypes() {
        return (List<String>) js.executeScript("var actual = []; var td = document.querySelector('#glass-general-panel > div.aui-group > div:nth-child(1) > div > table > tbody > tr:nth-child(8) > td.glass-meta-value'); for (let i=0;i<td.children.length;i++) {actual.push(td.children[i].getAttribute('title').split(' ')[1].toLowerCase());} actual.sort(); var res = []; actual.forEach(ele => res.push(ele.substring(0, ele.length-1))); return res;");
    }
}
