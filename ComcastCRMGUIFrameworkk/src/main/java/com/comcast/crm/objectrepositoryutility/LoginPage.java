package com.comcast.crm.objectrepositoryutility;
/**
 * @author Shivam Kumar
 * 
 * contains login page element&business lib like login()
 * 
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {//Rule-1 create a separate java class
	
	WebDriver driver;                          //Rule 3 :Object initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
	
	//Rule-2 object creation
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	
	//Rule 4 : object encapsulation
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule 4 : object encapsulation
	public void LoginToApp(String username,String password) {
		waitForPageToLoad(driver);
		
		driver.manage().window().maximize();
		usernameEdt.sendKeys("admin");
		passwordEdt.sendKeys("admin");
		loginBtn.click();
	}
	/**
	 * login to application based username, url password
	 * @param url
	 * @param username
	 * @param password
	 */
	public void LoginToApp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys("admin");
		passwordEdt.sendKeys("admin");
		loginBtn.click();
	}
	

}
