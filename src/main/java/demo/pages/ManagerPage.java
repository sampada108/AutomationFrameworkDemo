package demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.base.TestBase;

public class ManagerPage extends TestBase {

	// Page Factory - Page Object Repository
	@FindBy(xpath = "//td[contains(text(), 'Manger Id :')]")
	WebElement lblManagerId;

	@FindBy(xpath = "//a[contains(text(), 'New Customer')]")
	WebElement lnkNewCustomer;

	// @FindBy(xpath = "//a[contains(text(), 'Delete Customer')]")
	@FindBy(xpath = "//a[@href= 'DeleteCustomerInput.php']")
	WebElement lnkDeleteCustomer;

	@FindBy(xpath = "//a[contains(text(), 'Log out')]")
	WebElement lnkLogOut;

	// Initializing page objects
	public ManagerPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions on page objects
	public String validateTitle() {
		return driver.getTitle();
	}

	public String getmanegerId() {
		return lblManagerId.getText();
	}

	public AddNewCustomerPage clickNewCustomerLink() {
		lnkNewCustomer.click();
		return new AddNewCustomerPage();
	}

	public DeleteCustomerPage clickDeleteCustomerLink() {
		lnkDeleteCustomer.click();
		return new DeleteCustomerPage();
	}

	public LogoutPage clickLogoutLink() {
		lnkLogOut.click();
		return new LogoutPage();
	}
}
