package com.salesforce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.salesforce.base.TestBase;
import com.salesforce.pages.DashboardPage;
import com.salesforce.pages.LeadsPage;
import com.salesforce.pages.LoginPage;

public class LeadsPageTest extends TestBase {
	
	DashboardPage dashboardPage;
	LeadsPage leadsPage;
	LoginPage loginPage;
	
	public LeadsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		leadsPage = dashboardPage.clickOnLeadsTab();
		
	}
	
	@Test
	public void testNewLeadStatus() {
		leadsPage.clickOnallOpenLeadsButton();
		Assert.assertEquals(leadsPage.verifyLeadStatus("John Steele (Sample)", "New"), true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void tearDown(){
		close();
	}

}
