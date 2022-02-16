package demo.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.base.TestBase;

public class AddNewCustomerPage extends TestBase {

	// Page Factory - Page Object Repository
	@FindBy(xpath = "//input[@name='name']")
	WebElement txtCustomerName;

	@FindBy(xpath = "//input[@value='m']")
	WebElement rbtnGenderMale;

	@FindBy(xpath = "//input[@value='f']")
	WebElement rbtnGenderFemale;

	@FindBy(xpath = "//input[@id='dob']")
	WebElement txtBirthDate;

	@FindBy(xpath = "//textarea[@name='addr']")
	WebElement txtAddress;

	@FindBy(xpath = "//input[@name='city']")
	WebElement txtCity;

	@FindBy(xpath = "//input[@name='state']")
	WebElement txtState;

	@FindBy(xpath = "//input[@name='pinno']")
	WebElement txtPinNo;

	@FindBy(xpath = "//input[@name='telephoneno']")
	WebElement txtMobileNo;

	@FindBy(xpath = "//input[@name='emailid']")
	WebElement txtEmailid;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnSubmit;

	@FindBy(xpath = "//P[@class='heading3' and contains(text(), 'Customer Registered Successfully!!!')]")
	WebElement msgSuccess;

	@FindBy(xpath = "//td[contains(text(), 'Customer ID')]/following-sibling::td")
	WebElement txtCutomerID;
	
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement lnkHome;

	// Initializing page objects
	public AddNewCustomerPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions on page objects
	public String validateTitle() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleIs("Guru99 Bank New Customer Entry Page"));
		return driver.getTitle();
	}

	public void AddNewCustomer(String name, String gender, String birthDate, String address, String city, String state,
			String pin, String mobileNo, String emailid, String password) {

		txtCustomerName.clear();
		txtCustomerName.sendKeys(name);

		if ((!rbtnGenderMale.isSelected()) && gender.equalsIgnoreCase("male"))
			rbtnGenderMale.click();
		if ((!rbtnGenderFemale.isSelected()) && gender.equalsIgnoreCase("female"))
			rbtnGenderFemale.click();

		txtBirthDate.sendKeys(birthDate);
		txtBirthDate.sendKeys(Keys.TAB);

		txtAddress.clear();
		txtAddress.sendKeys(address);

		txtCity.clear();
		txtCity.sendKeys(city);

		txtState.clear();
		txtState.sendKeys(state);

		txtPinNo.clear();
		txtPinNo.sendKeys(pin);

		txtMobileNo.clear();
		txtMobileNo.sendKeys(mobileNo);

		txtEmailid.clear();
		txtEmailid.sendKeys(emailid);

		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	public boolean submitForm() {
		btnSubmit.click();
		return msgSuccess.isDisplayed();
	}

	public void clickLinkHome() {
		lnkHome.click();
	}
	public String getCustomerID() {
		return txtCutomerID.getText().trim();
	}

}
