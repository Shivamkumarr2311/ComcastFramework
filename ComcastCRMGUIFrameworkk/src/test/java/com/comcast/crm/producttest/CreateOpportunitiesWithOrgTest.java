package com.comcast.crm.producttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOpportunitiesWithOrgTest {

	public static void main(String[] args) throws Throwable {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		JavaUtility jlib= new JavaUtility();
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		String opponame=elib.getDataFromExcel("oppo",1 , 2)+jlib.randomNumber();
		String orgname=elib.getDataFromExcel("oppo",4 , 2)+jlib.randomNumber();

		
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+"  "+"header verified==PASS==");
		}
		else{
			System.out.println(orgname+"  "+"header not verified==FAIL==");
		}

		Thread.sleep(3000);
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(opponame);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		 
		wlib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();

		wlib.switchToTabOnTitle(driver, "module=Potentials");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
       String actopponame= driver.findElement(By.id("dtlview_Opportunity Name")).getText();
		if(actopponame.equals(opponame)) {
			System.out.println(opponame+"  is verified==PASS==");
		}else {
			System.out.println(opponame+"  is not verified==PASS==");

		}
       driver.quit();
	}

}
