package com.comcast.crm.orgtest;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithPhoneNumberTest_3rd_test_case {

	public static void main(String[] args) throws Throwable {
		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		
		//geting data from properties file
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		
		//geting data from excel file
		ExcelUtility elib=new ExcelUtility();

		String orgname=elib.getDataFromExcel("org", 4, 2)+jlib.randomNumber();
		String industry=elib.getDataFromExcel("org", 4, 3);
		String type=elib.getDataFromExcel("org", 4, 4);
		
		
		WebDriver driver=null;
		
		if(BROWSER.equals("chrome")) {
		driver=	new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")){
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}

		wlib.waitForPageToLoad(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		
		WebElement wbsel=driver.findElement(By.name("industry"));
		Select sel1=new Select(wbsel);
		sel1.selectByVisibleText(industry);
		
		WebElement wbsel2=driver.findElement(By.name("accounttype"));
		Select sel2=new Select(wbsel2);
		sel2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actIndustriesName=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustriesName.equals(industry)) {
			System.out.println(industry+"  "+"information is verified==PASS==");
		}
		else{
			System.out.println(industry+"  "+"information is not verified==FAIL==");
		}
		String actindustrytype=driver.findElement(By.id("dtlview_Type")).getText();
		if(actindustrytype.equals(type)) {
			System.out.println(type+"  "+"information is verified==PASS==");
		}
		else {
			System.out.println(type+"  "+"information is not verified==FAIL==");

		}
		driver.quit();
	}

}
