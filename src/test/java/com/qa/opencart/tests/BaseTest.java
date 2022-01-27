package com.qa.opencart.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

//Initialize every Common things Whatever being used in Test Pages,
public class BaseTest
{
	WebDriver driver;
	DriverFactory df;
	LoginPage lp;
	Properties prop;
	RegisterPage rg;
	MyAccountPage myAcPage;
	SearchResultPage sResPage;
	ProductInfoPage prodInfoPage;
	SoftAssert softAssert;
	
	
	@BeforeTest
	public void setUp()
	{
		df = new DriverFactory();
		prop = df.initializeProp();
		driver = df.initializeDriver(prop);
		softAssert = new SoftAssert();
		lp = new LoginPage(driver);
		rg = new RegisterPage(driver);
		myAcPage = new MyAccountPage(driver);
		sResPage = new SearchResultPage(driver);
		prodInfoPage = new 	ProductInfoPage(driver);
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
