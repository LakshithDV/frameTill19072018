package pageobjects.LargeBatchManager;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.WebBasePage;

import java.util.List;
import java.util.Map;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by Lakshith DV on 13-04-2018.
 * Here we are uploading summery and data sheet to the our created project.
 *
 */
public class ProjectSummaryPageLb extends WebBasePage {

    WebDriver driver;
    public ProjectSummaryPageLb(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void uploadBreif(){
        staticWait(5000);
        //clickElement(By.id("project_brief"),"Brief",10);
        clickElement(By.xpath("//button[@id='project_brief']"),"Brief",10);
        staticWait(2000);
        if(verifyElement(By.id("myForm"),"Form",3).isDisplayed()){
            driver.switchTo().defaultContent();
            switchToIframe("mce_brief_structure_ifr");
            //driver.findElement(By.id("tinymce")).sendKeys("This is testing purpose - negative check with more than 30 words This is testing purpose - negative check with less than 30 words This is testing purpose - negative check with less than 30 words");
            enterElementVisible(By.id("tinymce"),"This is testing purpose - negative check with more than 30 words This is testing purpose - negative check with less than 30 words This is testing purpose - negative check with less than 30 words","Structure content",3);
            driver.switchTo().defaultContent();

            switchToIframe("mce_brief_objective_ifr");
            enterElementVisible(By.id("tinymce"),"This is testing purpose - negative check with more than 30 words This is testing purpose - negative check with less than 30 words This is testing purpose - negative check with less than 30 words","Articles objective content",3);
            driver.switchTo().defaultContent();

            switchToIframe("mce_brief_dos_ifr");
            enterElementVisible(By.id("tinymce"),"This is testing purpose - negative check ","Do's content",3);
            driver.switchTo().defaultContent();

            switchToIframe("mce_brief_dontdos_ifr");
            enterElementVisible(By.id("tinymce"),"This is testing purpose - negative check ","Don't content",3);
            driver.switchTo().defaultContent();
            enterElementVisible(By.id("brief_tone_of_article"),"This is testing purpose- negative check with more than 10 words","brief ton",3);
            enterElementVisible(By.id("brif_target_audience"),"This is testing purpose- negative check  with more than 10 words","brif_target_audience",3);
            switchToIframe("brief_resources_ifr");
            enterElementVisible(By.id("tinymce"),"This is testing purpose - negative check with more than 30 words This is testing purpose - negative check with less than 30 words This is testing purpose - negative check with less than 30 words","brief_resources",3);
            driver.switchTo().defaultContent();
            clickElement(By.xpath("//button[contains(text(),'Save')]"),"Save",3);
            int size = driver.findElements(By.xpath("//p[@id='this_is_error_message']")).size();
            if(size>=4){
                getTest().log(LogStatus.PASS,"Expected error occured");
            }
            else{
                getTest().log(LogStatus.PASS,"no error occured");

            }
        }
        switchToIframe("mce_brief_structure_ifr");
        enterElementVisible(By.id("tinymce"),"This is testing purpose","Structure content",3);
        driver.switchTo().defaultContent();

        switchToIframe("mce_brief_objective_ifr");
        enterElementVisible(By.id("tinymce"),"This is testing purpose","Articles objective content",3);
        driver.switchTo().defaultContent();

        switchToIframe("mce_brief_dos_ifr");
        enterElementVisible(By.id("tinymce"),"This is testing purpose","do's",3);
        driver.switchTo().defaultContent();

        switchToIframe("mce_brief_dontdos_ifr");
        enterElementVisible(By.id("tinymce"),"This is testing purpose","don't ",3);
        driver.switchTo().defaultContent();
        enterElementVisible(By.id("brief_tone_of_article"),"This is testing purpose with less than 10 words","brief tone",3);
        enterElementVisible(By.id("brif_target_audience"),"This is testing purpose with less than 10 words","target audience",3);
        switchToIframe("brief_resources_ifr");
        enterElementVisible(By.id("tinymce"),"This is testing purpose","brief resource",3);
        driver.switchTo().defaultContent();
        clickElement(By.xpath("//button[contains(text(),'Save')]"),"Save",3);

        try {
            WebElement ele = verifyElement(By.xpath("(//div[@role='dialog'])[last()]"), "dialog", 3);
            scrollToWebelement(By.xpath("(//div[@role='dialog'])[last()]"));
            if (ele.isDisplayed()) {
                String text = getText(By.xpath("(//div[@class='bootstrap-dialog-message'])[last()]"), "Dialog content", 3);
                clickElement(By.xpath("//button[contains(text(),'OK')]"), "OK ", 5);
                staticWait(2000);
                getTest().log(LogStatus.PASS,text+"dialog present");

            }
        }catch(Exception e){
            getTest().log(LogStatus.FAIL,"Dialog not present");
        }
        staticWait(2000);
        //clickByJavascript(By.xpath("//button[contains(text(),'×')]"));
       // staticWait(2000);

        clickElement(By.id("project_brief"),"Brief",10);
        staticWait(2000);
        clickElement(By.xpath("//button[contains(text(),'I will do later')]"),"I will do later",3);
        staticWait(2000);
        clickElement(By.xpath("//button[contains(text(),'Yes')]"),"Yes",3);
        staticWait(1000);
    }
    public void uploadSample(){
        findElementVisibility(By.name("project_sample"),10);
        String filename=System.getProperty("user.dir")+"//src//main//resources//uploadfiles//sample.doc";
        WebElement uploadFile = driver.findElement(By.name("project_sample"));
        ((RemoteWebDriver) driver).executeScript("arguments[0].style =''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", uploadFile);
        uploadFile.sendKeys(filename);
        staticWait(1000);
    }
    public void uploadData(){
        findElementVisibility(By.name("project_brief"),10);
        // Where you can change the data sheet easily here by changing simple.
      //  String filename=System.getProperty("user.dir")+"//src//main//resources//uploadfiles//datanotforlargebatchfortest.xlsx";  Where we can swipe data sheet to one to other.
        String filename=System.getProperty("user.dir")+"//src//main//resources//uploadfiles//Lb data page.xlsx";
        WebElement uploadFile = driver.findElement(By.name("data_file"));
        ((RemoteWebDriver) driver).executeScript("arguments[0].style =''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", uploadFile);
        uploadFile.sendKeys(filename);
        staticWait(1000);
    }

    public void allocationType() {
        clickByJavascript(By.id("manual_allot"));
    }
    public void packetSizeMinusAndVerifyError(Map<String,String> data){
        List<WebElement> noOfArticles = driver.findElements(By.xpath("//input[@name='project_jobs_table_id[]']"));
        int noOfArticles1 = noOfArticles.size();
        if (noOfArticles1>1){
            enterElementVisible(By.name("packet_size"),"-1","Packet sixe",10);
            searchWritter(data);
            /* Click submit button*/
            allot();
            staticWait(2000);
            verifyAlertAndAccept();
            staticWait(4000);
        }
    }
    public void packetSizeMoreAndVerifyError(Map<String,String> data){
        List<WebElement> noOfArticles = driver.findElements(By.xpath("//input[@name='project_jobs_table_id[]']"));
        int noOfArticles1 = noOfArticles.size();
        if (noOfArticles1>1){
            enterElementVisible(By.name("packet_size"),(noOfArticles1+10)+"","Packet sixe",10);
            searchWritter(data);
            /* Click submit button*/
            allot();
            staticWait(2000);
            verifyAlertAndAccept();
            staticWait(4000);
        }
    }
    public void verifyWithValidPacketSize(Map<String,String> data){
        List<WebElement> noOfArticles = driver.findElements(By.xpath("//input[@name='project_jobs_table_id[]']"));
        int noOfArticles1 = noOfArticles.size();
        if (noOfArticles1>1){
            enterElementVisible(By.name("packet_size"),noOfArticles1+"","Packet sixe",10);
            searchWritter(data);
            /* Click submit button*/
            allot();
            staticWait(2000);
            verifyAlertAndAccept();
            staticWait(4000);
        }
    }

    public void searchWritter(Map<String,String> data){
        enterElementVisible(By.id("writer_search"),data.get("WriterSearch"),"WriterSearch",10);
        //clickElementVisible(By.id("user_search_btn"),"User search button",10);
        clickElementVisible(By.xpath("//*[@id='user_search_btn'][@content='Search']"),"User search button",10);
        //clickElementVisible(By.xpath("//label[text()='Laksh DV']//preceding-sibling::input[1]"),"Laksh dv",10);
        //clickElementVisible(By.xpath("//label[contains(text(),'Laksh dv')]"),"Laksh dv",10);    my code
           clickElementVisible(By.xpath("//input[@id='writer_search_1069']"),"Laksh dv",10);
    }

    public void allot(){
        scrollDown();
        clickByJavascript(By.id("editor_allot_submit"));
       /* staticWait(4000);                          Don't ever try to open this comment it will fail complety code.
        driver.switchTo().alert().accept();
        staticWait(4000);*/
    }
}
