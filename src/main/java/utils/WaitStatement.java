package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitStatement {

    WebDriver driver;
    public WaitStatement(WebDriver driver){
        this.driver=driver;
    }

    public WebElement findElementVisibility(final By by,int time) {

        WebDriverWait wait = new WebDriverWait(driver, time);

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until((ExpectedCondition<Boolean>) _webDriver -> (_webDriver.findElement(by) != null));
        } catch (Exception e) {
            System.out.println();
            return null;
        }
        return driver.findElement(by);

    }

}
