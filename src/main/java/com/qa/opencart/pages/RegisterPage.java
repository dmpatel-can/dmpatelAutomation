package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage
{

	private ElementUtil etil;
	
	public RegisterPage(WebDriver driver)
	{
		etil = new ElementUtil(driver);
	}
//******************************************************************/
	private By firstName = By.cssSelector("input#input-firstname");
	private By lastName = By.cssSelector("input#input-lastname");
	private By eMail = By.cssSelector("input#input-email");
	private By telePhone = By.cssSelector("input#input-telephone");
	private By pWord = By.cssSelector("input#input-password");
	private By pWordCon = By.cssSelector("input#input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	private By pPolicyCheck = By.xpath("//input[@type='checkbox']");
	private By continu = By.cssSelector("input.btn.btn-primary");
	
	private By sucessMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
//**********************************************************************************************************/
	public String registerPageTitle()
	{
		return etil.waitDoGetTitle(Constants.REGISTER_PAGE_TITLE, Constants.DEFAULT_TIME_TIMEOUT);
	}
	
	public Boolean getRegisterPageUrl()
	{
		return etil.waitDoForUrlContain(Constants.REGISTER_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_TIMEOUT);
	}
	
	public boolean doRegister(String firstname, String lastname, String email, String telephone, String password, String subscribe)
	{
		etil.doSendKeys(firstName, firstname);
		etil.doSendKeys(lastName, lastname);
		etil.doSendKeys(eMail, email);
		etil.doSendKeys(telePhone, telephone);
		etil.doSendKeys(pWord, password);
		etil.doSendKeys(pWordCon, password);
		if(subscribe.equals("Yes"))
			{etil.doClick(subscribeYes);}
		else
			{etil.doClick(subscribeNo);}
		etil.doClick(pPolicyCheck);
		etil.doClick(continu);
		
		String smsg = etil.waitDoGetElementText(sucessMessg, 5);
		System.out.println(smsg);
		if(smsg.contains(Constants.REGISTER_SUCCESS_MESSG))
		{
			etil.doClick(logoutLink);
			etil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
