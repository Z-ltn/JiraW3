package page_object_model.browseIssue;

import keyword.Keyword;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

import javax.xml.xpath.XPath;
import java.security.Key;

public class IssuePage extends PageBase {
    WebDriver driver;
    @FindBy(id="summary-val")@CacheLookup private WebElement summaryName;
    @FindBy(id="type-val")@CacheLookup private WebElement issueType;
    @FindBy(id="key-val")@CacheLookup private WebElement issueTypeKey;
    @FindBy(id="quickSearchInput")@CacheLookup private WebElement quickSearchInputField;
    @FindBy(xpath="//div[@class=\"no-results no-results-message\"]")@CacheLookup private WebElement NoIssueError;

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public void quickSearch(String inputString){
        Keyword.sendMessage(quickSearchInputField, inputString);
        Keyword.sendKey(quickSearchInputField, Keys.RETURN);

    }

    public String getSummaryNameText(){
        return summaryName.getText();
    }

    public String getIssueTypeText(){
        return issueType.getText();
    }

    public String getIssueTypeKeyText(){
        return issueTypeKey.getText();
    }
    public String getNoIssueErrorText() {
        return NoIssueError.getText();
    }
}
