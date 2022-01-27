package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginPageTest extends BaseTest
{	
	@DataProvider
	public Object[][] WrongLoginData()
	{
		return new Object[][]
		{
			{"testingjava@zmeil.com","test@15975"},
			{"testingjava@zmeil.com",""},
			{"","test@15975"},
			{"test#$%^@zmeil.com","test-9-2"},
			{"",""},
		};
	}
		
	@Test (dataProvider = "WrongLoginData")
	public void doNegativeLoginTest(String username, String password)
	{
		Assert.assertFalse(lp.doLoginWrong(username, password));
		
	}
}
