package demo.testcases;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import demo.base.TestBase;
import demo.pages.AddNewCustomerPage;
import demo.pages.LoginPage;
import demo.pages.LogoutPage;
import demo.pages.ManagerPage;
import demo.util.TestUtil;

public class ManagerPageTests extends TestBase {

	public static final Logger log = LogManager.getLogger(ManagerPageTests.class.getName());
	LoginPage loginpage;
	ManagerPage managerpage;
	AddNewCustomerPage newcustomerpage;
	LogoutPage logoutpage;
			
	public ManagerPageTests() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		managerpage = loginpage.validateLogin(prop.getProperty("userId"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void ManagerPageTitleTest() {
		String title = managerpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank Manager HomePage");
		log.info("Validated Manager page title");
	}

	@Test(priority = 2)
	public void verifyManagerIdTest() throws IOException {
		String mId = managerpage.getmanegerId();
		Assert.assertTrue(mId.contains(prop.getProperty("userId")), "Manger ID on manager home page  not matching");
		log.info("Verified Manager ID with the userID" + mId);
		//TestUtil.takeScreenshot("verifyManagerIdTest_Pass");
		log.info("Screenshot taken after verification of Manager ID.");
	}

	@Test(priority = 3)
	public void verifyAllLinksTest() {
		Object[][] expLinknames = TestUtil.getTestData("LinkNamesTestdata");

		List<WebElement> actLinkNames = driver.findElements(By.xpath("//ul[@class='menusubnav']/li"));
		System.out.println(actLinkNames.size());

		for (int i = 0; i < expLinknames.length; i++)
			Assert.assertTrue(expLinknames[i][0].equals(actLinkNames.get(i).getText()), "Test Failed");
		log.info("Verified al links present on Manager home page.");
	}

	@Test(priority = 4)
	public void verifynewCustomerlinkTest() {
		newcustomerpage = managerpage.clickNewCustomerLink();
		log.info("Clicking on New Cutomer link");
		String title = newcustomerpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank New Customer Entry Page");
		log.info("Validated Add New Cutomer page title");
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
