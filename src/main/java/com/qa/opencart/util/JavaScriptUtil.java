package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// JSE = Use Only If All other Types of Methods throw "Element Not Intractable Exception"
// JSE = It executes Code from Dom: So It's Not advisable to Use.

public class JavaScriptUtil
{
	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver)
	{this.driver = driver;}
	
/**======================***************************========================================*/
	
// Element BackGround Color Change
	
	private void jsDochangeColor(String color, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try
		{Thread.sleep(20);}
		catch (InterruptedException e)
		{}
	}

// BackGround Element Flash
	
	public void jsDoFlash(WebElement element)
	{
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 4; i++)
		{
			jsDochangeColor("rgb(0,200,0)", element);// 1
			jsDochangeColor(bgcolor, element);// 2
		}
	}

// Draw Border around Element
		
	public void jsDrawBorder(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

// ScrollDown to Bottom
	
	public void jsScrollPageDown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

// ScrollDown till Specific Height
	
	public void jsScrollPageDown(String specificHeight)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + specificHeight + "')");
	}

// ScrollUp to Top
	
	public void jsScrollPageUp()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

// Scroll towards Specific View	
	
	public void jsScrollIntoView(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

// JS Title
	
	public String jsGetTitle()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

// JS Refresh Browser
	
	public void jsRefreshBrowser()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

// Generate Your Own Alert	
	
	public void jsGenerateAlert(String message)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

// Get Whole Page Text
	
	public String jsGetPageInnerText()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
// JS Click
	
	public void jsClickElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

// JS Send Keys with ID Only
	
	public void jsSendKeysUsingWithId(String id, String value)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	
// JS Wait for Page Loading
	
	public void jsWaitForPageLoaded()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String loadingStatus = js.executeScript("return document.readyState;").toString();

		if (loadingStatus.equals("complete"))
		{
			System.out.println("Page is fully Loaded");
		} 
		else
		{
			for (int i = 1; i <= 20; i++)
			{
					try
					{Thread.sleep(1000);}
					catch (InterruptedException e)
					{e.printStackTrace();}
					
				loadingStatus = js.executeScript("return document.readyState;").toString();
				System.out.println("current page loading status: " + loadingStatus);
				
				if (loadingStatus.equals("complete"))
				{break;}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
