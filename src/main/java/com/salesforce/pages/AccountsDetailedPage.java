package com.salesforce.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.TestBase;

public class AccountsDetailedPage extends TestBase {

	public AccountsDetailedPage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//li[@class='slds-tabs_default__item']//a[@id='detailTab__item']")
	WebElement detailsTab;
	
	@FindBy(xpath = "//span[text()='Account Name']/parent::div/following-sibling::div//lightning-formatted-text")
	WebElement accountName;
	
	@FindBy(xpath = "//span[text()='Type']/parent::div/following-sibling::div//lightning-formatted-text")
	WebElement type;
	
	@FindBy(xpath = "//span[text()='Website']/parent::div/following-sibling::div//a")
	WebElement website;
	
	@FindBy(xpath = "//span[text()='Parent Account']/parent::div/following-sibling::div//a//span")
	WebElement parentAccount;
	
	@FindBy(xpath = "//span[text()='Description']/parent::div/following-sibling::div//lightning-formatted-text")
	WebElement description;
	
	@FindBy(xpath = "//span[text()='Industry']/parent::div/following-sibling::div//lightning-formatted-text")
	WebElement industry;
	
	@FindBy(xpath = "//span[text()='Employees']/parent::div/following-sibling::div//lightning-formatted-number")
	WebElement employees;
	
	@FindBy (xpath = "(//div[@class='slds-truncate'])[1]")
	WebElement address;

	public WebElement getDetailsTab() {
		return detailsTab;
	}

	public WebElement getAccountName() {
		return accountName;
	}

	public WebElement getType() {
		return type;
	}

	public WebElement getDescription() {
		return description;
	}

	public WebElement getWebsite() {
		return website;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getEmployees() {
		return employees;
	}

	public WebElement getAddress() {
		return address;
	}
	
	public WebElement getParentAccount() {
		return parentAccount;
	}
	
	

	
	

}
