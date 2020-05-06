package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.husbspot.config.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the driver based on the browser
	 * @param browser
	 * @return driver
	 */
	public WebDriver int_driver(Properties prop) {
		
		String browser = prop.getProperty("browser");
		System.out.println("browser name is " + browser);
		
		optionsManager = new OptionsManager(prop);
				
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		} else {
			System.out.println(browser + "is not found. Please pass the correct browser");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		
		getDriver().get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		
		
		return getDriver();
	}
	
	/**
	 * This method is used to initialize/load the properties from config file
	 * @return
	 */
	public Properties int_prop(){
		 prop = new Properties();
		 try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/husbspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return prop;
		 
	}

	/**
	 * take screenshot util
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
}
