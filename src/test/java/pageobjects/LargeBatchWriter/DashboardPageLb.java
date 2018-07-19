package pageobjects.LargeBatchWriter;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.List;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by Lakshith DV on 19-04-2018.
 * This will login to writer dashboard and work on project and it will submit it.
 *
 */
    public class DashboardPageLb extends WebBasePage {
    WebDriver driver;
    public DashboardPageLb(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public void selectProjInDashboard(String projectId){
    clickByJavascript(By.xpath("//*[@project_id='"+projectId+"']"));

    }
    public void selectProjectAndApproveAllRejected(String projectId) {
        clickByJavascript(By.xpath("//*[@project_id='" + projectId + "']"));


        staticWait(2000);
        //clickElement(By.xpath("(//*[@class='article_label_rejected'])[1]"), "Pending article" + i, 3);
            String wordCount = getText(By.xpath("//div[@class='on_top_panel_left']/."), "(", 3);
            String count[] = wordCount.split("#1 lean six sigma green belt certification Dublin (");
            String keywords = getText(By.xpath("//span[@class='keyword_span struct_key_vals']"), "Key words", 3);
            String keyword[] = keywords.split(",");
            String keywordDensity = getText(By.xpath("//div[contains(text(),'keyword_density : 2')]/."), "word count", 3);
            String keywordDensityCount[] = keywordDensity.split("keyword_density :");
            switchToIframe("fake_textarea_ifr");
            enterElementVisible(By.id("tinymce"), "", "", 3);
            getTest().log(LogStatus.PASS,"Word count is : "+count[1]);
            getTest().log(LogStatus.PASS,"Keywords count : "+keywordDensityCount[1]);
            for (int j = 1; j <= Integer.parseInt(count[1].trim()); j++) {
                type(By.id("tinymce"), "testing2 ", "", 3);
            }
            for (int k = 1; k <= Integer.parseInt(keywordDensityCount[1].trim()); k++) {
                System.out.println("Keyword density level: " + k);
                for (int l = 0; l < keyword.length; l++) {
                    type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                }
            }String content = getText(By.id("tinymce"),"Article content",3);
            getTest().log(LogStatus.PASS,"Article content : "+content);
            driver.switchTo().defaultContent();

            //scrollToWebelement(By.id("submit-content"));

            // here we are clicking the submit button without text.

            clickElement(By.id("submit_article_413318"), "submit", 3);
            String WithoutElmarticleStatusResult = getText(By.xpath("//div[@class='bootstrap-dialog-message']"), "Please satisfy the word count!", 3);
            Assert.assertEquals(WithoutElmarticleStatusResult, "Please satisfy the word count!");
            clickElement(By.xpath("//button[@id='1eaf8959-c9f6-4487-b695-5c4ebf78eb31']"), "OK", 3);

            // With element we are clicking the values.

            clickElement(By.id("save_article_413318"), "Save", 3);
            String SaveArticleStatusResult = getText(By.id("article_saved_message"), "Successfully Saved", 3);
            Assert.assertEquals(SaveArticleStatusResult, "Successfully Saved");

            clickElement(By.id("submit_article_413318"), "submit", 3);
            String articleStatusResult = getText(By.xpath("//div[@class='bootstrap-dialog-message']"), "Artcile Submitted Successfully!", 3);
            Assert.assertEquals(articleStatusResult, "Artcile Submitted Successfully!");
            clickElement(By.id("1f8e5af6-ee1c-4137-aa62-1ae20ff6dc59"), "OK", 3);

        }
    }




