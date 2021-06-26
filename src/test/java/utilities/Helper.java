package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.qameta.allure.Attachment;
import pages.AbstractPage;

public class Helper implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message){
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", AbstractPage.getDriver());
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = AbstractPage.getDriver();
		// Allure ScreenShot and SaveTestLog
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult)+ " failed and screenshot taken!");
	}
	
	public static String takeScreenShot(WebDriver driver, String screenshotPath) throws IOException {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File screenshot = new File(screenshotPath);
			FileUtils.copyFile(source, screenshot);
			driver.close();
			return screenshot.getAbsolutePath();
		} catch (IOException e) {
			throw new AssertionError("The screenshot was not taken successfully because of an IOException.", e);
		}
	}
}
