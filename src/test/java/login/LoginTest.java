package login;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	private LoginPage loginPage;
	private AccountPage accountPage;

	@Test
	public void enterLoginPage() {
		homePage.clickMyAccountButton();
		loginPage = homePage.clickLoginButton();
	}

	@Test(dependsOnMethods = "enterLoginPage")
	public void setLoginCredintials() {
		loginPage.setEmail(dataReader.map.get("email"));
		loginPage.setPassword(dataReader.map.get("password"));
		accountPage = loginPage.clickLoginBtn();
	}

	@Test(dependsOnMethods = "setLoginCredintials")
	public void validateSuccessfulLogin() {
		assertEquals(accountPage.getWelcomeMessage(),
				"Hi, " + dataReader.map.get("firstname") + " " + dataReader.map.get("lastname"),
				"Wrong message displayed!" + accountPage.getWelcomeMessage());
	}

}
