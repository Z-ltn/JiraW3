package page_object_model.glass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object_model.PageBase;
import util.Util;

import java.util.List;

import static keyword.Keyword.*;

public class GlassVersion extends PageBase {
    @FindBy(xpath = "//input[@name='name']") private WebElement versionName;
    @FindBy(xpath = "//input[@name='startDate']") private WebElement startDate;
    @FindBy(xpath = "//input[@name='releaseDate']") private WebElement releaseDate;
    @FindBy(xpath = "//input[@name='description']") private WebElement description;
    @FindBy(xpath = "//button[@class='aui-button aui-button-primary']") private WebElement addButton;
    @FindBy(css = ".error") private WebElement errorMessage;
    @FindBy(xpath = "//a[@data-version-type='released']") private WebElement releasedButton;
    @FindBy(xpath = "//a[@data-version-type='archived']") private WebElement archivedButton;
    @FindBy(css = ".project-config-operations-archive") private WebElement archiveButton;
    @FindBy(css = ".project-config-operations-unarchive") private WebElement unarchiveButton;
    @FindBy(css = ".project-config-operations-delete") private WebElement deleteButton;
    @FindBy(css = ".project-config-operations-release") private WebElement releaseButton;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "project-config-version-release-form-submit") private WebElement releaseSubmitButton;
    @FindBy(css = ".version-edit-dialog") private WebElement editButton;
    @FindBy(id = "version-name") private WebElement editNameField;
    @FindBy(id = "version-save-submit") private WebElement editSaveButton;

    public GlassVersion(WebDriver driver) {
        super(driver);
    }

    public void addNewVersionWithWrongReleaseDate() {
        sendMessage(versionName, "test");
        sendMessage(startDate, "6/aug/21");
        sendMessage(releaseDate, "1/aug/21");
        sendMessage(description, "test_description");
        sendKey(releaseDate, Keys.RETURN);
    }

    public void addNewVersion(String versionName) {
        sendMessage(this.versionName, versionName);
        clickOn(addButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void changeReleaseDate() {
        releaseDate.clear();
        sendMessage(releaseDate, "10/aug/21");
        clickOn(addButton);
    }

    public void deleteVersion(String versionName, boolean isArchived) {
        clickOn(releasedButton);
        clickOn(archivedButton);
        clickMenuButtonOnVersion(versionName);
        if (isArchived) clickOn(unarchiveButton);
        refresh();
        clickMenuButtonOnVersion(versionName);
        clickOn(deleteButton);
        clickOn(submitButton);
    }

    private void clickMenuButtonOnVersion(String versionName) {
        WebElement table = driver.findElement(By.cssSelector(".ui-sortable"));
        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        for (WebElement row : rows) {
            if (getText(row.findElement(By.xpath("//tr//a[.='" + versionName + "']"))).equals(versionName)) {
                clickOn(driver.findElement(By.cssSelector(".details-button")));
                break;
            }
        }
    }

    public void releaseVersion(String versionName) {
        clickMenuButtonOnVersion(versionName);
        clickOn(releaseButton);
        clickOn(releaseSubmitButton);
    }

    public void editVersion(String versionName, String newName) {
        clickMenuButtonOnVersion(versionName);
        clickOn(editButton);
        sendMessage(editNameField, newName);
        clickOn(editSaveButton);
        Util.wait(driver, TIME).until(ExpectedConditions.visibilityOf(addButton));
    }

    public void archiveVersion(String versionName) {
        clickMenuButtonOnVersion(versionName);
        clickOn(archiveButton);
    }
}
