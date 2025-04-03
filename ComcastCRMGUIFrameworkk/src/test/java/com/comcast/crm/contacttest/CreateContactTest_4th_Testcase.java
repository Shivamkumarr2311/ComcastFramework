package com.comcast.crm.contacttest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactTest_4th_Testcase {

	public static void main(String[] args) throws Throwable {
		
		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		
		//getting data from excel file
		ExcelUtility elib=new ExcelUtility();

		String lastname=elib.getDataFromExcel("contact", 4, 2)+jlib.randomNumber();
		
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
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		
	    
	  String startdate= jlib.getSystemDateYYYYDDMM();
	    String endDate= jlib.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);

		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		
		if(actualStartDate.contains(startdate)) {
			System.out.println(startdate+"  "+"information verified==PASS==");
		}
		else{
			System.out.println(startdate+"  "+"information not verified==FAIL==");
		}
		String actualenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actualenddate.equals(endDate)) {
			System.out.println(endDate+"  "+"information verified==PASS==");
		}
		else{
			System.out.println(endDate+"  "+"information not verified==FAIL==");
		}
		
		driver.quit();
	}

}
