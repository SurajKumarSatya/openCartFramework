package com.openCartFramework.testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.openCartFramework.pageObjects.LoginPage;

public class LoginPageTest extends BaseClass
{	
	
	@Test
	public void loginTest() throws IOException
	{
		
		//driver.navigate().to(base);
		logger.info("URL is opened");
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setUserName(username);
		logger.info("Entered userName");
		
		loginPage.setPassword(password);
		logger.info("Entered password");
		
		loginPage.clickLoginBtn();
		if(driver.getTitle().equals("OpenCart - Account Login"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test is passed");
		}
		else
		{
			captureScreen(driver, "loginTest"); // in BaseClass
			Assert.assertTrue(false);
			logger.info("Login Test is failed");
		}
	}
}
