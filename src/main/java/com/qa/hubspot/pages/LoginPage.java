package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. By locators
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//2.Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page Actions method
	@Step("get Login page title")
	
	public String getLoginPageTitle(){
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("Check signup link on login page")
	public boolean checkSignUpLink(){
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	@Step("login with : username : {0} and password : {1}")
	public HomePage doLogin(String un, String pwd){
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
				
		return new HomePage(driver);
	}

}
