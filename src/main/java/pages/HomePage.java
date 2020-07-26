package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {

	private By myAccountDropDown = By.cssSelector("div.dropdown-login");
	private By signUpButton = By.xpath("//a[text()=\"Sign Up\"]");
	private By loginButton = By.xpath("//a[text()=\"Login\"]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickMyAccountButton() {
		clickButton(myAccountDropDown);
	}

	public RegisterPage clickSignUpButton() {
		clickButton(signUpButton);
		return new RegisterPage(getDriver());
	}

	public LoginPage clickLoginButton() {
		clickButton(loginButton);
		return new LoginPage(getDriver());
	}

	// Helper Function to be used inside this class
	private void clickButton(By buttonidentifier) {
		try {
			getWait().until(ExpectedConditions.presenceOfElementLocated(buttonidentifier));
			try {
				getDriver().findElement(buttonidentifier).click();
			} catch (Exception e) {
				throw new AssertionError(
						"There is no such element with the xPath: //a[text()=\\\"Login\\\"] or the element is not clickable.",
						e);
			}
		} catch (TimeoutException e) {
			throw new AssertionError(
					"Timeout to the presence of the element with the xPath: //a[text()=\\\"Login\\\"] is reached.", e);
		}
	}
}
