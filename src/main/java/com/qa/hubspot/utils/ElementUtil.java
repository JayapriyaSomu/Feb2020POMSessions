package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}

	public int getElements(By locator) {
		List<WebElement> listElements = driver.findElements(locator);
		int totalLinks = listElements.size();

		for (int i = 0; i < totalLinks; i++) {
			String linkList = listElements.get(i).getText();

			if (!linkList.isEmpty()) {
				System.out.println(i + "--->" + linkList);
				System.out.print(listElements.get(i).getAttribute("href"));
			}
		}

		return totalLinks;
	}
	

	public List<String> getElementsList(By locator) {
		
		List<String> ar = new ArrayList<String>();
		
		List<WebElement> listElements = driver.findElements(locator);
		int totalLinks = listElements.size();

		for (int i = 0; i < totalLinks; i++) {
			String linkList = listElements.get(i).getText();
			ar.add(linkList);
		}

		return ar;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();

	}

	public void signUp(By locator) {
		getElement(locator).click();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
		
	}

	public int totalLinkCount(By locator) {
		return getElements(locator);

	}

	/**
	 * This method is used to get list of Elements in the drop down based on the locator sent as input.
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownValues(By locator) {
		List<String> ar = new ArrayList<String>();
		WebElement element1 = getElement(locator);
		Select select = new Select(element1);
		List<WebElement> listOfElements = select.getOptions();
		for (int i = 0; i < listOfElements.size(); i++) {
			ar.add(listOfElements.get(i).getText());
		}
		return ar;
	}

	/**
	 * This method is used to select a value from the drop down based on the input parameter
	 * @param day
	 * @param value
	 */
	public void selectDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	/**
	 * This method is used to select a value by index from the drop down based on the input parameter
	 * @param locator
	 * @param value
	 */
	public void selectDropDown(By locator, int value) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(value);
	}

	/**
	 * This method is used to choose a value from the drop down without SELECT method.
	 * @param locator
	 * @param locatorValue
	 * @param value
	 */
	public void dropDownWithoutSelectmethod(String locator, String locatorValue,String value) {
		List<WebElement> valueList = null;
		if (locator.equals("xpath")) {
			valueList = driver.findElements(By.xpath(locatorValue));
		} else if (locator.equals("css")) {
			valueList = driver.findElements(By.cssSelector(locatorValue));

		}
		for (int i = 0; i < valueList.size(); i++) {
			if (valueList.get(i).getText().equals(value)) {
				valueList.get(i).click();
				break;
			}

		}

	}
	
	public void doRightClick(By locator, List<WebElement> rightClickOptions, String value) {

		Actions action = new Actions(driver);
		action.contextClick(getElement(locator)).build().perform();

		for (int i = 0; i < rightClickOptions.size(); i++) {
			String text = rightClickOptions.get(i).getText();
			System.out.println(text);

			if (text.equals(value)) {
				rightClickOptions.get(i).click();
				break;
			}
		}
	}

	
	public  List<String>  getRightClickList( By rightClickElement,List<WebElement> rightClickOptions) {

		List<String> rightClickOptionsList = new ArrayList<String>();
		
		Actions action = new Actions(driver);
		action.contextClick(getElement(rightClickElement)).build().perform();

		for (int i = 0; i < rightClickOptions.size(); i++) {
			String text = rightClickOptions.get(i).getText();
			System.out.println(text);
			rightClickOptionsList.add(text);
		}
		return rightClickOptionsList;

	}
	


//wait utility

	public WebElement waitForElementPresent(By locator, int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}
		
	public WebElement waitForElementToBeClickable(By locator, int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeoout){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	public WebElement waitForElementVisibilityLocated(By locator, int timeoout){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public String waitForUrl(String url, int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}
	
	public boolean waitForAlertToBePresent(int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	
	public void clickWhenReady(By locator, int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	
	public void visibilityOfAllElements(List<WebElement> elements, int timeoout) {
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	
	
	public String waitForTitlePresent(String title, int timeoout){
		WebDriverWait wait = new WebDriverWait(driver,timeoout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
		
	
	
	
	
	
		
} 