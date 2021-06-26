package completeScenarios;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import data.JsonDataReader;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class PosTest extends BaseTest {

	private RegisterPage registerPage;
	private AccountPage accountPage;
	private HomePage homePage;
	private LoginPage loginPage;

	@BeforeClass
	public void initJsonReader() throws FileNotFoundException, IOException, ParseException {
		dataReader = new JsonDataReader();
		dataReader.JsonReader("pos_test");
	}
	
	@Test
	public void enterRegisterationPage()
	{
		homePage = new HomePage(driver);
		homePage.clickMyAccountButton();
		registerPage = homePage.clickSignUpButton();
		assertEquals(registerPage.getTitle(), "Register");
	}

	@Test(dependsOnMethods = "enterRegisterationPage")
	public void setNewUserData() throws IOException {
		
		registerPage.setFirstName(dataReader.map.get("firstname"));

		registerPage.setLastName(dataReader.map.get("lastname"));
		registerPage.setPhone(dataReader.map.get("phone"));
		registerPage.setEmail(dataReader.map.get("email"));
		registerPage.setPassword(dataReader.map.get("password"));
		registerPage.setConfirmPassword(dataReader.map.get("confirm_password"));
		
		accountPage = registerPage.clickSignup();
		assertEquals(accountPage.getTitle(), "My Account");
	}

	@Test(dependsOnMethods = "setNewUserData")
	public void verifySuccessfulRegisteration() {
		assertTrue(accountPage.getWelcomeMessage().endsWith(dataReader.map.get("lastname")),
				"Welcome message is not correct!");
	}

	@Test(dependsOnMethods = "verifySuccessfulRegisteration")
	public void logout() {
		loginPage = accountPage.logout();
		assertEquals(loginPage.getTitle(), "Login");
	}

	@Test(dependsOnMethods = "logout")
	public void setLoginCredintials() {
		loginPage.setEmail(dataReader.map.get("email"));
		loginPage.setPassword(dataReader.map.get("password"));
		accountPage = loginPage.clickLoginBtn();
		
		assertEquals(accountPage.getTitle(), "My Account");
	}

	@Test(dependsOnMethods = "setLoginCredintials")
	public void validateSuccessfulLogin() {
		assertEquals(accountPage.getWelcomeMessage(),
				"Hi, " + dataReader.map.get("firstname") + " " + dataReader.map.get("lastname"),
				"Wrong message displayed!" + accountPage.getWelcomeMessage());
	}

}
