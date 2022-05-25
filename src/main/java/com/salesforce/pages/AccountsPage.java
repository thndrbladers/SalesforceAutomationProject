package com.salesforce.pages;
import java.time.Duration;
import java.util.List;import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.TestBase;
import com.salesforce.util.TestUtil;

public class AccountsPage extends TestBase {

	public AccountsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@title='New']")
	WebElement newButton;

	@FindBy(xpath = "//span[text()='Account Name']/parent::label/following-sibling::div//input[@role='combobox']")
	WebElement accountNameInput;

	@FindBy(xpath = "//span[text()='Type']/parent::span/following-sibling::div")
	WebElement typeDropdown;

	@FindBy(xpath = "//div[12]/div[1]/ul[1]/li/a")
	List<WebElement> typeDropdownList;

	@FindBy(xpath = "//span[text()='Website']/parent::label/following-sibling::input")
	WebElement websiteInput;

	@FindBy(xpath = "//span[text()='Description']/parent::label/following-sibling::textarea")
	WebElement descriptionInput;

	@FindBy(xpath = "//span[text()='Parent Account']/parent::label/following-sibling::div//input")
	WebElement parentAccountInput;

	@FindBy(xpath = "(//mark[@class='data-match'])[1]")
	WebElement parentAccountDropdownOptions;

	@FindBy(xpath = "//span[text()='Phone']/parent::label/following-sibling::input")
	WebElement phoneNumber;

	@FindBy(xpath = "//span[text()='Industry']/parent::span/following-sibling::div")
	WebElement industryDropwdown;

	@FindBy(xpath = "((//div[contains(@data-proxy-id,'aura-pos-lib')]/div/ul)[2]//a")
	List<WebElement> industryDropwdownList;

	@FindBy(xpath = "//span[text()='Employees']/parent::label/following-sibling::input")
	WebElement employeesInput;

	@FindBy(xpath = "(//span[text()='Search Address'])[1]")
	WebElement searchAddressButton;

	@FindBy(xpath = "//input[@placeholder='Enter address']")
	WebElement enterBillingAddressInput;

	@FindBy(xpath = "(//li[@data-aura-class='uiAutocompleteOption forceAddressAutocompleteOption'])[1]")
	WebElement resultBillingAddressFirstOption;

	@FindBy(xpath = "//button[@title='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//a[@title='Accounts']")
	WebElement accountsTab;

	@FindBy(xpath = "(//table//tbody)[1]")
	WebElement recentlyViewedWebTable;

	public AccountsDetailedPage createNewAccount(String accountName, String type, String website, String description,
			String parentAccount, String phone, String industry, String employees, String billingAddress) {

		TestUtil.executeJavascriptClick(newButton);

		accountNameInput.sendKeys(accountName);
		typeDropdown.click();
		driver.findElement(By.xpath("//a[normalize-space()='" + type + "']")).click();
		websiteInput.sendKeys(website);
		descriptionInput.sendKeys(description);
		parentAccountInput.sendKeys(parentAccount);
		parentAccountDropdownOptions.click();
		phoneNumber.sendKeys(phone);
		industryDropwdown.click();
		driver.findElement(By.xpath("//a[normalize-space()='" + industry + "']")).click();
		employeesInput.sendKeys(employees);
//		searchAddressButton.click();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		enterBillingAddressInput.sendKeys(billingAddress);
//		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(resultBillingAddressFirstOption));
//		resultBillingAddressFirstOption.click();
		saveButton.click();
		
		return new AccountsDetailedPage();

	}

	public AccountsPage clickAccountsTab() {
		TestUtil.executeJavascriptClick(accountsTab);
		
		return new AccountsPage();
	}

}
