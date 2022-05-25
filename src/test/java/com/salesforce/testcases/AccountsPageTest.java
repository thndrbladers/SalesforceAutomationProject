package com.salesforce.testcases;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.salesforce.base.TestBase;
import com.salesforce.pages.AccountsDetailedPage;
import com.salesforce.pages.AccountsPage;
import com.salesforce.pages.DashboardPage;
import com.salesforce.pages.LoginPage;
import com.salesforce.util.NALExcelXLSReader;

public class AccountsPageTest extends TestBase {

	DashboardPage dashboardPage;
	LoginPage loginPage;
	AccountsPage accountsPage;
	AccountsDetailedPage accountsDetailedPage;

	public AccountsPageTest() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		accountsPage = dashboardPage.clickOnAccountsTab();

	}

	@Test(groups = {"sanity","regression"}, dataProvider= "newAccountData")
	public void verifyUserIsAbleToCreateNewAccount(String accountName, String type, String website, String description,
			String parentAccount, String phone, String industry, String employees, String billingAddress) {

		accountsDetailedPage=accountsPage.createNewAccount(accountName, type, website, description, parentAccount, phone, industry,
				employees, billingAddress);
		
		
		
		accountsDetailedPage.getDetailsTab().click();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(accountsDetailedPage.getAccountName().getText(), accountName);
		softAssert.assertEquals(accountsDetailedPage.getType().getText(), type);
		softAssert.assertEquals(accountsDetailedPage.getDescription().getText(), description);
		softAssert.assertEquals(accountsDetailedPage.getWebsite().getText(), website);
		softAssert.assertEquals(accountsDetailedPage.getParentAccount().getText(), parentAccount);
		softAssert.assertEquals(accountsDetailedPage.getIndustry().getText(), industry);
		softAssert.assertEquals(accountsDetailedPage.getEmployees().getText(), employees);
//		softAssert.assertEquals(accountsDetailedPage.getAddress().getText(), billingAddress);

		softAssert.assertAll();
	

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		close();
	}

	@DataProvider(name = "newAccountData")
	public Object[][] getNewAccountData() {
		NALExcelXLSReader nal = new NALExcelXLSReader(
				System.getProperty("user.dir") + prop.getProperty("SalesforceProjectTestDataPath"));
		int rowSize = nal.getRowCount("NewAccountData");

		int colSize = nal.getColumnCount("NewAccountData");

		Object[][] data = new Object[rowSize - 1][colSize];

		for (int i = 2; i <= rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				data[i - 2][j] = nal.getCellData("NewAccountData", j, i);
			}
		}
		return data.clone();
	}

}
