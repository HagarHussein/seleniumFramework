package completeScenarios;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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
import pages.RegisterPage;

public class NEmailEmp extends BaseTest {

	private RegisterPage registerPage;
	private AccountPage accountPage;
	public HomePage homePage; 

	@BeforeClass
	public void initJsonReader() throws FileNotFoundException, IOException, ParseException {
		dataReader = new JsonDataReader();
		dataReader.JsonReader("n_email_emp");
	}
	
	@Test
	@Description("Test all fields are valid except for email field empty. /n 1. Open the sign up page...")
	public void enterRegisterationPage() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountButton();
		registerPage = homePage.clickSignUpButton();
		assertEquals(registerPage.getTitle(), "Register");
	}

	@Test(dependsOnMethods = "enterRegisterationPage")
	@Description("Test all fields are valid except for email field empty. /n 2. Enter sign up data...")
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
	@Description("Test all fields are valid except for email field empty. \n 3. Verify unsuccessful registeration...")
	public void verifyUnSuccessfulRegisteration() {
		// Expect the website will stay in the same page
		assertEquals(registerPage.getTitle(), "Register");
	}

}
