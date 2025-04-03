package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement  createNewOrgBbtn;
	
	
	public WebElement getCreateNewOrgBbtn() {
		return createNewOrgBbtn;
	}
	
	@FindBy(name="search_text")
	private WebElement searchedt;
	
	@FindBy(name="search_field")
	private WebElement searchdd;
	
	public WebElement getSearchedt() {
		return searchedt;
	}

	public WebElement getSearchdd() {
		return searchdd;
	}

	public WebElement getSearchbtn() {
		return searchbtn;
	}

	@FindBy(name="submit")
	private WebElement searchbtn;
	
}
