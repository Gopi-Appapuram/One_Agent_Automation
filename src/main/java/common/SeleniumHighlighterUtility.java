package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Utility class to highlight elements on a web page using JavaScript.
 */
public class SeleniumHighlighterUtility {

	WebDriver driver;
	JavascriptExecutor js;

	/**
	 * Constructor to initialize the SeleniumHighlighterUtility with the WebDriver
	 * instance.
	 *
	 * @param driver The WebDriver instance to use for highlighting elements.
	 */
	public SeleniumHighlighterUtility(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	/**
	 * Highlights the given WebElement using a default green border.
	 *
	 * @param element The WebElement to highlight.
	 */
	public void highlightElement(WebElement element) {
		// Execute JavaScript to highlight the element
		js.executeScript("arguments[0].style.border = '2px solid Red'", element);

		// Sleep for a short while to see the highlight (optional)
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's border after highlighting
		js.executeScript("arguments[0].style.border = ''", element);
	}

	/**
	 * Highlights the given WebElement using the specified CSS border style.
	 *
	 * @param element        The WebElement to highlight.
	 * @param highlightStyle The CSS border style to use for highlighting Eg: "2px solid Red".
	 */
	public void highlightElementWithSpecifiedBodderAndColour(WebElement element, String highlightStyle) {
		// Execute JavaScript to highlight the element
		js.executeScript("arguments[0].style.border = 'highlightStyle'", element);

		// Sleep for a short while to see the highlight (optional)
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's border after highlighting
		js.executeScript("arguments[0].style.border = ''", element);
	}

	/**
	 * Highlights a list of WebElements using a default red border.
	 *
	 * @param elements The list of WebElements to highlight.
	 */
	public void highlightElements(List<WebElement> elements) {
		for (WebElement element : elements) {
			// Execute JavaScript to highlight each element
			js.executeScript("arguments[0].style.border = '2px solid green'", element);

			// Sleep for a short while to see the highlight (optional)
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Reset each element's border after highlighting
			js.executeScript("arguments[0].style.border = ''", element);
		}
		
	}

	/**
	 * Highlights a list of WebElements using the specified CSS border style.
	 *
	 * @param elements       The list of WebElements to highlight.
	 * @param highlightStyle The CSS border style to use for highlighting.
	 */
	public void highlightElementsWithSpecifiedStyle(List<WebElement> elements, String highlightStyle) {
		for (WebElement element : elements) {
			// Execute JavaScript to highlight each element
			js.executeScript("arguments[0].style.border = 'highlightStyle'", element);

			// Sleep for a short while to see the highlight (optional)
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Reset each element's border after highlighting
			js.executeScript("arguments[0].style.border = ''", element);
		}
	}
}
