package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

	private By emailField = By.cssSelector("#loginfrm input[type=email]");
	private By passwordField = By.cssSelector("#loginfrm input[type=password]");
	private By loginBtn = By.cssSelector("button.loginbtn"); // ("#loginfrm button[type=submit]");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setEmail(String email) {
		getDriver().findElement(emailField).sendKeys(email);
	}

	public void setPassword(String password) {
		getDriver().findElement(passwordField).sendKeys(password);
	}

	public AccountPage clickLoginBtn() {
		getDriver().findElement(loginBtn).click();
		return new AccountPage(getDriver());
	}

	public String getTitle() {
		getWait().until(ExpectedConditions.titleContains("Login"));
		return getDriver().getTitle();
	}
}
