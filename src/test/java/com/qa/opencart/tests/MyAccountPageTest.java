package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.Errors;


public class MyAccountPageTest extends BaseTest
{
	@BeforeClass	// Pre-Condition is to do Login

	public void MyAccountPageSetUp()
	{
		myAcPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void getAccountPageTitleTest()
	{
		String actualMyAcPageTitle = myAcPage.getMyAccountPageTitle();
		System.out.println("My Account Page Title: " + actualMyAcPageTitle);
		Assert.assertEquals(actualMyAcPageTitle, Constants.MY_ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accountPageHeaderTest ()
	{
		String actualMyAcPageHeader = myAcPage.getMyAccountPageHeader();
		System.out.println("My Account Page Header: " + actualMyAcPageHeader);
		Assert.assertEquals(actualMyAcPageHeader, Constants.MY_ACCOUNT_PAGE_HEADER, Errors.MY_ACCOUNT_PAGE_HEADER_ERROR_MESSAGE);
// Here 3rd comment is not compulsory but for expected error msg print only
	}
	
	@Test(priority = 3)
	public void isExistLogoutLinkTest()
	{
		Boolean logOutlink = myAcPage.isExistLogoutLink();
		Assert.assertTrue(logOutlink);
	}
	
	@Test(priority = 4)
	public void getAccountSecListTest()
	{
		List<String> actualSecList = myAcPage.getAccountSecList();
		Assert.assertEquals(actualSecList, Constants.getExpectedAccountSecList());
	}
	
// DataProvider Approach is used to fetch Data from Excel Sheet and to Provide to Test Method
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][]
		{{"IPhone 12"},{"MacBook"},{"Galaxy 22"}};
	}

	@Test(priority = 5, dataProvider = "productData")
	public void doSearchTest(String product)
	{
		myAcPage.doSearch(product);
	}	
}
