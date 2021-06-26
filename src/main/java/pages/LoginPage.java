package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

	By emailField = By.cssSelector("#loginfrm input[type=email]");
	By passwordField = By.cssSelector("#loginfrm input[type=password]");
	By loginBtn = By.cssSelector("button.loginbtn"); // ("#loginfrm button[type=submit]");
	By signupBtn = By.cssSelector("a.btn-success");
	
	
	public LoginPage(WebDriver driver, String pageName) {
		super(driver,pageName);
	}

	public void setEmail(String email) {
		getDriver().findElement(emailField).sendKeys(email);
	}

	public void setPassword(String password) {
		getDriver().findElement(passwordField).sendKeys(password);
	}

	public AccountPage clickLoginBtn() {
		WebElement loginElement = getDriver().findElement(loginBtn);
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(loginBtn));
		clickButton(loginElement);
		AccountPage accountPage = new AccountPage(getDriver(),ACCOUNTPAGENAME);
		return accountPage;
	}
	
	public RegisterPage clickSignupBtn() {
		clickButton(signupBtn);
		return new RegisterPage(getDriver(),REGISTERPAGENAME);
	}
	
	

	public String getTitle() {
		getWait().until(ExpectedConditions.titleContains("Login"));
		return getDriver().getTitle();
	}
}
