package pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	static final int TIMEOUT = 10;
	public static String HOMEPAGENAME = "PHPTRAVELS | Travel Technology Partner";
	public static String REGISTERPAGENAME = "Register";
	public static String LOGINPAGENAME = "Login";
	public static String ACCOUNTPAGENAME = "My Account";

	boolean valid = false;
	static WebDriver driver;
	WebDriverWait wait;

	public AbstractPage(WebDriver driver,String pageName) {
		AbstractPage.driver = driver;
		//this.wait = new WebDriverWait(driver, TIMEOUT);
		if (getFluentWait(ExpectedConditions.titleContains(pageName)))
			this.valid = true;
		else
			this.valid = false;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	
	public WebDriverWait getWait() {
		return wait;
	}
	
	
	public boolean getFluentWait(final ExpectedCondition<Boolean> expectedCondition) {
	
		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(getDriver()).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);
		try {
			fluentWait.until(expectedCondition);
			} 
		catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	
	public void clickButton(WebElement elem) {
		try {
			try {
				elem.click();
			} catch (Exception e) {
				throw new AssertionError(
						"There is no such element or the element is not clickable.", e);
			}
		} catch (TimeoutException e) {
			throw new AssertionError(
					"Timeout to the presence of the element is reached.", e);
		}
	}
	
	// Function overload
	public void clickButton(By elemLocator) {
		try {
			try {
				getDriver().findElement(elemLocator).click();
			} catch (Exception e) {
				throw new AssertionError(
						"There is no such element or the element is not clickable.", e);
			}
		} catch (TimeoutException e) {
			throw new AssertionError(
					"Timeout to the presence of the element is reached.", e);
		}
	}
	
	public void scrollTo(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(" + elem.getLocation().x + "," + (elem.getLocation().y - 100) + ");");
	}
	
	public String getTitle() {
		return getDriver().getTitle();
	}
	
	public boolean getValid() {
		return this.valid;
	}

}
