/*package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base_Class {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        // loading config.properties file
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        // Logger initialization
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting test setup");

        logger.info("Executing on OS: " + os + " and Browser: " + br);

        // Check execution environment
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            logger.info("Execution environment: Remote");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set OS
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                logger.error("No matching OS found");
                return;
            }

            // Set Browser
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    logger.error("No matching browser found");
                    return;
            }

            // Initialize Remote WebDriver
            try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/ui"), capabilities);
                logger.info("Remote WebDriver initialized successfully.");
            } catch (Exception e) {
                logger.error("Failed to initialize RemoteWebDriver", e);
                return;
            }
        } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            logger.info("Execution environment: Local");

            // Initialize local WebDriver based on browser choice
            switch (br.toLowerCase()) {
                case "chrome"
                     // Update with actual path
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "C:\\path\\to\\msedgedriver.exe");  // Update with actual path
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "C:\\path\\to\\geckodriver.exe");  // Update with actual path
                    driver = new FirefoxDriver();
                    break;
                default:
                    logger.error("Invalid Browser Name.");
                    return;
            }

            logger.info("Local WebDriver initialized successfully.");
        } else {
            logger.error("Invalid execution environment in config.properties");
            return;
        }

        // Set browser options
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL"));  // Read app URL from config.properties
        driver.manage().window().maximize();

        logger.info("Navigated to URL: " + p.getProperty("appURL"));
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver quit successfully.");
        } else {
            logger.error("Driver was not initialized.");
        }
    }

    // Additional helper methods (random string generation, screenshots, etc.)
    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomeAlphaNumberic() {
        return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException {
        // Check if driver is initialized
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Cannot capture screenshot");
        }

        // Log the action
        logger.info("Capturing screenshot for test: " + tname);

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        logger.info("Screenshot captured at: " + targetFilePath);
        return targetFilePath;
    }
}
*/