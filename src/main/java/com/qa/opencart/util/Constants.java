package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.List;

public class Constants
{
// TimeOut
	public static final int DEFAULT_TIME_TIMEOUT = 5;
// Login Page
	public static final String LOGIN_PAGE_URL = "https://demo.opencart.com/index.php?route=account/login";
	public static final String LOGIN_PAGE_URL_FRACTION = "cart.com/index.php?route=account/login";
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_ERROR_MESSAGE = "No match for E-Mail Address and/or Password.";
// Register Page
	public static final String REGISTER_PAGE_URL = "https://demo.opencart.com/index.php?route=account/register";
	public static final String REGISTER_PAGE_URL_FRACTION = "cart.com/index.php?route=account/reg";
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String FIRST_NAME = "Nemmi";
	public static final String LAST_NAME = "Tellsa";
	public static final String EMAIL = "nmtlssaa789@zohomail.com";
	public static final String TELEPHONE = "95251471452879592";
	public static final String PASSWORD = "nmtlssaa789";
	public static final String CONFIRM_PASSWORD = "nmtlssaa789";
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "RegisterTestData";
// My Account Page
	public static final String MY_ACCOUNT_PAGE_TITLE = "My Account";
	public static final String MY_ACCOUNT_PAGE_HEADER = "Your Store44";
		
	public static List<String> getExpectedAccountSecList()
	{
		List <String> expectedAccountSecList = new ArrayList <String> ();
		expectedAccountSecList.add("My Account");
		expectedAccountSecList.add("My Orders");
		expectedAccountSecList.add("My Affiliate Account");
		expectedAccountSecList.add("Newsletter");
		return expectedAccountSecList;
	}
	
// 
	public static final String SEARCH_RESULT_PAGE_TITLE = "Search";
	public static final Object IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOKPRO_IMAGE_COUNT = 4;
	public static final int MACBOOKAIR_IMAGE_COUNT = 4;
	
	
	
	
	
	
	







}
