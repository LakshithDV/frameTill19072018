package utils;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Drivers {

    public WebDriver driver;
    public WebDriver getWebDriver(String browser,String ip,String port) throws MalformedURLException {

        if (browser.equals("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(new URL("http://"+ip+":"+port+"/wd/hub"), capability);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }else if (browser.equals("chrome")) {
            //DesiredCapabilities capability = DesiredCapabilities.chrome();
            //driver = new RemoteWebDriver(new URL("http://"+ip+":"+port+"/wd/hub"), capability);
            //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver = new ChromeDriver();
        }

        return driver;
    }


}
