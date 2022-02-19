package demo.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import demo.base.TestBase;

public class Listeners extends TestBase implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = TestUtil.configureExtendReport();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		String screenshotFilePath = null;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			screenshotFilePath = TestUtil.takeScreenshot(driver, testMethodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().log(Status.FAIL, "Test Failed." + test.addScreenCaptureFromPath(screenshotFilePath));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable());
		extentTest.get().log(Status.SKIP, "Test Skipped");		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
