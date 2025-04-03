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
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateContactWithOrgTest_6th_testcase_POM {

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
		  LoginPage lp=new LoginPage(driver)	;	
          lp.LoginToApp(USERNAME,PASSWORD);
		
		//navigation to organization module
         HomePage hp=new HomePage(driver);
         hp.getOrgLink().click();
       
       
       //click on create organization button
         OrganizationsPage cnp=new OrganizationsPage(driver) ;
     	 cnp.getCreateNewOrgBbtn().click();
      
     	 //enter all details and save
     	 CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
     	 cnop.createOrg(orgname);
     	 
		//click on contact link
     	 Thread.sleep(1000);
		hp.getContactlink().click();
		
	    //click on create new contact
		
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getCreatenewcontactbtn().click();
		
		//enter details
		cncp.getLastnameedt().sendKeys(contactlastname);
	
	    cnop.getAddorgbtn().click();
	
		//switch to child window
		wlib.switchToTabOnURL(driver, "module=Accounts");
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		oip.getChildsearchtext().sendKeys(orgname);
		
		oip.getChildsearchbtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	
		//switch to parent window
		
		wlib.switchToTabOnTitle(driver, "Contacts&action");
		//click on save
        cncp.getSavebtn();		
	    System.out.println("done");
		driver.quit(); 
		
	}
}
