package com.salesforce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.TestBase;

public class LoginPage extends TestBase {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Object Repository
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="Login")
	WebElement login;
	
	@FindBy(id="rememberUn")
	WebElement rememberMe;
	
	@FindBy(linkText = "Forgot Your Password?")
	WebElement forgotYourPassword;
	
	@FindBy(id="logo")
	WebElement logo;
	
	@FindBy(id="footer")
	WebElement footer;
	
	//Actions
	
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean getSalesforceLogo() {
		return logo.isDisplayed();
	}
	
	public DashboardPage login(String uName,String pWord) {
		username.sendKeys(uName);
		password.sendKeys(pWord);
		rememberMe.click();
		login.click();
		
		return new DashboardPage();
		
		
	}
	
}
