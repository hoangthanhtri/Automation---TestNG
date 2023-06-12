package pageobjects.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class LoginPage extends BasePage {

    private static final String LOGIN_PATH = "/web/login";
    @FindBy(id = "login")
    WebElement emailTextbox;
    @FindBy(id = "password")
    WebElement passwordTextbox;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginButton;

    public LoginPage() {
        super();
    }

    public void navigateToLoginPage() {
        navigateToPath(LOGIN_PATH);
    }

    public void inputEmailTextbox(String inputValue) {
        waitAndSendKeys(emailTextbox, inputValue);
    }

    public void inputPasswordTextbox(String inputValue) {
        waitAndSendKeys(passwordTextbox, inputValue);
    }

    public void clickLoginButton() {
        waitAndClick(loginButton);
    }

}
