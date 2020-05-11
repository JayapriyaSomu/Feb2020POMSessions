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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : design Login feature")
@Feature("US - 105 : design test cases for login page feature")
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
	@Description("Verify Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Title is not found");		
	}
	
	@Test(priority=2)
	@Description("Verify Sign up Page Test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest(){
		System.out.println("Verify Sign up Link Test");
		Assert.assertTrue(loginPage.checkSignUpLink(), "Sign up link not present");
	}
	
	@Test(priority=3)
	@Description("Verify User Login Page Test")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyLoginTest(){
		System.out.println("Verify Login Test");
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Account name from home page is "  + homePage.getAccountName());
		System.out.println("Account name from config file is : " + prop.getProperty("accountname"));
		
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountname"), "Login failed");
	}
	
	
	@AfterTest
	public void tearDown(){
		System.out.println("close the browser");
		driver.quit();
	}
	
	
	
	
}
