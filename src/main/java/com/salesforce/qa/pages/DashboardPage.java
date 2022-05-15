package com.salesforce.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.qa.base.TestBase;

public class DashboardPage extends TestBase {
	
	Wait wait;
	
	public DashboardPage() {
		PageFactory.initElements(driver,this);
	}
	
	//Object Repository - Elements
	
	@FindBy(xpath="//span[normalize-space()='Home']")
	WebElement home;
	
	@FindBy(xpath="//span[normalize-space()='Accounts']")
	WebElement accounts;
	
	@FindBy(xpath="//span[normalize-space()='Contacts']")
	WebElement contacts;
	
	@FindBy(xpath="//span[normalize-space()='Tasks']")
	WebElement tasks;
	
	@FindBy(xpath="//div[@class='overlay homeHeroChartOverlay'][@data-aura-class='homeHeroChartOverlay']")
	WebElement quaterlyPerformanceChart;
	
	@FindBy(xpath="//a[@class='slds-context-bar__label-action dndItem']/span")
	List<WebElement> tabs;
	
	@FindBy(xpath="//span[@class='slds-p-right_small']")
	WebElement moreTab;
	
	@FindBy(xpath="//a[@role='menuitem']/span/span")
	List<WebElement> moreTabMenuItems;
	
	
	
	//Getters
	
	public WebElement getQuaterlyPerformanceChart() {
		return quaterlyPerformanceChart;
	}	
	
	//Actions

	public String getDashboardPageTitle() {
		return driver.getTitle();
	}
	
	public List<String> getTextOfAllTabs() {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		List<String> tabsText = new ArrayList<String>();
		if(moreTab.isDisplayed()) {
			moreTab.click();
			
			Iterator<WebElement> itr2=moreTabMenuItems.iterator();
			while(itr2.hasNext()) {
				tabsText.add(itr2.next().getText());
				
			}
			
		}
		
		Iterator<WebElement> itr=tabs.iterator();
		
		while(itr.hasNext()) {
			String temp=itr.next().getText();

			tabsText.add(temp);
		}
		tabsText.removeIf(String::isEmpty);
		
		Collections.sort(tabsText);
		
		return new ArrayList<String>(tabsText);
	}
	
	public ContactsPage clickOnContactsTab() {
		contacts.click();
		return new ContactsPage();
	}
	
	public AccountsPage clickOnAccountsTab() {
		accounts.click();
		return new AccountsPage();
	}
	
	public TasksPage clickOnTasksTab() {
		tasks.click();
		return new TasksPage();
	}
	
	
	
	
	

}
