package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class BaseClass {
	
	public static WebDriver driver;
	public static Actions action;
	public static Properties prop;
	public static EventFiringWebDriver edriver;

	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports reports;
	public static ExtentTest test;
	
	
	
	public BaseClass() 
	{
		try
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("D:\\Vinaykumar\\TradeMart\\src\\main\\java\\config\\config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
		}
	
	//intialize method
	public static void intializeMethod() throws IOException
	{
		String browsername=prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Vinaykumar\\TradeMart\\src\\main\\java\\resources\\chromedriver.exe");
			driver=new ChromeDriver();
			
		}
		
		edriver=new EventFiringWebDriver(driver);
		
		action=new Actions(driver);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	
	@BeforeSuite
	
	public void setupReport()
	{
		htmlreporter=new ExtentHtmlReporter("D:\\Vinaykumar\\TradeMart\\src\\main\\java\\reports\\report1.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlreporter);
	}
	
	@AfterMethod
	
	public void reportAftermethod()
	{
		reports.flush();
		driver.close();
	}
	
	//reusable methods
	
	public static void navigate(WebElement element)
	{
		action.moveToElement(element).click().build().perform();
	}
	
	
	public static void navigate(WebElement element,WebElement element1)
	{
		action.moveToElement(element).build().perform();
		action.moveToElement(element1).click().build().perform();
	}
	
	
	public static void navigate(WebElement element,WebElement element1,WebElement element2)
	{
		action.moveToElement(element).build().perform();
		action.moveToElement(element1).build().perform();
		action.moveToElement(element2).click().build().perform();
	}
	
	public static void selectOptions(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);;
	}
	
	public static void takeScreenShotAtEndOfTheTest() throws Throwable
	{
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String currentdir=System.getProperty("user.dir")+"//src//main//java//screenShots//";
		FileUtils.copyFile(srcfile, new File(currentdir+"screenshotss"+System.currentTimeMillis()+".png"));
	}

	
	

}
