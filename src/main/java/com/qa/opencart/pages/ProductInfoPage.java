package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage
{
	private ElementUtil etil;

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		etil = new ElementUtil(driver);
	}

	public String getProductHeader()
	{
		String productHeaderText = etil.doGetElementText(productHeader);
		System.out.println("product header is: " + productHeaderText);
		return productHeaderText;
	}

	public int getProductImagesCount()
	{
		return etil.waitGetElementsVisibility(productImages, 10).size();
	}

	public Map<String, String> getProductInfo()
	{// Linked HashMap gives as it is Data from the Page
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData()
	{
		List<WebElement> metaDataList = etil.getElements(productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock

		for (WebElement e : metaDataList)
		{
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData()
	{
		List<WebElement> metaPriceList = etil.getElements(productPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("ExTaxPrice", exPrice);
	}
}
