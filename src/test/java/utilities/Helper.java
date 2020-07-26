package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.TestListenerAdapter;

public class Helper extends TestListenerAdapter {

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
