package page_object_model.glass;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

import static keyword.Keyword.*;

public class Components extends PageBase {
    @FindBy(xpath = "//*[@id=\"components-add__component\"]/div[1]/input") private WebElement componentNameField;
    @FindBy(id = "assigneeType-field") private WebElement assigneeField;
    @FindBy(xpath = "//*[@id=\"components-add__component\"]/div[5]/button") private WebElement addComponentButton;
    @FindBy(css = "#components-table > tbody.items > tr:first-child > td:last-child > div > a") private WebElement actionsButton;
    @FindBy(linkText = "Edit") private WebElement editButton;
    @FindBy(linkText = "Delete") private WebElement deleteButton;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "component-name") private WebElement editComponentNameField;
    @FindBy(id = "component-save-submit") private WebElement editComponentSubmitButton;
    @FindBy(css = "#components-table > tbody > tr:first-child > td:first-child > div") private WebElement glassComponentName;
    @FindBy(xpath = "//*[@id=\"user-roles-page-container\"]/div[1]/div[1]/div[2]/div/div[2]/button") private WebElement editDefaultsButton;
    @FindBy(id = "select-role") private WebElement defaultAssigneeField;
    @FindBy(id = "react-select-3-input") private WebElement defaultAssigneeInput;
    @FindBy(xpath = "//*[@id=\"glass-general-panel\"]/div[1]/div[1]/div/table/tbody/tr[6]/td[2]/span/a") private WebElement defaultAssigneeRole;
    @FindBy(xpath = "//*[@id=\"glass-general-panel\"]/div[1]/div[1]/div/table/tbody/tr[6]/td[2]") private WebElement defaultAssigneeRole2;
    @FindBy(xpath = "//*[@id='jira']/div[2]/div[2]/div/div[3]/div[2]/div/div/footer/div/div[1]/button/span/span") private WebElement updateButton;

    public Components(WebDriver driver) {
        super(driver);
    }

    public void createComponent(String componentName, String assigneeName) {
        sendMessage(componentNameField, componentName);
        sendMessage(assigneeField, assigneeName);
        sendKey(assigneeField, Keys.RETURN);
        clickOn(addComponentButton);
    }

    public void editComponent(String editComponentName) {
        clickOn(actionsButton);
        clickOn(editButton);
        sendMessage(editComponentNameField, editComponentName);
        clickOn(editComponentSubmitButton);
        Util.wait(driver, 5).until(ExpectedConditions.elementToBeClickable(assigneeField));
    }

    public String getGlassComponentName() {
        return getText(glassComponentName);
    }

    public void editDefaults(String defaultAssignee) {
        clickOn(editDefaultsButton);
        clickOn(defaultAssigneeField);
        sendMessage(defaultAssigneeInput, defaultAssignee);
        sendKey(defaultAssigneeInput, Keys.RETURN);
        clickOn(updateButton);
    }

    public String getDefaultAssigneeRole() {
        try {
            return getText(defaultAssigneeRole);
        }
        catch (NoSuchElementException ignored) {
            return getText(defaultAssigneeRole2);
        }
    }

    public void deleteComponent() {
        openURL("https://jira-auto.codecool.metastage.net/projects/PP?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        clickOn(actionsButton);
        clickOn(deleteButton);
        clickOn(submitButton);
    }
}
