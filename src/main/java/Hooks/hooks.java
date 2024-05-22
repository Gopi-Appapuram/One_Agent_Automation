package Hooks;


import base.BasePage;
import base.BaseTest;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class hooks extends BasePage{

    /**
     * Constructor to initialize the BasePage with WebDriver instance.
     *
     * @param driver WebDriver instance
     */
    public hooks(WebDriver driver) {
        super(driver);
    }

    @AfterStep
    public void takeScreenShot(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

        if (scenario.isFailed()) {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName().replace(" ", "")+"failed.png");
        }
    }

}
