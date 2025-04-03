package com.comcast.crm.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactTestwithSupport_Date_5thtc {

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
        //login
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		HomePage hp=new HomePage(driver);
		//click on link
		hp.getContactlink().click();
		
	    //click on create new contact
		
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getCreatenewcontactbtn().click();
		
		//enter details
		cncp.getLastnameedt().sendKeys(lastname);
		
	    String startdate= jlib.getSystemDateYYYYDDMM();
	    String endDate= jlib.getRequiredDateYYYYDDMM(30);
	
		cncp.getStartdateedt().clear();
		cncp.getStartdateedt().sendKeys(startdate);
		
		cncp.getEnddateedt().clear();
		cncp.getEnddateedt().sendKeys(endDate);

		cncp.getSavebtn().click();
	    System.out.println("saved date");
		driver.quit();
	}

}
