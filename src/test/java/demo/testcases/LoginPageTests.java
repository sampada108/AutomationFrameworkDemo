package demo.testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.base.TestBase;
import demo.pages.LoginPage;
import demo.pages.LogoutPage;
import demo.pages.ManagerPage;
import demo.util.TestUtil;

public class LoginPageTests extends TestBase {

	LoginPage loginpage;
	ManagerPage managerpage;
	LogoutPage logoutpage;

	public LoginPageTests() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank Home Page");
	}

	@Test(priority = 2, dataProvider = "getLoginTestdata")
	public void loginWithInvalidData(String userId, String password, String errorMsg) {
		managerpage = loginpage.validateLogin(userId, password);

		try {

			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			Assert.assertEquals(alertText, errorMsg);
			alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] getLoginTestdata() {
		return TestUtil.getTestData("InvalidLoginTestdata");
	}
	
	@Test(priority = 3)
	public void loginWithValidData() {
		managerpage = loginpage.validateLogin(prop.getProperty("userId"), prop.getProperty("password"));

		String title = managerpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank Manager HomePage");
		logoutpage = managerpage.clickLogoutLink();
		logoutpage.Logout();
		System.out.println("Logout from Application.");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}	
}
