package demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory - Page Object Repository
	@FindBy(xpath = "//input[@name='uid' and @type='text']")
	WebElement txtUserId;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@type='submit' and @value='LOGIN']")
	WebElement btnLogin;

	// Initializing page objects
	public LoginPage() {
		PageFactory.initElements(driver, this); // pointing to this current class object
	 // PageFactory.initElements(driver, LoginPage.class);  
	}

	// Actions on page objects
	public String validateTitle() {
		return driver.getTitle();
	}

	public ManagerPage validateLogin(String un, String pwd) {
		txtUserId.clear();
		txtUserId.sendKeys(un);
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
		btnLogin.click();
	
		return new ManagerPage();
	}
	

}
