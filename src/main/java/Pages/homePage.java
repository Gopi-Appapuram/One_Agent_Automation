package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class homePage extends loginPage {
    WebDriver driver = null;

    private final By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By searchButton = By.id("nav-search-submit-button");

    public homePage(WebDriver driver) {
        super(driver);
    }

    public void isHomePageDisplayed() {
        String homePageTitle = getDriver().getTitle();
        assertEquals(homePageTitle, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

    public void enterSearchTerm(String searchTerm) {
        insertText(searchBox, searchTerm);
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }
}
