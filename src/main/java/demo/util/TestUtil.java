package demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import demo.base.TestBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestUtil extends TestBase {

	public static String TestdataSheetPath = filePathUserDir + "\\src\\main\\java\\demo\\testdata\\BankTestdata.xlsx";
	static Workbook workbook;
	static Sheet sheet;
	public static ExtentReports extent;

	public static Object[][] getTestData(String sheetName) {

		int numberOfRows;
		int numberOfColumns;
		FileInputStream file = null;

		try {
			file = new FileInputStream(TestdataSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// Factory for creating the appropriate kind of Workbook
			// (be it HSSFWorkbook(for .xls extension) or XSSFWorkbook(for .xlsx
			// extension)),
			// by auto-detecting from the supplied input.
			workbook = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);

		numberOfRows = sheet.getLastRowNum();
		numberOfColumns = sheet.getRow(0).getLastCellNum();

		// System.out.println("numberOfRows = " +numberOfRows);
		// System.out.println("numberOfColumns = " + numberOfColumns);

		Object[][] data = new Object[numberOfRows][numberOfColumns];

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	public static String takeScreenshot(WebDriver driver, String testMethodName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String DesFilepath = filePathUserDir + "/screenshots/" + testMethodName+"_Screenshot.png";
		FileUtils.copyFile(scrFile, new File(DesFilepath));
		return DesFilepath;
	}

	public static ExtentReports configureExtendReport() {
		
		// Report path is given to object of ExtentSparkReporter class
		String reportPath = filePathUserDir + "/Reports/TestReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		// Configure reporter reference with title and ResourceCDN of document
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setResourceCDN("Web Auotmation Report");
		
		// Create object of ExtentReports and attache reporter to ExtentReports object
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		// Set System Info - Tester , Environment etc.
		extent.setSystemInfo("Tester", "Sampada");
		extent.setSystemInfo("Environment", "Test");
		return extent;
	}

}
