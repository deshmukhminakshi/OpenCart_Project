package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
//import testBase.Base_Class;

public class TC001_AccountRegistrationTest extends BaseClass
{
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("*****  Starting TC001_AccountRegistrationTest  *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickRegister();
		logger.info("Clicked on Registered link");
		
		AccountRegistrationPage ap=new AccountRegistrationPage(driver);
		ap.setFirstName(randomeString().toUpperCase());
		ap.setLastName(randomeString().toUpperCase());
		ap.setEmail(randomeString()+"@gmail.com");
		ap.setTel(randomeNumber());
		
		String password=randomeAlphaNumberic();
		
		ap.setPassword(password);
		ap.setConpwd(password);
		ap.setCheckPolicy();
		ap.setContinue();
		
		String confmsg=ap.getConfirmation();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
	}
		catch(Exception e)
		{
			logger.error("Test Failed..");
			//logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("**** Finsihed Test ****");
	}
}
