package pages;

import java.awt.AWTException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RegisterPage extends AbstractPage {

	By firstNameField = By.name("firstname");
	By lastNameField = By.name("lastname");
	By phoneField = By.name("phone");
	By emailField = By.name("email");
	By passwordField = By.name("password");
	By confirmPasswordField = By.name("confirmpassword");

	By signUpBtn = By.cssSelector("#headersignupform > div > button");
	public By alertMsg = By.className("alert-danger");

	public RegisterPage(WebDriver driver,String pageName) {
		super(driver, pageName);
	}

	private void setField(By fieldName, String fieldText) {
		getDriver().findElement(fieldName).sendKeys(fieldText);
	}

	public void setFirstName(String text) {
		setField(firstNameField, text);
	}

	public void setLastName(String text) {
		setField(lastNameField, text);
	}

	public void setPhone(String text) {
		setField(phoneField, text);
	}

	public void setEmail(String text) {
		setField(emailField, text);
	}

	public void setPassword(String text) {
		setField(passwordField, text);
	}

	
	public void setConfirmPassword(String text) {
		setField(confirmPasswordField, text);
	}
	
	public AccountPage clickSignup() {
		WebElement signUpElement = getDriver().findElement(signUpBtn);
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(signUpBtn));
		clickButton(signUpElement);
		return new AccountPage(getDriver(),ACCOUNTPAGENAME);
	}
	
	public void scrollToAlertMsg() {
		WebElement alertElem = getDriver().findElement(alertMsg);
		scrollTo(alertElem);
	}
	
	
	public String getAlertMsgText() {
//		try {
//				getWait().until(ExpectedConditions.presenceOfElementLocated(alertMsg));
//			} catch (Exception e) {
//			throw new AssertionError(
//					"WebDriver couldn’t locate the element", e);
//			}
		return getDriver().findElement(alertMsg).getText();
	}
	

	public boolean isSamePage() {
		WebElement signUpElement = getDriver().findElement(signUpBtn);
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(signUpBtn));
		ExpectedConditions.invisibilityOf(signUpElement);
		return  signUpElement.isDisplayed();
	}

}
