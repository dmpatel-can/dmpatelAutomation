package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage
{
//Step-1: Private driver
	private WebDriver driver;
	private ElementUtil etil;
	
//Step-2: Public Constructor to use Private driver
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		etil = new ElementUtil(driver);
	}
	
//Step-3: By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");


//Step-4: Page Actions & Logic:
	@Step("Getting login Page Title . . .")
	public String getLoginPageTitle()
	{
		return etil.waitDoGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_TIMEOUT);
	}
	
	@Step("Getting login Page URL . . .")
	public Boolean getLoginPageUrl()
	{
		return etil.waitDoForUrlContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_TIMEOUT);
	}
	
	@Step("Do Forgot Password link Exist or Not . . .")
	public boolean isExistForgotPwdLink()
	{
		return etil.doIsDisplayed(forgetPwdLink);
	}
	
	@Step("Do Register link Exist or Not . . .")
	public boolean isExistRegisterLink()
	{
		return etil.doIsDisplayed(registerLink);
	}
	
/* TDD: Test Driven Development ~~> When you perform any action on a Page and it lands on a New Page(Child),
	and You connect that New Page with the last Parent Page by making an Object of New Page */
	@Step("Do login with username: {0} and password: {1}")
	public MyAccountPage doLogin(String un, String pwd)
	{
		etil.doSendKeys(emailId, un);
		etil.doSendKeys(password, pwd);
		etil.doClick(loginBtn);
		return new MyAccountPage(driver);
	}
	
	@Step("Do login with wrong username: {0} and password: {1}")
	public boolean doLoginWrong(String un, String pw)
	{
		System.out.println("Login With Wrong Credentials: "+un+ " & " +pw);
		etil.doSendKeys(emailId, un);
		etil.doSendKeys(password, pw);
		etil.clickActions(loginBtn);
		
		String warningLogin = etil.doGetElementText(loginErrorMesg);
		if(warningLogin.contains(Constants.LOGIN_ERROR_MESSAGE))
		{
			System.out.println("Login Not Done");
			return false;
		}
		return true;	
	}
	
	@Step("Nevigating to RegisterPage")
	public RegisterPage GoToRegisterPage()
	{
		etil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
}
