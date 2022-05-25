package com.salesforce.util;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.salesforce.base.TestBase;

public class CustomListener extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExcelReport.result(result.getName(),result.getParameters().toString(), "Pass",result.getThrowable().toString());
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExcelReport.result(result.getName(),result.getParameters().toString(), "Fail",result.getThrowable().toString());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExcelReport.result(result.getName(),result.getParameters().toString(), "Skipped",result.getThrowable().toString());
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExcelReport.result(result.getName(),result.getParameters().toString(), "Skipped",result.getThrowable().toString());
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ExcelReport.result(result.getName(),result.getParameters().toString(), "FailedWithTimeout",result.getThrowable().toString());
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test execution started");
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ExcelReport.write();
		System.out.println("Test execution Finished");
		ITestListener.super.onFinish(context);
	}

}