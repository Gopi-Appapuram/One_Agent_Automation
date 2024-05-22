package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Gopi Appapuram
 * 
 * Utility class to capture screenshots in WebDriver tests.
 */
public class ScreenshotUtility {

	private WebDriver driver;

	/**
	 * Constructor to initialize ScreenshotUtility with the WebDriver instance.
	 *
	 * @param driver The WebDriver instance to use for taking screenshots.
	 */
	public ScreenshotUtility(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Captures a screenshot of the current page and saves it with the specified
	 * file name.
	 *
	 * @param fileName The name to give to the screenshot file.
	 */
	public void takeScreenshot(String fileName) {
		try {
			// Capture screenshot of the current page
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(
					"D:\\ESoft_Solutions\\AutomationPractice\\BDD_Framework\\ScreenShots\\" + fileName + ".png"));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}

	/**
	 * Captures a screenshot of the specified WebElement and saves it with the
	 * specified file name.
	 *
	 * @param element  The WebElement to capture.
	 * @param fileName The name to give to the screenshot file.
	 */
	public void takeScreenshotOfElement(WebElement element, String fileName) {
		try {
			// Capture screenshot of the specific element
			File scrFile = element.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fileName + ".png"));
		} catch (IOException e) {
			System.out.println("Exception while taking element screenshot: " + e.getMessage());
		}
	}

	/**
	 * Captures a screenshot of the specified list of WebElements and saves it with
	 * the specified file name.
	 *
	 * @param elements The list of WebElements to capture.
	 * @param fileName The name to give to the screenshot file.
	 */
	public void takeScreenshotOfElements(List<WebElement> elements, String fileName) {
	    try {
	        for (int i = 0; i < elements.size(); i++) {
	            WebElement element = elements.get(i);
	            File scrFile = element.getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(scrFile, new File(fileName + "_" + i + ".png"));
//	            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	            FileUtils.copyFile(screenshot, new File(fileName + "_" + i + ".png"));

	        }
	    } catch (IOException e) {
	        System.out.println("Exception while taking element screenshot: " + e.getMessage());
	    }
	}

}
