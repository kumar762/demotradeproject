package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utilities.BaseClass;

public class LoginPage extends BaseClass{
	//elements
	
		@FindBy(name="txtUserName")WebElement Login_username;
		@FindBy(name="txtPassword")WebElement Login_password;
		@FindBy(name="Submit")WebElement Login_login;
		@FindBy(name="clear")WebElement Login_clear;
		
	//constructor
		
		public LoginPage ()
		{
			PageFactory.initElements(driver, this);
		}
		
		//login method
		
		public void login(String uname,String pwd)
		{
			Login_username.sendKeys(uname);
			Login_password.sendKeys(pwd);
			Login_login.click();
		}

}
