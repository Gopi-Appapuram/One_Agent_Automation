package Pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class loginPage extends BasePage {

    WebDriver driver = null;

    // Locators
    private final By emailInput = By.xpath("//input[@id ='ap_email']");
    private final By emailContinueButton = By.id("continue");
    private final By passwordInput = By.id("ap_password");
    private final By signInSubmitButton = By.id("signInSubmit");
    private final By welcomeMessage = By.id("nav-link-accountList-nav-line-1");
    private final By errorMessage = By.id("auth-error-message-box");


    public loginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) throws InterruptedException {
        insertText(emailInput, email);
    }

    public void clickContinue() {
        clickElement(emailContinueButton);
    }

    public void enterPassword(String password) {
        insertText(passwordInput, password);
    }

    public void clickSignInSubmit() {
        clickElement(signInSubmitButton);
    }

    public boolean isWelcomeMessageDisplayed() {
        boolean isDisplayed = false;
        try {
            if (getElement(welcomeMessage).isDisplayed()) {
                String welcomeMessageText = getElement(welcomeMessage).getText();
                System.out.println(welcomeMessageText);
                isDisplayed = true;
            } else {
                System.out.println("Welcome message not found");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Welcome message element not found: " + e.getMessage());
        }
        return isDisplayed;
    }


    public boolean isErrorMessageDisplayed() {
        boolean isDisplayed = false;
        try {
            if (getElement(errorMessage).isDisplayed()) {
                String ErrorMessage = getElement(errorMessage).getText();
                System.out.println(ErrorMessage);
                isDisplayed = true;
            } else {
                System.out.println("Message not found");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error message element not found: " + e.getMessage());
        }
        return isDisplayed;
    }
}
