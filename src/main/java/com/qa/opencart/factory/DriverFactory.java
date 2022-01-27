package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory
{
	public Properties prop;
	public WebDriver driver;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
// ThreadLocal Object is created to run parallel execution without major interruption. It's advisable.

/**==================================================================================================*/
// 1. Initialize Properties: @return prop	
	public Properties initializeProp()
	{
		prop = new Properties();//Make Object of Properties Class
		FileInputStream fis = null;
		String envName = System.getProperty("env");//To fetch env Value from ConfigData Folder
		
		if(envName == null) // This is Production Environment
		{	System.out.println("This Test is Running on Production Environment");
			try
			{/*Connect to config.properties and it will ask for try-catch*/
				fis = new FileInputStream("./src/test/resources/configData/config.properties");
				prop.load(fis); /*To Load Data and it will ask for add catch*/
			}
			catch (FileNotFoundException e)
			{e.printStackTrace();} 
			catch (IOException e)
			{e.printStackTrace();}
		}
		else
		{	try
			{	switch (envName.toLowerCase())
				{
				case "qa": 
					fis = new FileInputStream("./src/test/resources/configData/qa.config.properties"); break;	
				case "dev": 
					fis = new FileInputStream("./src/test/resources/configData/dev.config.properties"); break;	
				case "uat": 
					fis = new FileInputStream("./src/test/resources/configData/uat.config.properties"); break;	
				case "stage": 
					fis = new FileInputStream("./src/test/resources/configData/stage.config.properties"); break;
				default:
					fis = new FileInputStream("./src/test/resources/configData/config.properties"); break;
				}
			}		
			catch(FileNotFoundException e)
				{e.printStackTrace();}
		}
		try
		{prop.load(fis);}
		catch (IOException e)
		{e.printStackTrace();}
		
	  return prop;
	}
	
/**=============================================================================================================*/	
// 2. Initialize driver: @return driver
	public WebDriver initializeDriver(Properties prop)
	{
		String webBrowser = prop.getProperty("browser").trim();
		System.out.println("This is The Browser: "+ webBrowser);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if(webBrowser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));	
		}
		else if(webBrowser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
		}
		else if(webBrowser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		openUrl(prop.getProperty("url"));
	  return getDriver();	
	}
	
/**==================================================================================================================*/	
// 3. @return: Thread Local Copy of WebDriver. Also write synchronized keyword bcoz to counter multiple thread attacks
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}

/**==================================================================================================================*/
// 4. Creating Open URL
	public void openUrl(String url)
	{
		getDriver().get(url);
	}
	
/**==================================================================================================================*/
// 5. Creating Open URL
		public void openUrl(String baseUrl, String pathPortion)
		{	try
			{
				if(baseUrl == null)
				{throw new Exception("baseUrl is null");}
			}
			catch(Exception e)
			{}//http:amazon.com/accpage/users.html
			getDriver().get(baseUrl+"/"+pathPortion);
		}
		
/**==================================================================================================================*/
// 6. Creating Open URL
		public void openUrl(String baseUrl, String pathPortion, String queryParameter)
		{	try
			{	
				if(baseUrl == null)
				{throw new Exception("baseUrl is null");}
			}
			catch(Exception e)
			{} //http:amazon.com/accpage/users.html?route=account/login
			getDriver().get(baseUrl+"/"+pathPortion+"?"+queryParameter);
		}	
/**==================================================================================================================*/	

// @Get ScreenShot from Selenium
	public String getScreenshot()
	{	//Convert driver into screenshot type of file via TakesScreenchot Interface and call screenshot method and give value with output type interface file
		File screenshotfile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//Now five this file to current project directory by creating a screenshots folder
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destinationfile = new File(path);
		//Now Copy screenshot from ....
		try
		{FileUtils.copyFile(screenshotfile, destinationfile);}
		catch (IOException e)
		{e.printStackTrace();}	// Now screenshot is completely stored in this path 
	return path;
	}

	
	
	
	
}





