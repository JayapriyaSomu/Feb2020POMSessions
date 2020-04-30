package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class LoginPageTest {

	BasePage basePage;
	LoginPage loginPage;
	
	Properties prop;
	WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.int_prop();
		driver = basePage.int_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Title is not found");		
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest(){
		Assert.assertTrue(loginPage.checkSignUpLink(), "Sign up link not present");
	}
	
	@Test(priority=3)
	public void verifyLoginTest(){
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Account name from home page is "  + homePage.getAccountName());
		System.out.println("Account name from config file is : " + prop.getProperty("accountname"));
		
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountname"), "Login failed");
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
