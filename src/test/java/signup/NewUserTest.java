package signup;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountPage;
import pages.RegisterPage;

public class NewUserTest extends BaseTest {
	private RegisterPage registerPage;
	private AccountPage accountPage;

	@Test
	public void enterRegisterationPage() {
		homePage.clickMyAccountButton();
		registerPage = homePage.clickSignUpButton();
	}

	@Test(dependsOnMethods = "enterRegisterationPage")
	public void setNewUserData() {
		registerPage.setFirstName(dataReader.map.get("firstname"));
		registerPage.setLastName(dataReader.map.get("lastname"));
		registerPage.setPhone(dataReader.map.get("phone"));
		registerPage.setEmail(dataReader.map.get("email"));
		registerPage.setPassword(dataReader.map.get("password"));
		accountPage = registerPage.clickSignUpBtn();
	}

	@Test(dependsOnMethods = "setNewUserData")
	public void verifySuccessfulRegisteration() {
		assertTrue(accountPage.getWelcomeMessage().endsWith(dataReader.map.get("lastname")),
				"Welcome message is not correct!");
	}

}
