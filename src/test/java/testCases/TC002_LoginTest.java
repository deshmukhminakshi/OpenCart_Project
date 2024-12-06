package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
//import testBase.Base_Class;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("Starting TC002_LoginTest");
		try
		{
		//Home Page
	    logger.info("HomePage is here");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		logger.info("LoginPage is here");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount
		logger.info("AccountPage is here");
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true,"Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished LoginTest");
	}

}
