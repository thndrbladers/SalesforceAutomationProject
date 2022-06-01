package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.TestBase;

public class LeadsPage extends TestBase {
	Actions action;
	public LeadsPage() {
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
	}
	
	@FindBy(xpath="//button[@title='Select a List View']")
	WebElement recentlyViewedArrow;
	
	@FindBy(xpath="//span[normalize-space()='All Open Leads']")
	WebElement allOpenLeadsButton;
	
	public void clickOnallOpenLeadsButton() {
		action.moveToElement(recentlyViewedArrow).click().build().perform();
		allOpenLeadsButton.click();
	}
	
	public boolean verifyLeadStatus(String name, String expectedStatus) {
		String status=driver.findElement(By.xpath("//a[normalize-space()='"+name+"']/ancestor::th/following-sibling::td[6]/span/span[1]")).getText();
		if(status.equals(expectedStatus)) {
			return true;
		}else {
			return false;
		}
	}

}
