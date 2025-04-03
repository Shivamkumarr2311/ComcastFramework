package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithIndustriesTest_2ndWithPOM_ {

	public static void main(String[] args) throws Throwable {
		
		
		//geting data from properties file

		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		//geting data from excel file
				ExcelUtility elib=new ExcelUtility();
		
		String orgname=elib.getDataFromExcel("org",7,2)+jlib.randomNumber();
		String industry=elib.getDataFromExcel("org", 4, 3);
		
		
		
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
		LoginPage lp= new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
       hp.getOrgLink().click();
       
       
        OrganizationsPage op= new OrganizationsPage(driver);
        op.getCreateNewOrgBbtn().click();
        
        
        CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
        cnop.createOrg(orgname, industry);

		
		
		//verifying the data
        OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actheader=oip.getHeadermsg().getText();
		if(actheader.contains(orgname)) {
			System.out.println(orgname+"  "+"is verified==pass==");
		}else {
			System.out.println(orgname+"  "+"is not verified==fail==");

		}
		driver.quit();
	}

}
