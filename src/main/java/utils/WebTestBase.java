package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static reporting.ComplexReportFactory.*;

public class WebTestBase {

    public WebDriver driver;
    public String excelpath;
    ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void CreateTestLogPath() {
        excelpath=System.getProperty("user.dir")+"//src//main//resources//TestData-Dev.xlsx";
    }

    @AfterSuite(alwaysRun = true)
    public void close() {
        closeReport();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws MalformedURLException {
        //test=getTest(method.getDeclaringClass().getSimpleName()+"-"+method.getName(),method.getName());
        test=getTest(method.getName(),method.getName());

        String url=System.getProperty("environment");
        String browser=System.getProperty("browser");
        String ip=System.getProperty("ip");
        String port=System.getProperty("port");
        if(browser!=null &&ip!=null &&port!=null){
            System.out.println("Inside properties value");
            System.out.println("Environment -------"+url);
            System.out.println("browser -------"+browser);
            System.out.println("ip -----------"+ip);
            System.out.println("port --------"+port);
            driver=new Drivers().getWebDriver(browser,ip,port);
        }else{
            driver=new Drivers().getWebDriver("chrome","localhost","4444");
        }

        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod(alwaysRun = true)
    public void reportWrapUp(ITestResult result,Method method) {

        if(!result.isSuccess()){

            String imagpath=System.getProperty("user.dir")+"\\Results\\"+method.getName();

            // generate screenshot as a file object
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                // copy file object to designated location
                org.apache.commons.io.FileUtils.copyFile(scrFile, new File(imagpath+".png"));
                System.out.println(imagpath+".png");
            }catch(Exception e){

            }
            getTest().log(LogStatus.FAIL, getTest().addScreenCapture(imagpath+".png"));

        }
        closeTest(test);
        driver.quit();
    }

//    @Parameters({"browser","ip","port"})
    @BeforeClass
    public void invokeBrowser() throws MalformedURLException {

//       String url=System.getProperty("environment");
//       String browser=System.getProperty("browser");
//       String ip=System.getProperty("ip");
//       String port=System.getProperty("port");
//       if(browser!=null &&ip!=null &&port!=null){
//           System.out.println("Inside properties value");
//           System.out.println("Environment -------"+url);
//           System.out.println("browser -------"+browser);
//           System.out.println("ip -----------"+ip);
//           System.out.println("port --------"+port);
//           driver=new Drivers().getWebDriver(browser,ip,port);
//       }else{
//           driver=new Drivers().getWebDriver("chrome","localhost","4444");
//       }
//
//       driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
//       driver.manage().window().maximize();
//       System.out.println("url value is ------------------- "+url);
//       if(url!=null){
//           driver.get(url);
//       }else{
//           driver.get("http://www.textmercato.com/profile");
//       }
//        System.out.println("After url invocation");
    }




}
