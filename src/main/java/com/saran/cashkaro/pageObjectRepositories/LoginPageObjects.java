package com.saran.cashkaro.pageObjectRepositories;

import org.openqa.selenium.By;

public interface LoginPageObjects extends CommonObjects {
	
	By XPATH_JOINLINK = By.xpath(String.format(XPATH_LINK, "Join Free"));
	By XPATH_SIGNINLINK = By.xpath(String.format(XPATH_LINK, "Sign in"));

	
	By XPATH_FIRSTNAME = By.xpath(String.format(XPATH_TXT, "firstname"));
	By XPATH_EMAIL = By.xpath(String.format(XPATH_TXT, "email"));
	By XPATH_PASSWORD = By.xpath(String.format(XPATH_TXT, "password"));
	By XPATH_MOBILENUMBER = By.xpath(String.format(XPATH_TXT, "mobile_number"));
	By XPATH_CAPTCHA = By.xpath(String.format(XPATH_TXT, "to_be_check"));

	By XPATH_JOINWITHEMAIL = By.xpath(String.format(XPATH_BUTTON, "JOIN WITH EMAIL"));
	
	
	By XPATH_USERNAME = By.xpath(String.format(XPATH_TXT, "uname"));
	By XPATH_PASSWORD_SIGNIN = By.xpath(String.format(XPATH_TXT, "pwd"));
	
	By XPATH_SIGNINWITHEMAIL = By.xpath(String.format(XPATH_BUTTON, "SIGN IN WITH EMAIL"));
	
	By XPATH_MYACCOUNT = By.xpath(String.format(XPATH_LINK, "My Account"));
	
}
