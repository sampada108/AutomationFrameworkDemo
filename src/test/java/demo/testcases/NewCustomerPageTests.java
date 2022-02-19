package demo.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public static final Logger log = LogManager.getLogger(NewCustomerPageTests.class.getName());
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
		log.info("Validated Add New Cutomer page title");
	}

	@Test(priority = 2, dataProvider = "getNewCutomerData")
	public void validateNewCutomerCreationTest(String name, String gender, String birthDate, String address,
			String city, String state, String pin, String mobileNo, String emailid, String password) {

		newcustomerpage.AddNewCustomer(name, gender, birthDate, address, city, state, pin, mobileNo, emailid, password);
		log.info("Entered values for all fields in the New Cutomer page.");
		
		boolean bflag = newcustomerpage.submitForm();
		log.info("Submitted form fille dwith values.");

		Assert.assertTrue(bflag);
		
		String custid = newcustomerpage.getCustomerID();
		log.info("New customer created with ID " + custid);
		
		//Delete customer
		newcustomerpage.clickLinkHome();
		log.info("Clicking on Home link.");
		
		//managerpage.clickNewCustomerLink();
		deletecustomerpage = managerpage.clickDeleteCustomerLink();
		log.info("Click on Delete Customer Link.");
		deletecustomerpage.validateDeleteCustomer(custid);
		log.info("Customer with ID " + custid + "is Deleted.");
		newcustomerpage.clickLinkHome();
		log.info("Clicking on Home link.");
	}

	@DataProvider
	public Object[][] getNewCutomerData() {
		return TestUtil.getTestData("NewUserTestdata");
	}

	@AfterMethod
	public void teardown() {
		logoutpage = managerpage.clickLogoutLink();
		log.info("Clicking on Logout link");
		logoutpage.Logout();
		log.info("Logout from application.");
		driver.quit();
		log.info("Closing all browsers");
	}
}
