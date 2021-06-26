package completeScenarios;

import static org.testng.Assert.assertEquals;

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

public class NPwdLimit extends BaseTest {

	RegisterPage registerPage;
	AccountPage accountPage;
	HomePage homePage;
	
	@BeforeClass
	public void initJsonReader() throws FileNotFoundException, IOException, ParseException {
		dataReader = new JsonDataReader();
		dataReader.JsonReader("n_pwd_limit");
	}
	
	@Test
	@Description("Test all fields are valid and there isn't a limit on the password field {3 char}. 1. Open the sign up page...")
	public void enterRegisterationPage() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountButton();
		registerPage = homePage.clickSignUpButton();
		assertEquals(registerPage.getTitle(), "Register");
	}

	@Test(dependsOnMethods = "enterRegisterationPage")
	@Description("Test all fields are valid and there isn't a limit on the password field {3 char}. 2. Enter sign up data...")
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
	@Description("Test all fields are valid and there isn't a limit on the password field {3 char}. 3. Verify unsuccessful registeration...")
	public void verifySuccessfulRegisteration() {
		// Expect the sign up will be completed successfully
		assertEquals(registerPage.getTitle(), "My Account");
		assertEquals(accountPage.getWelcomeMessage(),
				"Hi, " + dataReader.map.get("firstname") + " " + dataReader.map.get("lastname"),
				"Wrong message displayed!" + accountPage.getWelcomeMessage());
	}
	

}
