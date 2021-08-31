package page_object_model.glass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_object_model.PageBase;

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
    @FindBy(css = ".item-state-ready") private List<WebElement> rows;
    @FindBy(css = ".details-button") private WebElement detailsButton;
    @FindBy(css = ".project-config-operations-unarchive") private WebElement unarchiveButton;
    @FindBy(css = ".project-config-operations-delete") private WebElement deleteButton;
    @FindBy(id = "submit") private WebElement submitButton;



    public GlassVersion(WebDriver driver) {
        super(driver);
    }

    public void addNewVersion() {
        sendMessage(versionName, "test");
        sendMessage(startDate, "6/aug/21");
        sendMessage(releaseDate, "1/aug/21");
        sendMessage(description, "test_description");
        sendKey(releaseDate, Keys.RETURN);
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
        for (WebElement row : rows) {
            if (getText(row.findElement(By.xpath("//tr//a[.='" + versionName + "']"))).equals(versionName)) {
                clickOn(detailsButton);
                break;
            }
        }
    }
}
