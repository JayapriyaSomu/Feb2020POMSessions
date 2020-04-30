package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		} else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
		} else {
			System.out.println(browser + "is not found. Please pass the correct browser");
		}
		
		driver.get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();
		
		return driver;
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

}
