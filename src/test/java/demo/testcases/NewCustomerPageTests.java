package demo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.base.TestBase;
import demo.pages.AddNewCustomerPage;
import demo.pages.DeleteCustomerPage;
import demo.pages.LoginPage;
import demo.pages.LogoutPage;
import demo.pages.ManagerPage;
import demo.util.TestUtil;

public class NewCustomerPageTests extends TestBase {

	LoginPage loginpage;
	ManagerPage managerpage;
	AddNewCustomerPage newcustomerpage;
	DeleteCustomerPage deletecustomerpage;
	LogoutPage logoutpage;

	public NewCustomerPageTests() {
		super();
	}
	

	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		managerpage = loginpage.validateLogin(prop.getProperty("userId"), prop.getProperty("password"));
		newcustomerpage = managerpage.clickNewCustomerLink();
	}

	@Test(priority = 1)
	public void NewCustomerPageTitleTest() {
		String title = newcustomerpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank New Customer Entry Page");
	}

	@Test(priority = 2, dataProvider = "getNewCutomerData")
	public void validateNewCutomerCreationTest(String name, String gender, String birthDate, String address,
			String city, String state, String pin, String mobileNo, String emailid, String password) {

		newcustomerpage.AddNewCustomer(name, gender, birthDate, address, city, state, pin, mobileNo, emailid, password);

		boolean bflag = newcustomerpage.submitForm();

		Assert.assertTrue(bflag);
		System.out.println("New customer created.");
		
		
		String custid = newcustomerpage.getCustomerID();
		System.out.println("New customer created with ID " + custid);
		
		//Delete customer
		newcustomerpage.clickLinkHome();
		
		//managerpage.clickNewCustomerLink();
		deletecustomerpage = managerpage.clickDeleteCustomerLink();
		deletecustomerpage.validateDeleteCustomer(custid);
		newcustomerpage.clickLinkHome();
	}

	@DataProvider
	public Object[][] getNewCutomerData() {
		return TestUtil.getTestData("NewUserTestdata");
	}

	@AfterMethod
	public void teardown() {
		logoutpage = managerpage.clickLogoutLink();
		logoutpage.Logout();
		driver.quit();
		System.out.println("Logout from application.");
	}
}
