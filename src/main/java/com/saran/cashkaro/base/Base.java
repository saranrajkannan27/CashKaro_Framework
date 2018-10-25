package com.saran.cashkaro.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saran.cashkaro.testUtils.ReuseableLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public  WebDriver driver;
	public  Properties properties;
	public  ExtentReports report;
	public  ExtentTest test;
	public  static  String reportFolderName;


	public  Base() {
		properties= new Properties();
		try {
			FileInputStream fis= new FileInputStream(".\\src\\main\\java\\com\\saran\\cashkaro\\config\\environment.properties");
			properties.load(fis);
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		}catch(IOException e) {		
			e.printStackTrace();
		}
	}

	public  void intializeWebdriver() {

		String browser= properties.getProperty("Browser");

		if(browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver(options);

		}
		if(browser.equalsIgnoreCase("firefox")){

			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@BeforeSuite
	public void beforesuite(){
		reportFolderName();
		report = new ExtentReports(System.getProperty("user.dir") +"\\Results\\"+reportFolderName+"\\TestSummary.html", true);
		report
		.addSystemInfo("Host Name", "Saran")
		.addSystemInfo("Environment", "QA")
		.addSystemInfo("User Name", "Saranraj Kannan");
		report.loadConfig(new File(System.getProperty("user.dir")+"./extent-report-config.xml"));
	}

	public String reportFolderName() {
		return reportFolderName="RUN_"+getCurrentFormattedTime("dd_MMM_yyyy_hh_mm_ss");
	}

	public static  String getCurrentFormattedTime(String format) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String time=formater.format(calendar.getTime());
		return time;
	}

	@BeforeMethod
	public void startTest(Method result) {
		intializeWebdriver();
		test=report.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started","");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
		test.log(LogStatus.INFO, "Browser Closed Successfully","");
		report.endTest(test);


	}

	@AfterSuite
	public void aftersuite(){
		report.flush();
		report.close();
		
	}

}
