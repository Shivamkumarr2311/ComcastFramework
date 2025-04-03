package com.comcast.crm.ContacttestwithPom;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;

import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContacttest extends BaseClass {

	@Test(groups = {"smokeTest"})

	public void Createcontact() throws Throwable {

		String lastname = elib.getDataFromExcel("contact", 4, 2) + jlib.randomNumber();

		HomePage hp = new HomePage(driver);
		// click on link
		hp.getContactlink().click();

		// click on create new contact

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getCreatenewcontactbtn().click();

		// enter details
		cncp.getLastnameedt().sendKeys(lastname);

		cncp.getSavebtn().click();
		System.out.println("created contact");
		String headerinfo = cncp.getActHeader().getText();
		boolean status=headerinfo.contains(lastname);
		Assert.assertEquals(status, true);

		String actlastName = cncp.getActLastname().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actlastName, lastname);
				
	

	}

	@Test(groups = {"regressionTest"})

	public void createContactWithSupportDate() throws Throwable {
		String lastname = elib.getDataFromExcel("contact", 4, 2) + jlib.randomNumber();

		HomePage hp = new HomePage(driver);
		// click on link
		hp.getContactlink().click();

		// click on create new contact

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getCreatenewcontactbtn().click();

		// enter details
		cncp.getLastnameedt().sendKeys(lastname);

		String startdate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);

		cncp.getStartdateedt().clear();
		cncp.getStartdateedt().sendKeys(startdate);

		cncp.getEnddateedt().clear();
		cncp.getEnddateedt().sendKeys(endDate);

		cncp.getSavebtn().click();
		System.out.println("saved date");
		String actualStartDate = cncp.getActStartDate().getText();
		
		SoftAssert sobj=new SoftAssert();
		sobj.assertEquals(actualStartDate, startdate);
	
		String actualenddate =cncp.getActEndDate().getText();
		sobj.assertEquals(actualenddate, endDate);


	}

	@Test(groups = {"regressionTest"})
	public void createContactWithOrgTest() throws Throwable {
		// getting data from excel
		String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.randomNumber();
		String contactlastname = elib.getDataFromExcel("contact", 7, 3) + jlib.randomNumber();

		// navigation to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBbtn().click();

		// enter all details and save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname);

		// click on contact link
		Thread.sleep(1000);
		hp.getContactlink().click();

		// click on create new contact

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getCreatenewcontactbtn().click();

		// enter details
		cncp.getLastnameedt().sendKeys(contactlastname);

		cnop.getAddorgbtn().click();

		// switch to child window
		wlib.switchToTabOnURL(driver, "module=Accounts");

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		oip.getChildsearchtext().sendKeys(orgname);

		oip.getChildsearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// switch to parent window

		wlib.switchToTabOnTitle(driver, "Contacts&action");
		// click on save
		cncp.getSavebtn().click();
		//String headerinfo = cncp.getHeaderinfo().getText();
		String headerinfo = cncp.getActHeader().getText().trim();
		boolean status=headerinfo.contains(contactlastname);
		Assert.assertEquals(status, true);
		String actOrgName = cncp.getActorgname().getText().trim();
		
		SoftAssert sobj=new SoftAssert();
		sobj.assertEquals(actOrgName, orgname);
		sobj.assertAll();

	}

}
