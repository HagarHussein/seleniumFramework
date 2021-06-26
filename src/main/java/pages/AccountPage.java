package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends AbstractPage {

	By welcomeMessage = By.cssSelector("h3.text-align-left");
	By myAccountDropdown = By.cssSelector("div.dropdown-login a.btn-text-inherit");
	By logoutLink = By.linkText("Logout");
	By alertMsg = By.className("alert-danger");
	boolean valid = false;
	
	public AccountPage(WebDriver driver, String pageName) {
		super(driver,pageName);
	}

	public String getWelcomeMessage() {
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(welcomeMessage));
		return getDriver().findElement(welcomeMessage).getText();
	}

	public LoginPage logout() {
		getDriver().findElement(myAccountDropdown).click();
		getDriver().findElement(logoutLink).click();
		return new LoginPage(getDriver(),LOGINPAGENAME);
	}

	public String getAlertMsgText() {
		return getDriver().findElement(alertMsg).getText();
	}
	
	public String getURL() {
		return getDriver().getCurrentUrl();
	}
	
	public void setValid(boolean flag ) {
		this.valid = flag;
	}
	
	public boolean getValid() {
		return this.valid;
	}
}
