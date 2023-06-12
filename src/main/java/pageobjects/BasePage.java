package pageobjects;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static utils.GlobalVars.BASE_URL;
import static utils.GlobalVars.DEFAULT_EXPLICIT_TIMEOUTS;

public class BasePage {
    public WebDriverWait wait;

    @FindBy(xpath = "//a[@title='Home menu']")
    WebElement backToHomePageButton;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_EXPLICIT_TIMEOUTS));
    }

    public WebDriver getDriver() {
        return DriverFactory.getWebDriver();
    }

    protected void navigateToPath(String path) {
        getDriver().get(BASE_URL + path);
    }

    protected void navigateToUrl(String url) {
        getDriver().get(url);
    }

    protected WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElementList) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
    }


    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitAndClick(WebElement element) {
        WebElement webElement = waitForElementToBeClickable(element);
        webElement.click();
    }

    protected void waitAndSendKeys(WebElement element, String keys) {
        WebElement webElement = waitForVisibilityOf(element);
        webElement.clear();
        webElement.sendKeys(keys);
    }

    protected String waitAndGetText(WebElement element) {
        WebElement webElement = waitForVisibilityOf(element);
        return webElement.getText();
    }

    public void clickBackToHomePageButton() {
        waitAndClick(backToHomePageButton);
    }

    protected void selectHeaderDropdownListByName(WebElement dropdownButton, String selectValue) {
        waitAndClick(dropdownButton);
        By optionXpath = By.xpath("//button[contains(@aria-expanded,'true')]/following-sibling::div/a");
        List<WebElement> options = waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        for (WebElement option : options
        ) {
            if (option.getText().compareToIgnoreCase(selectValue) == 0) {
                waitAndClick(option);
                break;
            }

        }
    }

    protected void searchAndSelectComboBoxByIndex(WebElement comboBoxTextbox, String searchValue) {
        WebElement comboBoxTextboxElement = waitForVisibilityOf(comboBoxTextbox);
        comboBoxTextboxElement.sendKeys(searchValue);
        By optionXpath = By.xpath("//ul[contains(@style,'display: block')]/li");
        List<WebElement> options = waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        waitAndClick(options.get(0));
    }

    protected void searchAndSelectComboBoxByIndex(WebElement comboBoxTextbox, String searchValue, int index) {
        WebElement comboBoxTextboxElement = waitForVisibilityOf(comboBoxTextbox);
        comboBoxTextboxElement.sendKeys(searchValue);
        By optionXpath = By.xpath("//ul[contains(@style,'display: block')]/li");
        List<WebElement> options = waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        waitAndClick(options.get(index));
    }

}
