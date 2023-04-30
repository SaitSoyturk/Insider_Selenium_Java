import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.io.FileUtils;
import static Base.Config.BROWSER_NAME;
import static Base.BrowserFactory.getChromeOptions;
import static Base.BrowserFactory.getFirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.Duration.ofSeconds;
public class BaseTest {
    static WebDriver driver;
    protected static ExtentReports report;
    protected ExtentTest extentLogger;
    public static ChromeOptions chromeOptions;
    public static FirefoxOptions firefoxOptions;

    @BeforeSuite
    public void beforeSuite() {
        report = new ExtentReports();
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public static WebDriver setup() {
        chromeOptions = getChromeOptions();
        firefoxOptions = getFirefoxOptions();

        switch (BROWSER_NAME) {
            case "chrome":
            default:
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // if test fails
        if(result.getStatus()==ITestResult.FAILURE){
            // record the name of failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of screenshot
            String screenShotPath = getScreenshot(result.getName());

            //add your screenshot to your report
            extentLogger.addScreenCaptureFromPath(screenShotPath);

            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());
        }
        driver.quit();
    }
}
