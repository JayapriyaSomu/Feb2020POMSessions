package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.cssSelector("h1.private-page__title");
	By accountName = By.cssSelector("span.account-name ");
	
	By contactsLinkmain = By.id("nav-primary-contacts-branch");
	By contactsLinksecondary = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver){
		this.driver = driver;		
		elementUtil = new ElementUtil(driver);
	}

	@Step("get home page title....")
	public String getHomePageTitle(){
		return elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 10);
	
	}
	
	@Step("get home page header....")
	public String getHomePageHeader() {
		if(elementUtil.doIsDisplayed(header)){
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	@Step("get Account name on home page.....")
	public String getAccountName() {
		System.out.println("accountName is : " + accountName);
		elementUtil.waitForElementPresent(accountName, 10);
		if(elementUtil.doIsDisplayed(accountName)){
			return elementUtil.doGetText(accountName);
		} else {
		return null;
		}
	}
	
	@Step("go to Contacts page....")
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	private void clickOnContacts(){
		elementUtil.waitForElementPresent(contactsLinkmain, 10);
		elementUtil.doClick(contactsLinkmain);
		elementUtil.waitForElementPresent(contactsLinksecondary, 5);
		elementUtil.doClick(contactsLinksecondary);
	}
	
	
}
