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

@Epic("Epic - 102 : design homepage feature")
@Feature("US - 105 : design test cases for home page feature")
public class HomePageTest{
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	Properties prop;
	WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.int_prop();
		driver = basePage.int_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	@Description("Verify Home Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest(){
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is " +  title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	
	@Test(priority=2)
	@Description("Verify home page header Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest(){
		String header = homePage.getHomePageHeader();
		System.out.println("Home Page header is " + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	@Description("Verify user has logged in to the application")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoggedInUserTest(){
		String accountName = homePage.getAccountName();
		System.out.println("Logged in Account name is " + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
