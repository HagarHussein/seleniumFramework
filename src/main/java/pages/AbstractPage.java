package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	private static final int TIMEOUT = 10;

	private WebDriver driver;
	private WebDriverWait wait;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, TIMEOUT);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

}
