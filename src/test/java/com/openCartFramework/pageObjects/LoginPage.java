package com.openCartFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver; // local driver
	
	public LoginPage(WebDriver driver)  // remote driver
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="email")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-lg hidden-xs']")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[@class='btn btn-black navbar-btn']")
	@CacheLookup
	WebElement logoutbtn;
	
	// Action Method
	public void setUserName(String userName)
	{
		txtUserName.sendKeys(userName);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginBtn()
	{
		btnLogin.click();
	}
	
	public void clickLogoutBtn()
	{
		logoutbtn.click();
	}
	
	
	
}
