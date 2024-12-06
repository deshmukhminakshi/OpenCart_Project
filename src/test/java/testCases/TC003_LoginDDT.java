package testCases;
/*Data is vaild-login success-test pass-logout
 data is valid--login failed-test fail
 
 data is invalid-login success-test fail-logout
 data is invalid--login failed-test pass
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
//import testBase.Base_Class;
import utilities.DataProviders;

@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven") //getting data provider from same class.
public class TC003_LoginDDT extends BaseClass
{
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("*** starting Login_DDT test ***");
		
		try
		{
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setpassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.logout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		    if(exp.equalsIgnoreCase("Invalid"))
		    {
		    	if(targetPage==true)
		    	{
		    		macc.logout();
		    		Assert.assertTrue(false);
		    	}
		    	else
		    	{
		    		Assert.assertTrue(true);
		    	}
		    }
		    }catch(Exception e)
		    {
		    	Assert.fail();
		    }
		    
		    logger.info("*** DDT test is exceuted ***");
	}
	

}
