package com.comcast.crm.orgtest;

import org.openqa.selenium.WebDriver;
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
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithPhoneNumberTest_3rdWithPOM {

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
		String phonenumber=elib.getDataFromExcel("org", 7, 3);
		
		
		
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
		
		LoginPage lp= new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
       hp.getOrgLink().click();
       
       
        OrganizationsPage op= new OrganizationsPage(driver);
        op.getCreateNewOrgBbtn().click();
        
        CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
        
       cnop.createOrg1(orgname, phonenumber);
        
        OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actphonenumber=oip.getPhoneheader().getText();
		if(actphonenumber.equals(phonenumber)) {
			System.out.println(phonenumber+"  "+"is verified==pass==");
		}else {
			System.out.println(phonenumber+"  "+"is not verified==fail==");

		}
		driver.quit();
	
	}

}
