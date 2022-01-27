package com.qa.opencart.tests;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest

{	
	@BeforeClass
	public void registerPageSetUp()
	{
		rg = lp.GoToRegisterPage();
	}
	
	@Test(priority = 1)
	public void registerPageTitleTest()
	{
		String actualRgTitle = rg.registerPageTitle();
		Assert.assertEquals(actualRgTitle, Constants.REGISTER_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void RegisterPageUrlTest()
	{
		Assert.assertTrue(rg.getRegisterPageUrl());
	}

// To Generate Random EmailId for creating Users	
	public String getRandomEmail()
	{
		Random randomGenerator = new Random();
		String email = "automation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}

//To Get Data	
	@DataProvider
	public Object[][] getRegisterData()
	{
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData")
	public void doRegisterTest(String firstname, String lastname, String telephone, String password, String subscribe)
	{
		Assert.assertTrue(rg.doRegister(firstname, lastname, getRandomEmail(), telephone, password, subscribe));	
	}
}
