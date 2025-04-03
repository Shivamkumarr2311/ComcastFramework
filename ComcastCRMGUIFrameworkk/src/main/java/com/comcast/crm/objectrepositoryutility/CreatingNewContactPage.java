package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		@FindBy(name="lastname")
		private WebElement lastnameedt;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getLastnameedt() {
		return lastnameedt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createnewcontactbtn;

	public WebElement getCreatenewcontactbtn() {
		return createnewcontactbtn;
	}
	@FindBy(name="support_start_date")
	private WebElement startdateedt;
	
	@FindBy(name="support_end_date")
	private WebElement enddateedt;

	public WebElement getStartdateedt() {
		return startdateedt;
	}

	public WebElement getEnddateedt() {
		return enddateedt;
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText]")
	private WebElement headerinfo;

	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	@FindBy (xpath="//span[@id='dtlview_Last Name']")
	private WebElement actLastname;

	public WebElement getActLastname() {
		return actLastname;
	}
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement actEndDate;

	public WebElement getActStartDate() {
		return actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement actorgname;

	public WebElement getActorgname() {
		return actorgname;
	}

	@FindBy(className="dvHeaderText")
	private WebElement actHeader;

	public WebElement getActHeader() {
		return actHeader;
	}

	
}
