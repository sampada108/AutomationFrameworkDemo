package demo.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.base.TestBase;

public class DeleteCustomerPage extends TestBase {
	
	// Page Factory - Page Object Repository
	@FindBy(xpath = "// input[@name='cusid']")
	WebElement txtCustomerId;

	@FindBy(xpath = "// input[@name='AccSubmit']")
	WebElement btnSubmit;

	// Initializing page objects
	public DeleteCustomerPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions on page objects
	public void validateDeleteCustomer(String custid) {
		txtCustomerId.sendKeys(custid);
		btnSubmit.click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		alert.accept();
	}
}
