package com.saran.cashkaro;

import org.testng.annotations.Test;

import com.saran.cashkaro.base.Base;
import com.saran.cashkaro.pages.LoginPage;
import com.saran.cashkaro.testUtils.ReuseableLibrary;

public class LoginPageTest extends ReuseableLibrary{
	
	LoginPage login;
	
	@Test
	public void Verify_Warning_Message() {
		login=new LoginPage(driver,test);
		login.launchapp();
		login.enterUserDetails();
		login.verifyWarningMessage();
		
	}
	@Test
	public void Verify_User_Signin() {
		login=new LoginPage(driver,test);
		login.launchapp();
		login.signin();
		login.verifySignInpage();
		
	}
}
