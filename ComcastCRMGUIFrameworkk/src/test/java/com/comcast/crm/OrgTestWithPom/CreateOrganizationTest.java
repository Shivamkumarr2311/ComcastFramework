package com.comcast.crm.OrgTestWithPom;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;

import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = {"smokeTest"})
	public void CreateOrg() throws Throwable {

		// geting data from properties file
		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.randomNumber();

		// navigation to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBbtn().click();

		// enter all details and save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname);

		// verify header details
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actorgname = oip.getHeadermsg().getText();
		boolean status=actorgname.contains(orgname);
		Assert.assertEquals(status, true);

	}

	@Test(groups = {"regressionTest"})
	public void CreateOrgWithIndustriesTest() throws Throwable {
		String orgname = elib.getDataFromExcel("org", 7, 2) + jlib.randomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBbtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname, industry);

		// verifying the data
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actheader = oip.getHeadermsg().getText();
		SoftAssert sobj=new SoftAssert();
		sobj.assertEquals(actheader, orgname);
	}

	@Test(groups = {"regressionTest"})
	public void CreateOrgWithPhoneNumberTest() throws Throwable {
		String orgname = elib.getDataFromExcel("org", 4, 2) + jlib.randomNumber();
		String phonenumber = elib.getDataFromExcel("org", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBbtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);

		cnop.createOrg1(orgname, phonenumber);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphonenumber = oip.getPhoneheader().getText();
		SoftAssert sobj=new SoftAssert();
		sobj.assertEquals(actphonenumber, phonenumber);

	}

}
