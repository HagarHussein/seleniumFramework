package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends AbstractPage {

	private By firstNameField = By.name("firstname");
	private By lastNameField = By.name("lastname");
	private By phoneField = By.name("phone");
	private By emailField = By.name("email");
	private By passwordField = By.name("password");
	private By confirmPasswordField = By.name("confirmpassword");

	private By signUpBtn = By.cssSelector("#headersignupform > div > button");

	public RegisterPage(WebDriver driver) {
		super(driver);
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
		setField(confirmPasswordField, text);
	}

	public AccountPage clickSignUpBtn() {
		WebElement signUpElement = getDriver().findElement(signUpBtn);
		scrollDownToSignUpBtn(signUpElement);
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(signUpBtn));
		signUpElement.click();
		return new AccountPage(getDriver());
	}

	private void scrollDownToSignUpBtn(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(" + elem.getLocation().x + "," + (elem.getLocation().y - 100) + ");");
	}

	public String getTitle() {
		getWait().until(ExpectedConditions.titleContains("Register"));
		return getDriver().getTitle();
	}

}
