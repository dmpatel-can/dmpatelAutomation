package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class MyAccountPage
{
	
	private WebDriver driver;
	private ElementUtil etil;
	
	private By header = By.cssSelector("div#logo a");
	private By accountsSections = By.cssSelector("div#content h2");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	private By logoutLink = By.linkText("Logout");
	
	
	public MyAccountPage(WebDriver driver)
	{
		this.driver = driver;
		etil = new ElementUtil(driver);
	}
 
	public String getMyAccountPageTitle()
	{
		return etil.waitDoGetTitle(Constants.MY_ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_TIMEOUT);
	}
	
	public String getMyAccountPageHeader()
	{
		return etil.doGetElementText(header);
	}
	
	public boolean isExistLogoutLink()
	{
		return etil.doIsDisplayed(logoutLink);
	}
	
	public void logout()
	{
		if(isExistLogoutLink())
		{
			etil.doClick(logoutLink);
		}
	}
	
	public List<String> getAccountSecList()
	{
		List<WebElement> accSecList = etil.waitGetElementsVisibility(accountsSections, Constants.DEFAULT_TIME_TIMEOUT);
		List<String> accSecValList = new ArrayList<String>();
			for(WebElement e : accSecList)
			{
				String text = e.getText();
				accSecValList.add(text);
			}
		return accSecValList;
	}
	
	public boolean isExistSearch()
	{
		return etil.doIsDisplayed(searchField);
	}
	
/* TDD: Test Driven Development ~~> When you perform any action on a Page and it lands on a New Page(Child),
	and You connect that New Page with the last Parent Page by making an Object of New Page */
	public SearchResultPage doSearch(String productName)
	{
		System.out.println("Searching the product: " + productName);
		etil.doSendKeys(searchField, productName);
		etil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
	
	
	

}
