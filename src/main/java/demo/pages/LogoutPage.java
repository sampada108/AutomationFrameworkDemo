package demo.pages;

import org.openqa.selenium.Alert;

import demo.base.TestBase;

public class LogoutPage extends TestBase {

	public void Logout() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
