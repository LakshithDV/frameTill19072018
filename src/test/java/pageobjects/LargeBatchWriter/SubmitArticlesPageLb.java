package pageobjects.LargeBatchWriter;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebBasePage;

import java.util.List;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by Lakshith_10.48AM on 25-04-2018.
 */
public class SubmitArticlesPageLb extends WebBasePage {
    WebDriver driver;
    public SubmitArticlesPageLb(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public void downloadFiles(String projectId){
        staticWait(2000);
       // clickByJavascript(By.xpath("//*[@project_id='" + projectId + "']"));
        staticWait(2000);
        clickElement(By.xpath("//a[@data-toggle='modal']"),"Brief",3);
        if(verifyElement(By.xpath("//label[@id='mce_brief_structure_label']"),"Brief content",3).isDisplayed()){
            clickElement(By.xpath("//button[@class='btn btn-default']"),"Close button",3);
        }
        clickByJavascript(By.xpath("//a[contains(text(),'Sample |')]"));
        clickByJavascript(By.xpath("//a[contains(text(),'Data')]"));
    }
    public void verifyCompletedProjecvts(String projId){
        verifyElement(By.xpath("//div[@project_id='"+projId+"']/../../div/h4[contains(text(),'Projects completed')]"),"Completed project",3);
    }
    public void submitArticles() {


        List<WebElement> noOfArticle = driver.findElements(By.xpath("//a[@id='completed_article_number']"));

        for (int i = 1; i <= noOfArticle.size(); i++) {
            if(i>1){
                clickElement(By.xpath("(//*[@class='article_label_pending'])[1]"),"Pending article"+i,3);
            }
            String wordCount = getText(By.xpath("(//div[@class='art_detail'])[1]"), "word count", 3);
            String count[] = wordCount.split("Word Count");
            String keywords = getText(By.xpath("//span[@class='keyword_span struct_key_vals']"), "Key words", 3);
            String keyword[] = keywords.split(",");
            String keywordDensity = getText(By.xpath("//div[contains(text(),'Keyword density')]/.."), "word count", 3);
            String keywordDensityCount[] = keywordDensity.split("Keyword density");
            switchToIframe("jander_ifr");
            //enterElementVisible(By.xpath("//div[@id='mceu_36']//iframe[@id='jander_ifr']"), "", "", 3);
            type(By.id("tinymce"), "", "", 3);
            getTest().log(LogStatus.PASS,"Word count is : "+count[1]);
            getTest().log(LogStatus.PASS,"Keywords count : "+keywordDensityCount[1]);
            int count1 = Integer.parseInt(count[1].trim());
            boolean once = true;
            if(i==1) {
                for (int j = 1; j <= 1; j++) {
                    type(By.id("tinymce"), "testing3 ", "", 3);
                }

                String content = getText(By.id("tinymce"),"Article content",3);   // Lakshith Changing code here id='jander_ifr' into id='tinymce'.
                getTest().log(LogStatus.PASS,"Article content : "+content);
                driver.switchTo().defaultContent();
                scrollToWebelement(By.id("submit-content"));
                clickElement(By.id("submit-content"),"Submit",3);
                handleDialog();
                switchToIframe("jander_ifr");
                enterElementVisible(By.id("tinymce"), "", "", 3);
            }


            for (int j = 1; j <= Integer.parseInt(count[1].trim()); j++) {
                type(By.id("tinymce"), "testing3 ", "", 3);
            }
            int keywordDenCount = Integer.parseInt(keywordDensityCount[1].trim());
            if(keywordDenCount>1) {
                for (int k = 1; k <= (keywordDenCount - 1); k++) {
                    for (int l = 0; l < keyword.length; l++) {
                        type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                    }
                    driver.switchTo().defaultContent();
                    try {
                        scrollToWebelement(By.id("submit-content"));
                        clickElement(By.id("submit-content"), "Submit", 3);
                        handleDialog();
                        switchToIframe("jander_ifr");
                        for (int l = 0; l < keyword.length; l++) {
                            type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                        }
                        driver.switchTo().defaultContent();
                        scrollToWebelement(By.id("submit-content"));
                        clickElement(By.id("submit-content"),"Submit",3);
                        // String articleStatusResult = getText(By.id("article_status_result"), "article_status_result", 3);
//                        Assert.assertEquals(articleStatusResult, "Successfully submitted");
                    } catch (Exception e) {
                        getTest().log(LogStatus.PASS, "Exception occured: " + e);
                    }
                }
            }
            else{
                driver.switchTo().defaultContent();
                scrollToWebelement(By.id("submit-content"));
                clickElement(By.id("submit-content"),"Submit",3);
                handleDialog();
                switchToIframe("jander_ifr");
                for (int l = 0; l < keyword.length; l++) {
                    type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                }
                driver.switchTo().defaultContent();
                scrollToWebelement(By.id("submit-content"));
                clickElement(By.id("submit-content"),"Submit",3);
            }

        }
    }
}
