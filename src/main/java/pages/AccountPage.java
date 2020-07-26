package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends AbstractPage {

	private By welcomeMessage = By.cssSelector("h3.text-align-left");
	private By myAccountDropdown = By.cssSelector("div.dropdown-login a.btn-text-inherit");
	private By logoutLink = By.linkText("Logout");

	public AccountPage(WebDriver driver) {
		super(driver);
	}

	public String getWelcomeMessage() {
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(welcomeMessage));
		return getDriver().findElement(welcomeMessage).getText();
	}

	public LoginPage logout() {
		getDriver().findElement(myAccountDropdown).click();
		getDriver().findElement(logoutLink).click();
		return new LoginPage(getDriver());
	}

	public String getTitle() {
		getWait().until(ExpectedConditions.titleContains("Account"));
		return getDriver().getTitle();
	}
}
