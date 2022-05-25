package com.salesforce.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.base.TestBase;
import com.salesforce.pages.DashboardPage;
import com.salesforce.pages.LoginPage;
import com.salesforce.util.NALExcelXLSReader;

@Listeners(com.salesforce.util.ExtentReporterNG.class)
public class DashboardPageTest extends TestBase {

	DashboardPage dashboardPage;
	LoginPage loginPage;

	public DashboardPageTest() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(groups = { "sanity","regression"})
	public void verifyAllTabsOnDashboardPage() {

		/*
		 * Robot robot = new Robot(); for (int i = 0; i < 2; i++) {
		 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_SUBTRACT);
		 * robot.keyRelease(KeyEvent.VK_SUBTRACT);
		 * robot.keyRelease(KeyEvent.VK_CONTROL); }
		 */
		
		SoftAssert softAssert=new SoftAssert();
		
		List<String> tabsTextActual = dashboardPage.getTextOfAllTabs();
		List<String> tabsTextExpected = new ArrayList<String>();

		NALExcelXLSReader nal = new NALExcelXLSReader(System.getProperty("user.dir")+prop.getProperty("SalesforceProjectTestDataPath"));

		int rowCount = nal.getRowCount("DashboardPageTabs");
		int colCount = nal.getColumnCount("DashboardPageTabs");

		for (int i = 2; i <= rowCount; i++) {
			tabsTextExpected.add(nal.getCellData("DashboardPageTabs", "dashboardPageTabs", i));
		}

		Collections.sort(tabsTextExpected);

		softAssert.assertEquals(tabsTextActual, tabsTextExpected);
		softAssert.assertAll();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		close();
	}

}
