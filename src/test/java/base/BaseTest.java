package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import data.JsonDataReader;
import pages.HomePage;
import utilities.Helper;

public class BaseTest {

	private WebDriver driver;
	protected HomePage homePage;
	protected JsonDataReader dataReader;
	private ExtentTest logger;
	private ExtentReports report;

	@BeforeTest
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.phptravels.net/home");
		homePage = new HomePage(driver);
	}

	@BeforeTest
	public void initJsonReader() throws FileNotFoundException, IOException, ParseException {
		dataReader = new JsonDataReader();
		dataReader.JsonReader();
	}

	@BeforeMethod
	public void setupReport(Method m) {
		report = new ExtentReports("./Reports/customizedReport.html");
		logger = report.startTest(m.getName());
	}

	@AfterMethod
	public void updateReportWithFailedScreenshots(ITestResult result, Method m) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = "./Screenshots/" + result.getName() + ".png";
			String screenshotAbsolutePath = Helper.takeScreenShot(driver, screenshot_path);
			String image = logger.addScreenCapture(screenshotAbsolutePath);
			logger.log(LogStatus.FAIL, m.getName(), image);

		}
		report.endTest(logger);
		report.flush();
	}

	@AfterTest
	public void closeSession() {
		driver.quit();
	}

}
