package demo.testcases;

import java.io.IOException;
import java.util.List;

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
	}

	@Test(priority = 2)
	public void verifyManagerIdTest() throws IOException {
		String mId = managerpage.getmanegerId();
		System.out.println(mId);
		Assert.assertTrue(mId.contains(prop.getProperty("userId")), "Manger ID on manager home page  not matching");
		TestUtil.takeScreenshot();
	}

	@Test(priority = 3)
	public void verifyAllLinksTest() {
		Object[][] expLinknames = TestUtil.getTestData("LinkNamesTestdata");

		List<WebElement> actLinkNames = driver.findElements(By.xpath("//ul[@class='menusubnav']/li"));
		System.out.println(actLinkNames.size());

		for (int i = 0; i < expLinknames.length; i++)
			Assert.assertTrue(expLinknames[i][0].equals(actLinkNames.get(i).getText()), "Test Failed");
	}

	@Test(priority = 4)
	public void verifynewCustomerlinkTest() {
		newcustomerpage = managerpage.clickNewCustomerLink();
		String title = newcustomerpage.validateTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Guru99 Bank New Customer Entry Page");
	}

	@AfterMethod
	public void teardown() {
		logoutpage = managerpage.clickLogoutLink();
		logoutpage.Logout();
		System.out.println("Logout from application.");
		driver.quit();
	}

}
