package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;                          //Rule 3 :Object initialization
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeadermsg() {
		return headerMsg;
	}
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryHeader;
	
	
	public WebElement getIndustryHeader() {
		return industryHeader;
	}
	@FindBy(id="dtlview_Phone")
	private WebElement phoneheader;
	
	public WebElement getPhoneheader() {
		return phoneheader;
	}
	@FindBy(name="search_text")
	private WebElement childsearchtext;
	public WebElement getChildsearchtext() {
		return childsearchtext;
	}
	@FindBy(name="search")
	private WebElement childsearchbtn;
	public WebElement getChildsearchbtn() {
		return childsearchbtn;
	}
  
}
