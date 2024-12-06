package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
@FindBy(id="input-firstname")
WebElement txtFirstname;

@FindBy(id="input-lastname")
WebElement txtLastname;

@FindBy(id="input-email")
WebElement txtemail;

@FindBy(id="input-telephone")
WebElement telephone;

@FindBy(id="input-password")
WebElement password;

@FindBy(id="input-confirm")
WebElement ConPwd;

@FindBy(xpath="//input[@name='agree']")
WebElement checkpolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btn;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname)
{
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname)
{
	txtLastname.sendKeys(lname);  
}

public void setEmail(String email)
{
	txtemail.sendKeys(email);
}

public void setTel(String Tel)
{
	telephone.sendKeys(Tel);
}

public void setPassword(String pwd)
{
	password.sendKeys(pwd);
}

public void setConpwd(String conpwd)
{
	ConPwd.sendKeys(conpwd);
}

public void setCheckPolicy()
{
	checkpolicy.click();
}

public void setContinue()
{
	btn.click();
	
}

public String getConfirmation()
{
	try
	{
		return(msgConfirmation.getText());
	}catch(Exception e)
	{
		return(e.getMessage());
	}
}

}
