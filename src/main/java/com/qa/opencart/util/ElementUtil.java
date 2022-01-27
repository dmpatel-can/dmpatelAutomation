package com.qa.opencart.util;

import java.time.Duration;
//Control + O ~~> To find A Specific Method from the Class
//Control + Shift + R ~~> To find A Specific Class
////Control + Shift + R ~~> * and Search your Class By KeyWord
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil
{
	// Create Constructor to use private variable
		private WebDriver driver;
		private JavaScriptUtil jstil;
	
		public ElementUtil(WebDriver driver)
		{
			this.driver = driver;
			jstil = new JavaScriptUtil(driver);
		}
	
/**=============================================================*/
	
	public By getBy(String locatortype, String locatorvalue)
	{
		By locator = null;
		switch (locatortype.toLowerCase())
		{		
		case "id":
			locator = By.id(locatorvalue);
			break;
		
		case "name":
			locator = By.name(locatorvalue);
			break;
			
		case "xPath":
			locator = By.xpath(locatorvalue);
			break;
			
		case "cssSelector":
			locator = By.cssSelector(locatorvalue);
			break;
			
		case "linkText":
			locator = By.linkText(locatorvalue);
			break;
			
		case "tagName":
			locator = By.tagName(locatorvalue);
			break;
			
		default:
			System.out.println("Pass the right locator");
			break;
		}	
		return locator;
	}
	
/**============================== WebElement Methods ===================***/
	
/************************ Normal WebElement/WebElements********************/
// WebElement By locator
	
	public WebElement getElement(By locator)
	{	
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jstil.jsDoFlash(element);		
		}
		return element;
	}
// WebElement By String Value
	
	public WebElement getElement(String locatortype, String locatorvalue)
	{	
		return driver.findElement(getBy(locatortype, locatorvalue));
	}
// For Multiple Elements
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
		
/************** WAIT WebElement with (Presence of Element Methods)**************************************************/
// These Methods give WebElement Back with checking Element availability in Dom Only
// Then You can Perform all Selenium Methods after using this WebElement.
				
