package demo.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static String filePathUserDir = System.getProperty("user.dir");
	static long pageLoadTimeout = 40;
	static long implicitWaitTimeout = 20;
	public static WebDriverWait wait ;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream isconfig = new FileInputStream(
					filePathUserDir + "\\src\\main\\java\\demo\\config\\config.properties");
			prop.load(isconfig);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					filePathUserDir + "\\src\\main\\java\\demo\\resources\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("fireFox")) {
			System.setProperty("webdriver.gecko.driver",
					filePathUserDir + "\\src\\main\\java\\demo\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestBase.pageLoadTimeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeout));
		
		driver.get(prop.getProperty("url"));
	}

}
