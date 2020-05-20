package com.projectname.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Listeners extends TestListenerAdapter 
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	

	
	public void onStart(ITestContext testContext)
	
	{
		//htmlReporter =new ExtentHtmlReporter(System.getProperty("user.dir")+"./test-output/myReports.html");
		
		htmlReporter =new ExtentHtmlReporter("./report1/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Devesh");
	}
	public void onTestSuccess(ITestResult result)
	
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is "+result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case FAILLED is "+result.getName());
		test.log(Status.FAIL, "Test Case FAILLED is "+result.getThrowable());
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is "+result.getName());
	}
	
	public void onFinish(ITestResult result)
	{
		
		extent.flush();
	}
	
	
}
