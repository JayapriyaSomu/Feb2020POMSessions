package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class ContactsPage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	By createContacts = By.xpath("(//span[text()='Create contact'])[1]");
	By createContactsForm = By.xpath("(//span[text()='Create contact'])[2]");
	By contactEmail = By.xpath("//input[@data-field='email']");
	By contactFirstName = By.xpath("//input[@data-field='firstname']");
	By contactLastName = By.xpath("//input[@data-field='lastname']");
	By contactJobTitle = By.xpath("//input[@data-field='jobtitle']");
	By contactNavigationLink	 = By.xpath("(//i18n-string[text()='Contacts'])[2]");

	public ContactsPage(WebDriver driver){
		this.driver = driver;		
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getContactsPageTitle(){
		return elementUtil.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}

	public String createNewContact(String email, String firstName, String lastName, String jobTitle){
		elementUtil.waitForElementToBeClickable(createContacts, 10);
		elementUtil.doClick(createContacts);
		elementUtil.waitForElementPresent(contactEmail, 5).sendKeys(email);
		elementUtil.waitForElementPresent(contactFirstName, 5).sendKeys(firstName);
		elementUtil.waitForElementPresent(contactLastName, 5).sendKeys(lastName);
		elementUtil.waitForElementPresent(contactJobTitle, 5).sendKeys(jobTitle);
		elementUtil.waitForElementToBeClickable(createContactsForm,15);
		jsUtil.clickElementByJS(elementUtil.getElement(createContactsForm));	
		
		String fullName = firstName+" " +lastName;
		String nameXpath =  "(//span[text()='"+fullName+"'])[2]";
						  
		elementUtil.waitForElementPresent(contactNavigationLink, 10);
		String contactName = elementUtil.doGetText(By.xpath(nameXpath)).trim();
		elementUtil.doClick(contactNavigationLink);		
		
		return contactName;
		
		
	}
	
	
}
