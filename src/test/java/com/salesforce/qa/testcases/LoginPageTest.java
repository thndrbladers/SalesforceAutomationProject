package com.salesforce.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.DashboardPage;
import com.salesforce.qa.pages.HomePage;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.util.NALExcelXLSReader;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
		
	}
	
	@Test(groups={"sanity","regression","positive"})
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Login | Salesforce");
		
	}
	
	@Test(groups={"sanity","regression","positive"})
	public void loginPageLogoTest() {
		Assert.assertEquals(loginPage.getSalesforceLogo(), true);;
		
	}
	
	@Test(groups={"sanity","regression","positive"})
	public void validateLogin() {
		dashboardPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(driver.getTitle(), "Home | Salesforce");
		
	}
	
	@Test(groups={"sanity","regression","negative"},dataProvider="loginTestData")
	public void validateLoginNegativeScenarios(String username,String password) {
		SoftAssert softAssert=new SoftAssert();
		dashboardPage=loginPage.login(username,password);
		softAssert.assertNotEquals(driver.getTitle(), "Home | Salesforce");
		softAssert.assertAll();
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		close();
		
	}
	
	@DataProvider(name="loginTestData")
	public Iterator<Object[]> getLoginTestData(){
		List<Object[]> list=new ArrayList<>();
		NALExcelXLSReader nal=new NALExcelXLSReader(prop.getProperty("SalesforceProjectTestDataPath"));
		
		String sheetName="LoginTestData";
		int rowSize=nal.getRowCount(sheetName);
		System.out.println(rowSize);
		
		for(int i=0;i<rowSize;i++) {
			list.add(new Object[] {nal.getCellData(sheetName, "username", i),nal.getCellData(sheetName, "password", i)});
		}
		
		return new ArrayList<Object[]>(list).iterator();
		
	}

}
