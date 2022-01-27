package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.util.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design OpenCart App - Ligin Page")
@Story("US 101: OpenCart Design with multiple features")
@Listeners(TestAllureListener.class)//To get the ScreenShot in Allure Report
public class LoginPageTest extends BaseTest
/*After completing driver setup in LoginPage, BaseTest, DriverFactory
We can inherit anything from BaseTest */

{
	@Description("loginPageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String actualLpTitle = lp.getLoginPageTitle();
		System.out.println(actualLpTitle);
		Assert.assertEquals(actualLpTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("loginPageUrlTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void loginPageUrlTest()
	{
		Assert.assertTrue(lp.getLoginPageUrl());
	}
	
	@Test(priority = 3)
	public void registerLinkTest()
	{
		Assert.assertTrue(lp.isExistRegisterLink());
	}

	@Test(priority = 4)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(lp.isExistForgotPwdLink());
	}
	
// To Test this Page Chaining Methods: Use Title comparison.
	@Description("login Test with correct credentils")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void doLoginTest()
	{
		myAcPage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(myAcPage.getMyAccountPageTitle(), Constants.MY_ACCOUNT_PAGE_TITLE);
	}
}
