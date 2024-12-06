package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger;//var created for log4j
	public Properties p;
	
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//loading config.properties file
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		
		//log code starts
		logger=LogManager.getLogger(this.getClass());
		//logger.info("Executing on OS:" +os+ "and Browser:" +br);)
		
		//if You want to execute remote then below code is for remote execution.
		/*if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);	
			}
			else
			{
				System.out.println("No matching os");
				return;
			}*/
			
			/*//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");
			break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");
			break;
			default:System.out.println("No Matching Browser");
			return;
			}
			
			//we have to create driver for remote or launching the URL
			driver=new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),capabilities);
			logger.info("Remote WebDriver initialize successfully");
		}*/
		
		//code for local execution
		//if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		//{
			//add switch for parallel testing --this is local execution 
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver();break;
			case "edge"   : driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default: System.out.println("Invalid Browser Name..");
			return;	
		}
		//}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); //reading URL from config.properties
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
	    String generatednumeric=RandomStringUtils.randomNumeric(10);
	    return generatednumeric;
	}
	
	public String randomeAlphaNumberic()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		String generatednumeric=RandomStringUtils.randomNumeric(3);
	    return (generatedstring+"@"+generatednumeric);

}
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot=(TakesScreenshot) driver;
		File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname+ "_" + timeStamp+ ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
