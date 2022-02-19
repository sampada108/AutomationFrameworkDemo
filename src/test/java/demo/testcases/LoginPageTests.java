package demo.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public static final Logger log = LogManager.getLogger(LoginPageTests.class.getName());
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
		log.info("Validated login page title.");
	}

	@Test(priority = 2, dataProvider = "getLoginTestdata")
	public void loginWithInvalidData(String userId, String password, String errorMsg) {
		managerpage = loginpage.validateLogin(userId, password);

		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			Assert.assertEquals(alertText, errorMsg);
			alert.accept();
			log.info("Validated login with invalid user name or password");

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
		log.info("Login in application");
		String title = managerpage.validateTitle();
		Assert.assertEquals(title, "Guru99 Bank Manager HomePage");
		log.info("Validated Manager page title.");
		logoutpage = managerpage.clickLogoutLink();
		logoutpage.Logout();
		log.info("Logout from application");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		log.info("Closed all browsers.");
	}
}
