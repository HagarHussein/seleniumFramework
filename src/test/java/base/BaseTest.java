package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

// cross platform
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import data.JsonDataReader;
import pages.HomePage;
import utilities.Helper;

@Listeners({Helper.class})
public class BaseTest {

	public WebDriver driver;

	public JsonDataReader dataReader; // to be moved inside each test class

	
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.phptravels.net/home");
		
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void closeSession() {
		driver.quit();
	}

}
