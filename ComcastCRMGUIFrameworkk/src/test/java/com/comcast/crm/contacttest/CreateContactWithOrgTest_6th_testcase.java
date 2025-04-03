package com.comcast.crm.contacttest;
import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;


public class CreateContactWithOrgTest_6th_testcase {

	public static void main(String[] args) throws Throwable {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();


		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		
		
		//geting data from excel
		String orgname=elib.getDataFromExcel("contact", 7, 2)+jlib.randomNumber();
		String contactlastname=elib.getDataFromExcel("contact", 7, 3)+jlib.randomNumber();

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
		
		//login to app
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verifying the headerinfo
		
		
		String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+"  "+"header verified==PASS==");
		}
		else{
			System.out.println(orgname+"  "+"header not verified==FAIL==");
		}
		//navigate to conatct module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactlastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
		wlib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		
		//switch to parent window
		
		wlib.switchToTabOnTitle(driver, "Contacts&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		 headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(contactlastname)) {
			System.out.println(contactlastname+"  "+"header verified==PASS==");
		}
		else{
			System.out.println(contactlastname+"  "+"header not verified==FAIL==");
		}
		String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if(actOrgName.trim().equalsIgnoreCase(orgname)) {
			System.out.println(orgname+"  "+"information is created==PASS==");
		}
		else{
			System.out.println(orgname+"  "+"information is not created==FAIL==");
	}
		driver.quit();
		
	}
}
