package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver) 
	{
		super(driver);	
	}
@FindBy(id="input-email")
WebElement lemail;

@FindBy(id="input-password")
WebElement lpwd;

@FindBy(xpath="//input[@value='Login']")
WebElement l_login;

public void setEmail(String email)
{
	lemail.sendKeys(email);
}

public void setpassword(String pwd)
{
	lpwd.sendKeys(pwd);
}

public void clickLogin()
{
	l_login.click();
}

}
