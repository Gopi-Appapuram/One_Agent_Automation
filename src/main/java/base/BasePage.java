package base;

import java.io.File;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.zeroturnaround.zip.ZipUtil;

public class BasePage {

    protected WebDriver driver = null;

    WebDriverWait wait;
    JavascriptExecutor js;
    Duration explicitWait = Duration.ofSeconds(30);

    /**
     * Constructor to initialize the BasePage with WebDriver instance.
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Returns the WebDriver instance.
     *
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Finds and returns the WebElement based on the provided By locator.
     * Highlights the element by adding a red border.
     *
     * @param by By locator to find the element
     * @return WebElement found by the locator
     */
    protected WebElement getElement(By by) {
        wait = new WebDriverWait(driver, explicitWait);
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(by));
        return driver.findElement(by);
    }

    protected void insertText(By by, String value) {
        getElement(by).sendKeys(value);
    }

    protected void clearText(By by) {
        WebElement element = getElement(by);
        element.clear();
    }


    /**
     * Clicks on the WebElement identified by the provided By locator.
     *
     * @param by By locator to find the element to be clicked
     */
    protected void clickElement(By by) {
        WebElement element = getElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Switches to the window specified by index.
     *
     * @param windowIndex Index of the window to switch to
     */
    protected void switchToWindowByIndex(int windowIndex) {
        Set<String> winHandlers = driver.getWindowHandles();
        driver.switchTo().window(winHandlers.toArray()[windowIndex].toString());
    }

    /**
     * Selects the dropdown option by visible text.
     *
     * @param by          By locator to find the dropdown
     * @param optionTitle Visible text of the option to be selected
     */
    protected void selectDropdownOptionByVisibleText(By by, String optionTitle) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(optionTitle);
    }

    /**
     * Selects the dropdown option by index.
     *
     * @param by    By locator to find the dropdown
     * @param index Index of the option to be selected
     */
    protected void selectDropdownOptionByIndex(By by, int index) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
    }

    /**
     * Selects the dropdown option by value.
     *
     * @param by    By locator to find the dropdown
     * @param value Value of the option to be selected
     */
    protected void selectDropdownOptionByValue(By by, String value) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    /**
     * Deselects the dropdown option by visible text.
     *
     * @param by          By locator to find the dropdown
     * @param optionTitle Visible text of the option to be deselected
     */
    protected void deSelectDropdownOption(By by, String optionTitle) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.deselectByVisibleText(optionTitle);
    }

    /**
     * Deselects the dropdown option by index.
     *
     * @param by    By locator to find the dropdown
     * @param index Index of the option to be deselected
     */
    protected void deselectDropdownOptionByIndex(By by, int index) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.deselectByIndex(index);
    }

    /**
     * Deselects the dropdown option by value.
     *
     * @param by    By locator to find the dropdown
     * @param value Value of the option to be deselected
     */
    protected void deselectDropdownOptionByValue(By by, String value) {
        WebElement element = getElement(by);
        Select dropdown = new Select(element);
        dropdown.deselectByValue(value);
    }

    /**
     * Scrolls to the WebElement identified by the provided By locator.
     *
     * @param by By locator to find the element to be scrolled to
     */
    protected void scrollToElement(By by) {
        WebElement element = getElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hovers the mouse pointer over a specified element.
     *
     * @param by The locator used to find the element.
     */
    protected void hoverToElement(By by) {
        Actions actions = new Actions(driver);
        WebElement element = getElement(by);
        actions.moveToElement(element).perform();
    }

    /**
     * Waits until the specified element is visible on the page.
     *
     * @param timeInSeconds The maximum duration to wait for the element to be visible.
     * @param locator       The locator used to find the element.
     */
    protected void waitUntilVisibilityOfElement(Duration timeInSeconds, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the specified element is clickable on the page.
     *
     * @param timeInSeconds The maximum duration to wait for the element to be clickable.
     * @param locator       The locator used to find the element.
     */
    protected void waitUntilElementToBeClickable(Duration timeInSeconds, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the specified element is selected on the page.
     *
     * @param timeInSeconds The maximum duration to wait for the element to be selected.
     * @param locator       The locator used to find the element.
     */
    protected void waitUntilElementToBeSelected(Duration timeInSeconds, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    /**
     * Sets the implicit wait time for the WebDriver.
     * Implicit wait is used to specify the amount of time the WebDriver should wait
     * when searching for an element if it is not immediately present.
     *
     * @param timeInSeconds The time to wait in seconds.
     */
    protected void setImplicitWait(int timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    /**
     * Zips the contents of a folder and saves the zip file to the specified destination.
     *
     * @param sourcePath      The path to the folder to be zipped.
     * @param destinationPath The path where the zip file should be saved.
     */
    public static void zipFolder(String sourcePath, String destinationPath) {
        ZipUtil.pack(new File(sourcePath), new File(destinationPath + ".zip"));
    }


}
