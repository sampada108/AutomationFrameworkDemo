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

import demo.base.TestBase;

public class TestUtil extends TestBase{
	
	public static String TestdataSheetPath = 
			filePathUserDir + "\\src\\main\\java\\demo\\testdata\\BankTestdata.xlsx";
	static Workbook workbook;
	static Sheet sheet;
	
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
			//Factory for creating the appropriate kind of Workbook 
			//(be it HSSFWorkbook(for .xls extension) or XSSFWorkbook(for .xlsx extension)), 
			//by auto-detecting from the supplied input.
			workbook = WorkbookFactory.create(file);   
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		
		numberOfRows = sheet.getLastRowNum();
		numberOfColumns = sheet.getRow(0).getLastCellNum();
		
		//System.out.println("numberOfRows = " +numberOfRows);
		//System.out.println("numberOfColumns = " + numberOfColumns);
		
		Object[][] data = new Object[numberOfRows][numberOfColumns];
	

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				//System.out.println(data[i][j]);
			}
		}
		return data;
	}
	

	public static void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePathUserDir + "/screenshots/" + "Screenshot.png"));
	}
	
}
