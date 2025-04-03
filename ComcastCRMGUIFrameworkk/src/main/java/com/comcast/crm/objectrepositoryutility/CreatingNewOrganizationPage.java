package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;                          //Rule 3 :Object initialization
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
    @FindBy(name="accountname")
	private WebElement orgNamEdt;
    
    @FindBy(xpath="//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
    
    @FindBy(name="industry")
    private WebElement industryDB;
    
    public WebElement getPhonetext() {
		return phonetext;
	}
	@FindBy(id="phone")
    private WebElement phonetext;
    
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement addorgbtn;
	
	
	public WebElement getAddorgbtn() {
		return addorgbtn;
	}

	public WebElement getOrgNamEdt() {
		return orgNamEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgName) {
		orgNamEdt.sendKeys(orgName);
		saveBtn.click();
	}
	public void createOrg(String orgName, String industry) throws Throwable {
		orgNamEdt.sendKeys(orgName);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		Thread.sleep(1000);
		saveBtn.click();
	}
	public void createOrg1(String orgName,String phoneNumber) {
		orgNamEdt.sendKeys(orgName);
		phonetext.sendKeys(phoneNumber);
		saveBtn.click();
	}
}
