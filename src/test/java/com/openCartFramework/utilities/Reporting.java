package com.openCartFramework.utilities;

// Listeners class to generate extent report 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Reporting extends TestListenerAdapter
{
    //public ExtentHtmlReporter htmlReporter;

    // ExtentSparkReporter, ExtentReports, ExtentTest class are coming from "extentReport"

    public ExtentSparkReporter htmlReporter;  // look and feel of the report
    public ExtentReports extent;  // sending common information to the report
    public ExtentTest test;  // sending PASS or FAIl message to the report


    public void onStart(ITestContext testContext)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
    	String repName = "Test-Report" + timeStamp + ".html";
    	htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/"+repName);

//    	try {
//			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
        htmlReporter.config().setDocumentTitle("openCart Automation Report");
        htmlReporter.config().setReportName("openCart Testing Report");
        htmlReporter.config().setTheme(Theme.STANDARD);


        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Suraj");

    }

    public void onTestSuccess(ITestResult result)
    {
        test = extent.createTest(result.getName());  // create a new entry in the report
        
        test.log(Status.PASS,"Test Case PASSED is " + result.getName());
    }

    public void onTestFailure(ITestResult result)
    {
        test = extent.createTest(result.getName());  // create a new entry in the report

        test.log(Status.FAIL,"Test Case FAILED is " + result.getName());  // to add name in extent report
        test.log(Status.FAIL,"Test Case FAILED is " + result.getThrowable());  // to add errors/exception in report
        
        String screenShotPath = System.getProperty("user.dir") + "\\Screenshot\\"+result.getName()+".png";
        
        File f = new File(screenShotPath);
        if(f.exists())
        {
        	try {
            	test.fail("Screenshot is below" + test.addScreenCaptureFromPath(screenShotPath));
        		}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }

    public void onTestSkipped(ITestResult result)
    {
        test = extent.createTest(result.getName());  // create a new entry in the report

        test.log(Status.SKIP,"Test Case SKIPPED is " + result.getName());  // to add name in extent report

    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }


}
