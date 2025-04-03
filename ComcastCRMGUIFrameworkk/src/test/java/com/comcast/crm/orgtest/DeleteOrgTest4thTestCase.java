package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest4thTestCase {
	public static void main(String[] args) throws Throwable {

	FileUtility flib=new FileUtility();
	JavaUtility jlib=new JavaUtility();
	WebDriverUtility wlib=new WebDriverUtility();
	
	String BROWSER=flib.getDataFromPropertiesFile("browser");
	String URL=flib.getDataFromPropertiesFile("url");
	String USERNAME=flib.getDataFromPropertiesFile("username");
	String PASSWORD=flib.getDataFromPropertiesFile("password");
	
     //geting data from excel file

	ExcelUtility elib=new ExcelUtility();
    String orgname=elib.getDataFromExcel("org", 1, 2)+jlib.randomNumber();
	
	
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
	  
	LoginPage lp=new LoginPage(driver);
	lp.LoginToApp(USERNAME, PASSWORD);
	
	 HomePage hp=new HomePage(driver);
     hp.getOrgLink().click();
     
     
     //click on create organization button
    OrganizationsPage cnp=new OrganizationsPage(driver) ;
   	 cnp.getCreateNewOrgBbtn().click();
   	 
   	 //create new organization
   	CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
	 cnop.createOrg(orgname);
	Thread.sleep(1000);
	 //return back to org
     hp.getOrgLink().click();
	
	//search orgname
     cnp.getSearchedt().sendKeys(orgname);
    
     wlib.select(cnp.getSearchdd(), "Organization Name");//webElement is passed first and then drop  down selection
     cnp.getSearchbtn().click();
     
     driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
     
     wlib.switchToAletAndAccept(driver);
     System.out.println("deleted");
     
    hp.logout();
    driver.quit();
	}
}
