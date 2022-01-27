package com.qa.opencart.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultPage
{
	private WebDriver driver;
	private ElementUtil etil;
	private By productResults = By.cssSelector("div.caption a");
	
	public SearchResultPage(WebDriver driver)
	{
		this.driver = driver;
		etil = new ElementUtil(driver);
	}
	
	public String getSearchResultPageTitle()
	{
		String actualSearchResultPageTitle = etil.waitDoGetTitle(Constants.SEARCH_RESULT_PAGE_TITLE, Constants.DEFAULT_TIME_TIMEOUT);
		return actualSearchResultPageTitle;
	}
	
	public int getProductsListCount()
	{
		int resultCount = etil.waitGetElementsVisibility(productResults, 10, 2000).size();
		System.out.println("the search product count: " + resultCount);
		return resultCount;
	}

	public ProductInfoPage selectProduct(String mainProductName)
	{
		System.out.println("main product name is : " + mainProductName);
		List<WebElement> searchList = etil.waitGetElementsVisibility(productResults, 10, 2000);
			for (WebElement e : searchList)
			{
				String text = e.getText();
				if (text.equals(mainProductName))
				{e.click();break;}
			}
		return new ProductInfoPage(driver);
	}	
}
