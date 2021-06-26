package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {

	By myAccountDropDown = By.cssSelector("div.dropdown-login");
	By signUpButton = By.xpath("//a[text()=\"Sign Up\"]");
	By loginButton = By.xpath("//a[text()=\"Login\"]");

	public HomePage(WebDriver driver) {
		super(driver, HOMEPAGENAME);
	}
	
	public void clickMyAccountButton() {
		clickButton(myAccountDropDown);
	}

	public RegisterPage clickSignUpButton() {
		clickButton(signUpButton);
		return new RegisterPage(getDriver(),REGISTERPAGENAME);
	}

	public LoginPage clickLoginButton() {
		clickButton(loginButton);
		return new LoginPage(getDriver(),LOGINPAGENAME);
	}

}
