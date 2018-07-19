package utils;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static reporting.ComplexReportFactory.getTest;

public class WebBasePage extends WaitStatement{

    WebDriver driver;

    public WebBasePage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void open(String url){

        driver.get(url);
        getTest().log(LogStatus.PASS,"Url opened - "+url);
    }

    public void enterElementVisible(By by,String value,String name,int time){
        WebElement element=findElementVisibility(by,time);
        scrollToWebelement(by);
        if(element!=null){
            element.clear();
            element.sendKeys(value);
            getTest().log(LogStatus.PASS,name+" entered with value - "+value);
        }else{
            getTest().log(LogStatus.FAIL,name+" entered with value - "+value);
        }
    }
    public void type(By by,String value,String name,int time){
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            element.sendKeys(value);
        }
    }
    public void enterElementVisibleWC(By by,String value,String name,int time){
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            element.sendKeys(value);
            getTest().log(LogStatus.PASS,name+" entered with value - "+value);
        }else{
            getTest().log(LogStatus.FAIL,name+" entered with value - "+value);
        }
    }

    public void clickElementVisible(By by,String name,int time){
        scrollToWebelement(by);
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            element.click();
            getTest().log(LogStatus.PASS,name+" clicked");
        }else{
            getTest().log(LogStatus.FAIL,name+" not clicked");
        }
    }

    public void clickElement(By by,String name,int time){
        scrollToWebelement(by);
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            element.click();
        }else{
            getTest().log(LogStatus.FAIL,name+" not clicked");

        }
    }


    public WebElement verifyElement(By by,String name,int time){
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            getTest().log(LogStatus.PASS,name+" element visible");
        }else{
            getTest().log(LogStatus.FAIL,name+" element not visible");
        }
        return element;
    }

    public void selectValueWithText(By by,String text,String name,int time){
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            Select se=new Select(element);
            se.selectByVisibleText(text);
            getTest().log(LogStatus.PASS,name+" selected with value - "+text);
        }else{
            getTest().log(LogStatus.FAIL,name+" not selected with value - "+text);
        }
    }
    public void selectValueWithValue(By by,String value,String name,int time){
        WebElement element=findElementVisibility(by,time);
        if(element!=null){
            Select se=new Select(element);
            se.selectByValue(value);
            getTest().log(LogStatus.PASS,name+" selected with value - "+value);
        }else{
            getTest().log(LogStatus.FAIL,name+" not selected with value - "+value);
        }
    }
    public void scrollDown(){
        staticWait(2000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        staticWait(2000);
    }

    public void staticWait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void scrollToWebelement(By by){
        WebElement Element = driver.findElement(by);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", Element);

    }


    public void clickByJavascript(By by){

        WebElement element = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

    }

    public int getRowCount(By by){

        return driver.findElements(by).size();
    }


    public String getText(By by,String name,int time){
        WebElement element=findElementVisibility(by,time);
        String text=null;
        if(element!=null){
            text=element.getText();
            getTest().log(LogStatus.PASS,text+" - text displayed");
        }else{
            getTest().log(LogStatus.FAIL,name+" not available");
        }
        return text;
    }
    public void verifyAlertAndAccept(){
        WebDriverWait wait = new WebDriverWait(driver, 10 );
        if(wait.until(ExpectedConditions.alertIsPresent())==null)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Alert alert = driver.switchTo().alert();
            String alertMsg = alert.getText();
            getTest().log(LogStatus.PASS,"Alert present with: "+alertMsg);
            alert.accept();
            System.out.println("alert was present and accepted");
        }
    }
    public void handleDialog(){
        try {
            WebElement ele = verifyElement(By.xpath("//div[@id='ui-id-1']"), "dialog", 3);
            scrollToWebelement(By.xpath("//div[@id='ui-id-1']"));
            if (ele.isDisplayed()) {
                String text = getText(By.xpath("//span[@class='prompt_dialog_content']"), "Dialog content", 3);
                clickElement(By.xpath("//button[contains(text(),'Ok')]"), "ok ", 5);
                staticWait(2000);
                getTest().log(LogStatus.PASS,text+"dialog present");

            }
        }catch(Exception e){
            getTest().log(LogStatus.FAIL,"Dialog not present");
        }
    }
    public void switchToIframe(String frameId){
        //driver.switchTo().frame();
        WebElement ele = driver.findElement(By.id(frameId));
        driver.switchTo().frame(ele);
        staticWait(1000);

    }
}
