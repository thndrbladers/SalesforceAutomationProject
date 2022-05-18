package com.salesforce.testcases;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
		
	}
	
	@Test(groups={"sanity","regression"})
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Login | Salesforce");
		
	}
	
	@Test(groups={"sanity","regression"})
	public void validateLogin() {
		String username=prop.getProperty("username");
		String password=prop.getProperty("password");
		dashboardPage=loginPage.login(username,password);
		Assert.assertNotEquals(dashboardPage.getDashboardPageTitle(), "Login | Salesforce");	
	}
	
	
	@Test(groups={"regression"},dataProvider="loginTestData")
	public void validateLoginNegativeScenarios(String username,String password) {
		//Wait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		SoftAssert softAssert=new SoftAssert();
		dashboardPage=loginPage.login(username,password);
		softAssert.assertEquals(dashboardPage.getDashboardPageTitle(), "Login | Salesforce");
		softAssert.assertAll();
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		close();
		
	}
	
	@DataProvider(name="loginTestData")
	public Iterator<Object[]> getLoginTestData(){
		List<Object[]> list=new ArrayList<>();
		NALExcelXLSReader nal=new NALExcelXLSReader(System.getProperty("user.dir")+prop.getProperty("SalesforceProjectTestDataPath"));
		
		String sheetName="LoginTestData";
		int rowSize=nal.getRowCount(sheetName);
		
		for(int i=0;i<rowSize;i++) {
			list.add(new Object[] {nal.getCellData(sheetName, "username", i),nal.getCellData(sheetName, "password", i)});
		}
		
		return new ArrayList<Object[]>(list).iterator();
		
	}

}