// WebElementWait
	
	public WebElement waitGetElement(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
// WebElement Wait PollingTime
	
	public WebElement waitGetElementmentPollingTime(By locator, int timeOut, long pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

// For Multiple Wait Elements
	
	public List<WebElement> waitGetElements(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	
/*********************** WAIT WebElement with (Visibility Methods) ************************/
	
// These Methods give WebElement Back with checking Element availability in Dom & Page Both
// Then You can Perform all Selenium Methods after using this WebElement.
		
		
//Element Visible Wait By WebElement
	
	public WebElement waitGetElementVisiblityByWebEle(By locator, int timeOut)
	{
	WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
//Element Visible Wait By WebElement PollingTime
	
	public WebElement waitGetElementVisiblityByWebEle(By locator, int timeOut, long pollingTime)
	{
	WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
	return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
//Element Visible Wait By locator
	
	public WebElement waitGetElementVisibility(By locator, int timeOut)
	{
	WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
//Element Visible Wait By locator PollingTime
	
	public WebElement waitGetElementVisibility(By locator, int timeOut, long pollingTime)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
//All Elements Visible Wait
	
	public List<WebElement> waitGetElementsVisibility(By locator, int timeOut)
	{
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
//All Elements Visible Wait PollingTime
	
	public List<WebElement> waitGetElementsVisibility(By locator, int timeOut, long pollingTime)
	{
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

/***************************** WebElement with FluentWait & WebDriverWait *******************************************/
//FluentWait	
	public  WebElement waitElementWithFluentWait(By locator, int timeOut, int pollingTime)
	{	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							.withTimeout(Duration.ofSeconds(timeOut))
							.pollingEvery(Duration.ofMillis(pollingTime))
							.ignoring(NoSuchElementException.class)
							.ignoring(StaleElementReferenceException.class)
							.withMessage(Errors.ELEMENT_NOT_FOUND_MESSAGE);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
	}
	
//WebDriverWait
	
	public WebElement waitElementWithWebDriverWait(By locator, int timeOut, int pollingTime)
	{	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					  wait.pollingEvery(Duration.ofMillis(pollingTime))
					  	  .ignoring(NoSuchElementException.class)
					  	  .ignoring(StaleElementReferenceException.class)
					  	  .withMessage(Errors.ELEMENT_NOT_FOUND_MESSAGE);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
	}
	
/******************************* WAIT CUSTOM WebElement Method *****************************************/
	
// When the Above All WebElement Methods do Not Work, Use this Custom Method which is The Most Accurate.
// Variables can be defined at class level but cannot be initialized.	
	public WebElement WaitGetElementCustom (By locator, int timeOut)
	{
// Initialize Variable being used in Method Body
		WebElement element = null; 	
		int attempts = 0;
// Now Let's Start Logic		
		while(attempts < timeOut)	/* Step-1: Use while loop Bcoz No iterations are not fixed*/
		{	
				try		/*Step-3: Cover with try-catch for handling exception*/
				{
					element = driver.findElement(locator);	/*Step-2*/
					break;
				}
				catch (NoSuchElementException e)
				{
						System.out.println("Element is Not Found: "+attempts+" : "+locator);
						try
						{
							Thread.sleep(500);	/*Step-4: Cover with try-catch*/
						}
						catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}
				}
			attempts++; // Step-1
		}
		
		if(element == null) // Step-5
		{
			try
			{
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch (Exception e)
			{
				System.out.println("Element is Not Found");
			}
		}
		
		return element; // U must return to get WebElement
	}
	
// Custom WebElement Polling Method
	
	public WebElement WaitGetElementCustom (By locator, int timeOut, int pollingTime)
	{
		WebElement element = null;		
		int attempts = 0;
		
		while(attempts < timeOut)
		{	
				try
				{
					element = driver.findElement(locator);
					break;
				}
				catch (NoSuchElementException e)
				{
						System.out.println("Element is Not Found: "+attempts+" : "+locator);
						try
						{
							Thread.sleep(pollingTime);
						}
						catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}
				}
			attempts++;
		}
		
		if(element == null)
		{
			try
			{
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch (Exception e)
			{
				System.out.println("Element is Not Found");
			}
		}
		
		return element;
	}
	
/**======= Get Current URL ===========*/
	
	public String doGetCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

/**============== To Send KeyWords ===================*/
	
/************** SendKeys with Normal Methods **********/
// Send Keys	
	public void doSendKeys(By locator, String keys)
	{
		
		getElement(locator).clear();
		getElement(locator).sendKeys(keys);
	}
	
	public void doSendKeys(String locatortype, String locatorvalue, String keys)
	{
		getElement(locatortype, locatorvalue).sendKeys(keys);
	}
	
/************** SendKeys with WAIT (Presence Methods) **********************/
// Send Keys Wait
	
	public void waitDoSendKeys(By locator, String keys, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(keys);
	}
	
	public void waitDoSendKeys(By locator, String keys, int timeOut, long pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(keys);
	}

/**============ TO Click =============*/
	
/**** To Click with Normal Methods ****/
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public void doClick(String locatortype, String locatorvalue )
	{
		getElement( locatortype, locatorvalue).click();
	}
			
/*********************** To Click with WAIT (Presence Methods) *********************/
	
// Click Wait
	
	public void waitDoClick(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}
	
	public void waitDoClick(By locator, int timeOut, long polllingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(polllingTime));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}
	
/*********************** To Click with WAIT (Clickable Method) *********************/
	
	public void waitDoClickElementWhenReady (By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
/**===== Element Displayed or Not Normal Method =====*/
// Element Display
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}

/**===================== Element Exist or Not ==========================*/
	
// For Verifying If Element Exist withCount() ?
		
	public boolean doElementExist (By locator)
	{
		int eleCount = doElementsCount(locator);
		System.out.println("The Total Elements Present are: "+ eleCount);
		if(eleCount<=1)
	
		{
			System.out.println(locator + " Element is Present ");
			return true;
		}
		else
		{
			System.out.println(locator + " Element is Not Present ");
			return false;
		}
	}
	
/**============================ Counting Total Elements ==================================*/
	
	public int doElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	
/**============================= Element Text On the Page =====================================*/
// ************************* Get Text with Normal Methods ***********************************/
	
// Get Page Text of Element
	
	public String doGetElementText(By locator)
	{
		 return getElement(locator).getText();
	}

// Get Page Text of Multiple Elements in String Values.
	
	public List<String> doGetElementsStringText(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList)
		{
			String eleText = e.getText();
			if(!eleText.isEmpty())
			{
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}
	
/************************* Get Page Text with WAIT (Presence Methods) ***********************/
	
// Get Page Text of Element Wait
	
	public String waitDoGetElementText(By locator, int timeOut)
	{
		WebElement ele = waitGetElementVisibility(locator, timeOut);
		return ele.getText();
	}
// Get Page Text of Element Wait PollingTime
	
	public String waitDoGetElementText(By locator, int timeOut, long pollingTime)
	{
		WebElement ele = waitGetElementVisibility(locator, timeOut, pollingTime);
		return ele.getText();
	}
	
// Get Page Text of Multiple Elements Wait
	
	public List<String> waitDoGetElementsText(By locator, int timeOut)
	{
		List<WebElement> eleList =  waitGetElementsVisibility(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList)
		{
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;		
	}
	
// Get Page Text of Multiple Elements Wait PollingTime

	public List<String> waitDoGetElementsText(By locator, int timeOut, long pollingTime)
	{
		List<WebElement> eleList =  waitGetElementsVisibility(locator, timeOut, pollingTime );
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList)
		{
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;		
	}

/**============================ Attribute DOM Value ======================================*/	

// For Capturing/Printing Attribute Value
	
		public String doGetAttributeValue(By locator, String attribute)
		{
			String attrValue = getElement(locator).getAttribute(attribute);
			System.out.println(attrValue);
			return attrValue;
		}
// For Capturing/Printing Attributes Values
	
	public List<String> doGetAttributesValueList(By locator, String attributeName)
	{
		List<WebElement> eleList = getElements(locator);
		List <String> attriList = new ArrayList <String>();
		
		for(WebElement e : eleList)
		{		
			String attributeValue = e.getAttribute(attributeName);
			attriList.add(attributeValue);		
		}
		return attriList;
	}
	
/*************************************************************************************
************************** Drop-Down Generic Methods *********************************
*************************************************************************************/
//Select Class Methods
	public void doDropDownSelectVisibleText(By locator, String text)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doDropDownSelectIndex(By locator, int index)
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doDropDownSelectValue(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
// Select Class with Option() Method: If The Above 3 do not work
	
	public void doDropDownSelectOption(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> optionEleList = select.getOptions();
	
		for(WebElement e : optionEleList)
		{
			String opText = e.getText();
			System.out.println(opText);
			if(opText.equals(value))
			{
				e.click(); break;
			}
		}
	}
/*********************** Non-Select DropDown *************************************/

// Without Select Class Object: Drop-Down Click specific Value
//id and name should not be used as they are for single element.
//For without Select: Better Use XPath which will hv all options in the DropDown
//Condition: XPath should consider Select class from Dom
	
	public void doDropDownClickValueWithoutSelect(By locator, String value)
	{
		List<WebElement> EleList = getElements(locator);
		for(WebElement e : EleList)
		{
			String sList = e.getText();
			if(sList.equals(value))
			{
				e.click(); break;
			}			
		}	
	}
	
// Print Drop-Down List
		
	public List<WebElement> doDropDownSelectPrintList(By locator)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();
		
		for(WebElement e : optionList)
		{
			e.getText();
		}
		return optionList;
	}
	
// Print Static Table Text by XPath Only:
	
	public void printStaticTable(By rowLocator, By colLocator, String beforeXPath, String afterXPath)
	{
		int rowCount = doElementsCount(rowLocator);
		int colCount = doElementsCount(colLocator);
		
		for(int row=2; row<=rowCount; row++)
		{
			for(int col=1; col<=colCount; col++)
			{
				String XPath = beforeXPath+row+afterXPath+col+"]";
				String tableText = doGetElementText(By.xpath(XPath));
				System.out.println(tableText);		
			}
			System.out.println();
		}		
	}
	
// Single/Multiple/All Choices without Select Tag

	public void doDropDownMultipleChoiceNoSelect(By locator, String... value)
	{
		List<WebElement> choiceList = getElements(locator);
		boolean flag = false;
		
		if(value[0].equalsIgnoreCase("all"))
		{
			try	{
					for(WebElement e : choiceList)
					{e.click();}
				}
			catch (ElementNotInteractableException e)
				{
					System.out.println("All Elements are Over");
				}
		}
		
		else
		{
			for(WebElement e : choiceList)
			{	String text = e.getText();
				for(int i=0; i<value.length; i++)
				{
					if(text.equalsIgnoreCase(value[i]))		
					{flag = true; e.click(); break;}			
				}
			}
			
			if (flag == false)
			{
				System.out.println("Element is not Available");
			}
		}
	}
/**==>============================== ACTIONS Util ================================================

*/// Move To Element on Movement Menu
	 
	public void doMoveToElement(By locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}

//Click on Child Elements after Movement done by Actions Class on Movement Menu.
	 public void doClickOnChildMenu(By parentLocator, By childLocator) throws InterruptedException
	 {
		doMoveToElement(parentLocator);
		Thread.sleep(2000);
		doClick(childLocator);		
	 }
	 	
/*************************** Actions Click & Send Keys ***************************************
=> Use Actions Class When OverLap issues on Element
=> Use If "Element Not Intractable" situation happens */
	 
// Actions Click
	 	
	 public void clickActions(By locator)
	 {
		 Actions act = new Actions(driver);
		 act.click(getElement(locator)).build().perform();
	 }

// Actions Move & Click
	 
	 public void clickWithMoveActions(By locator)
	 {
		 Actions act = new Actions(driver);
		 act.moveToElement(getElement(locator)).click().build().perform();
	 }
		
// Actions Send Keys
	 public void doSendKeysActions(By locator, String value)
		{	
			Actions act = new Actions(driver);
			act.sendKeys(getElement(locator), value).build().perform();	
		}

// Actions Click & SendKeys
	 public void doClickSendKeysActions(By locator, String value)
		{	
			Actions act = new Actions(driver);
			act.click(getElement(locator)).sendKeys(value).build().perform();
		}
	 
/**================================ Non-WebElement Wait ==================================
==========================================================================================

************************************** Wait URL Check ***********************************/

// URL Contains/Fractions Check
	 
	 public Boolean waitDoForUrlContain(String fractionUrl, int timeOut)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.urlContains(fractionUrl));
	 }
	 
// URL Check
	 
	 public Boolean waitDoForUrlToBe(String url, int timeOut)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.urlToBe(url));
	 }

/******************************** Wait Title Check *************************************/

// U r given FractionTitle, Check It is Present or Not
	 
	 public Boolean waitDoTitleContainsPresent(String fractionTitle, int timeOut)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.titleContains(fractionTitle));
	 }
	 
// U r given Title, Check It is Present or Not
	 
	 public Boolean waitDoTitleToBePresent(String title, int timeOut)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.titleIs(title));
	 }
	 
// Get Title
	 
	 public String waitDoGetTitle(String title, int timeOut)
	 {
		 if(waitDoTitleToBePresent(title, timeOut))
		 {
			return driver.getTitle(); 
		 }
		 return null;
	 }
	 
// Get Title from FractionTitle
	 
	 public String waitDoGetTitleFromFraction(String fractionTitle, int timeOut)
	 {
		 if(waitDoTitleContainsPresent(fractionTitle, timeOut))
		 {
			return driver.getTitle(); 
		 }
		return null;
	 }
	 
/********************************* Wait ALERT *************************************/
	 
// Alert Present or Not
	 
	public Alert waitDoAlertPresent(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
// Alert Get Text
	
	public String waitDoGetAlertText(int timeOut)
	{
		return waitDoAlertPresent(timeOut).getText();
	} 
	 
// Alert Accept
	
	public void waitDoAlertAccept(int timeOut)
	{
		waitDoAlertPresent(timeOut).accept();
	}
	
// Alert Dismiss
	
	public void waitDoAlertDismiss(int timeOut)
	{
		waitDoAlertPresent(timeOut).dismiss();
	} 

// Alert Send Keys
	
	public void waitDoAlertSendKeys(String keys, int timeOut)
	{
		waitDoAlertPresent(timeOut).sendKeys(keys);
	}
	
/********************************* Wait FRAME *************************************/	
	
// Move Driver--to--> Frame by locator
	
	public void waitMoveToFrameWithLocator(By framelocator, int tomeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tomeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
	}
	
// Move Driver--to--> Frame by WebElement
	
	public void waitMoveToFrameWithElement(By frameElement, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
// Move Driver--to--> Frame by index
	
	public void waitMoveToFrameWithIndex(int frameIndex, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
// Move Driver--to--> Frame by name/id
	
	public void waitMoveToFrameWithNameOrId(String nameOrID, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
	}

	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
		
		
		
		
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
