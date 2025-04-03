package com.comcast.crm.leadtest;

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

public class CreateLeadswithIndustry {

	public static void main(String[] args) throws Throwable {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		JavaUtility jlib= new JavaUtility();
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		String lastname=elib.getDataFromExcel("leads",4 , 2)+jlib.randomNumber();
		String company=elib.getDataFromExcel("leads", 4, 3)+jlib.randomNumber();
		String industry=elib.getDataFromExcel("leads", 4, 4);

		
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
		
		//click on leads
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.name("company")).sendKeys(company);
		WebElement websel=driver.findElement(By.name("industry"));
		Select sel=new Select(websel);
		sel.selectByVisibleText(industry);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actIndustriesName=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustriesName.equals(industry)) {
			System.out.println(industry+"  "+"information is verified==PASS==");
		}
		else{
			System.out.println(industry+"  "+"information is not verified==FAIL==");
		}
		
		String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(lastname)) {
			System.out.println(lastname+"  "+"header verified==PASS==");
		}else {
			System.out.println(lastname+"  "+"header not verified==FAIL==");

		}

		driver.quit();
		
		
	}

}
