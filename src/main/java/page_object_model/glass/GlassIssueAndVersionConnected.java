package page_object_model.glass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

import java.util.List;

import static keyword.Keyword.*;

public class GlassIssueAndVersionConnected extends PageBase {
    @FindBy(css = ".issue-summary") private List<WebElement> connectedIssues;

    public GlassIssueAndVersionConnected(WebDriver driver) {
        super(driver);
    }

    public boolean isIssueConnected(String summaryName) {
        boolean isConnected = false;
        for (WebElement issue: connectedIssues) {
            if (issue.findElement(By.xpath("//td/a[.='" + summaryName + "']")).isDisplayed()) isConnected = true;
        }
        return isConnected;
    }

    public String getIssueID(String summaryName) {
        String ticketID = "";
        for (WebElement issue: connectedIssues) {
            if (getText(issue.findElement(By.xpath("//td/a[@class='issue-summary']"))).equals(summaryName)) {
                ticketID = getText(issue.findElement(By.xpath("//td/a[@class='issue-key']")));
            }
        }
        return ticketID;
    }
}
