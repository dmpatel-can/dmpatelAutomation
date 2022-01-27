package com.qa.opencart.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.util.Constants;

	public class ProductInfoPageTest extends BaseTest
	{
		@BeforeClass
		public void productInfoSetup()
		{
			myAcPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
	
		@Test(priority = 1)
		public void productHeaderTest()
		{
			sResPage = myAcPage.doSearch("MacBook");
			prodInfoPage = sResPage.selectProduct("MacBook Pro");
			Assert.assertEquals(prodInfoPage.getProductHeader(), "MacBook Pro");
		}
	
		@Test(priority = 2)
		public void productImagesCountTest()
		{
			sResPage = myAcPage.doSearch("iMac");
			prodInfoPage = sResPage.selectProduct("iMac");
			Assert.assertEquals(prodInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
		}
	
		@Test(priority = 3)
		public void productInfoTest()
		{
			sResPage = myAcPage.doSearch("MacBook");
			prodInfoPage = sResPage.selectProduct("MacBook Pro");
			Map<String, String> actProductInfoMap = prodInfoPage.getProductInfo();
			actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
			softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
			softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
			softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
			softAssert.assertAll();
		}
	}
