package practice.test;
/**
 * 
 * test class for Contact module
 * @author Shivam Kumar
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass {
	
	
	@Test
	public void searchcontactTest() {
		/*step 1 : login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp("url", "username", "password");
	}

}
