package com.saran.cashkaro.pages;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saran.cashkaro.pageObjectRepositories.LoginPageObjects;
import com.saran.cashkaro.testUtils.ReuseableLibrary;

public class LoginPage extends ReuseableLibrary implements LoginPageObjects{
	
	
	/**
	 * @author Saran
	 * @since 10/24/2018
	 * 
	 * This class consists of methods related to Login Page.
	 * 
	 */
	public LoginPage(WebDriver driver,ExtentTest test) {
		
		this.driver=driver;
		this.test=test;
		
	}

	public void launchapp() {
		
		String url= properties.getProperty("Url");
		driver.get(url);
		test.log(LogStatus.INFO, url+"Application is Launched", "");
		click(XPATH_JOINLINK);
	}
	
	public void enterUserDetails() {
		
	if(isElementPresent(XPATH_FIRSTNAME)){
		
		enterText(XPATH_FIRSTNAME,"SARANRAJ");
		sendTab(XPATH_FIRSTNAME);
		
		enterText(XPATH_EMAIL,"sanwew13223@gmail.com");	
		sendTab(XPATH_EMAIL);
		
		enterText(XPATH_PASSWORD,"123456wwq");
		sendTab(XPATH_PASSWORD);
		
		enterText(XPATH_MOBILENUMBER,"9486090397");
		//enterText(XPATH_CAPTCHA,"12"); not handled
		
		click(XPATH_JOINWITHEMAIL);
	}
	}
	
	
	public void verifyWarningMessage(){
		
		String message= driver.findElement(XPATH_CAPTCHA).getAttribute("value");
		
		if(message.equals("Please enter valid number")){
			test.log(LogStatus.PASS, "Warning message is displayed", "");
		}else{
			test.log(LogStatus.FAIL, "Warning message is not displayed", "");
		}
	}
		

	public void signin(){
		
		click(XPATH_SIGNINLINK);
		
		if(isElementPresent(XPATH_USERNAME)){
			enterText(XPATH_USERNAME,"saranrajkannan27@gmail.com");
			enterText(XPATH_PASSWORD_SIGNIN,"test@123");
			click(XPATH_SIGNINWITHEMAIL);
			
			
		}else{
			test.log(LogStatus.FAIL, "sign in  is not displayed", "");
		}
	}
	
	public void verifySignInpage(){
		
		if(isElementPresent(XPATH_MYACCOUNT)){
			test.log(LogStatus.PASS, "User Succesfully Logged in", "");
			
		}else{
			test.log(LogStatus.FAIL, "User not Succesfully Logged in", "");
		}
		
	}
	


}
