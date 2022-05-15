package com.salesforce.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.base.TestBase;
import com.salesforce.pages.DashboardPage;
import com.salesforce.pages.LoginPage;
import com.salesforce.util.NALExcelXLSReader;

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
	
	
	@Test(groups={"sanity","regression"},dataProvider="loginTestData")
	public void validateLoginNegativeScenarios(String username,String password) {
		//Wait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		SoftAssert softAssert=new SoftAssert();
		dashboardPage=loginPage.login(username,password);
		if((username.equals(prop.getProperty("username"))) && (password.equals(prop.getProperty("password")))) {
			softAssert.assertNotEquals(dashboardPage.getDashboardPageTitle(), "Login | Salesforce");
		}else {
			softAssert.assertEquals(dashboardPage.getDashboardPageTitle(), "Login | Salesforce");
		}
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