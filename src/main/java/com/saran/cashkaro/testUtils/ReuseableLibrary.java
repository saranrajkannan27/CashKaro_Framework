package com.saran.cashkaro.testUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;
import com.saran.cashkaro.base.Base;

public class ReuseableLibrary extends Base {
	
	/**
	 * @author Saran
	 * @since 10/24/2018
	 * 
	 * This class consists of reusable functions like "click", "enterText"etc.
	 * 
	 */
	
	public void enterText(By by, String textToEnter) {
		try {

			driver.findElement(by).sendKeys(textToEnter);
			test.log(LogStatus.PASS,
					"Text '<b>" + textToEnter + "</b>' is entered successfully in element with locator - <b>" + by
							+ "</b>",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Text '<b>" + textToEnter + "</b>' is not entered successfully in element with locator - <b>" + by
							+ "</b>",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
			test.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void click(By by) {

		try {

			driver.findElement(by).click();
			test.log(LogStatus.PASS, "Element with locator - <b>" + by + "</b> is clicked successfully",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element with locator - <b>" + by + "</b> is not clicked successfully",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
			test.log(LogStatus.FAIL, e.getMessage());
			
		}

	}
	
	public String screenShot() {

		String screenshotName = null;

		screenshotName = getCurrentFormattedTime("dd_MMM_yyyy_hh_mm_ss_SSS");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {

			FileUtils.copyFile(scrFile,
					new File("./Results/" + reportFolderName + "/screenshots/" + screenshotName + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotName;

	}
	
	
	public boolean isElementPresent(By by){
		
		List<WebElement> element= driver.findElements(by);	
		
		if(element.size()!=0){
			test.log(LogStatus.PASS, "Element with locator - <b>" + by + "</b> is Present",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
			return true;
		}else{
			test.log(LogStatus.FAIL, "Element with locator - <b>" + by + "</b> is not Present",
					"<b>Screenshot: <b>" + test.addScreenCapture("./screenshots/" + screenShot() + ".png"));
			return false;
		}
		
	}
	
	
	public void sendTab(By by){
		WebElement webElement = driver.findElement(by);//You can use xpath, ID or name whatever you like
		webElement.sendKeys(Keys.TAB);
	}
}
