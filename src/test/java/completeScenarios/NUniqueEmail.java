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
import io.qameta.allure.Description;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class NUniqueEmail extends BaseTest {

	RegisterPage registerPage;
	AccountPage accountPage;
	HomePage homePage;
	LoginPage loginPage;
	
	@BeforeClass
	public void initJsonReader() throws FileNotFoundException, IOException, ParseException {
		dataReader = new JsonDataReader();
		dataReader.JsonReader("n_unique_email");
	}
	
	@Test
	@Description("Test all fields are valid but the email is already registered. 1. create an account 1st time")
	public void precondition() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountButton();
		registerPage = homePage.clickSignUpButton();
		assertEquals(registerPage.getTitle(), "Register");
		registerPage.setFirstName(dataReader.map.get("firstname"));

		registerPage.setLastName(dataReader.map.get("lastname"));
		registerPage.setPhone(dataReader.map.get("phone"));
		registerPage.setEmail(dataReader.map.get("email"));
		registerPage.setPassword(dataReader.map.get("password"));
		registerPage.setConfirmPassword(dataReader.map.get("confirm_password"));
		
		accountPage = registerPage.clickSignup();
		assertEquals(accountPage.getTitle(), "My Account");
		assertTrue(accountPage.getWelcomeMessage().endsWith(dataReader.map.get("lastname")),
				"Welcome message is not correct!");
		//logout
		loginPage = accountPage.logout();
		assertEquals(loginPage.getTitle(), "Login");
	
	}
	
	@Test(dependsOnMethods = "precondition")
	@Description("Test all fields are valid but the email is already registered. 2. Open the sign up page...")
	public void enterRegisterationPage() {
		registerPage = loginPage.clickSignupBtn();
		assertEquals(registerPage.getTitle(), "Register");
	}

	@Test(dependsOnMethods = "enterRegisterationPage")
	@Description("Test all fields are valid but the email is already registered. 3. Enter the same sign up data...")
	public void setNewUserData() throws IOException {
		
		registerPage.setFirstName(dataReader.map.get("firstname"));
		registerPage.setLastName(dataReader.map.get("lastname"));
		registerPage.setPhone(dataReader.map.get("phone"));
		registerPage.setEmail(dataReader.map.get("email"));
		registerPage.setPassword(dataReader.map.get("password"));
		registerPage.setConfirmPassword(dataReader.map.get("confirm_password"));
	
		accountPage = registerPage.clickSignup();
	}

	@Test(dependsOnMethods = "setNewUserData")
	@Description("Test all fields are valid but the email is already registered. 4. Verify unsuccessful registeration...")
	public void verifyUnSuccessfulRegisteration() {
		assertEquals(registerPage.getTitle(), "Register");
		assertEquals(registerPage.getAlertMsgText(), "Email Already Exists.");
	}


}
