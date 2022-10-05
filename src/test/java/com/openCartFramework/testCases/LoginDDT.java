package com.openCartFramework.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.openCartFramework.pageObjects.LoginPage;
import com.openCartFramework.utilities.XLUtils;

public class LoginDDT extends BaseClass
{	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("UserName provided");
		
		lp.setPassword(pwd);
		logger.info("Password provided");
		
		lp.clickLoginBtn();
		logger.info("Login Button Clicked");
		
		Thread.sleep(3);
				
		if(isLogoutPresent()==true) 
		{
			logger.info("Login Passed");
			driver.findElement(By.xpath("(//a[text()='Logout'])[2]")).click();//logout
			driver.navigate().refresh();
			driver.findElement(By.xpath("(//a[text()='Login'])[2]")).click(); //login
			
		}
		else
		{
			logger.warn("Login Failed");
			driver.navigate().refresh();
			driver.findElement(By.xpath("(//a[text()='Login'])[2]")).click(); //login
		}
	}
	
	public boolean isLogoutPresent()
	{
		try
		{
			WebElement log = driver.findElement(By.xpath("(//a[text()='Logout'])[2]"));//logout
			if(log.isDisplayed())
				{
					return true;
				}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String peth = System.getProperty("user.dir") + "/src/test/java/com/openCartFramework/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(peth, "Sheet1");
		int colnum = XLUtils.getCellCount(peth, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colnum];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colnum; j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(peth, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
