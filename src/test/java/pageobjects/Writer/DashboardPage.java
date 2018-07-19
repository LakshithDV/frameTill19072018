package pageobjects.Writer;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.List;
import java.util.Map;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by theem_000 on 19-04-2018.
 */
public class DashboardPage extends WebBasePage {
    WebDriver driver;
    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
public void selectProjInDashboard(String projectId){
    clickByJavascript(By.xpath("//*[@project_id='"+projectId+"']"));

}

    public void selectProjectAndApproveAllRejected(String projectId) {
        clickByJavascript(By.xpath("//*[@project_id='" + projectId + "']"));
        staticWait(2000);
        List<WebElement> noOfArticle = driver.findElements(By.xpath("//*[@class='article_label_rejected']"));
        for (int i = 1; i <= noOfArticle.size(); i++) {
            if (i > 1) {
                clickElement(By.xpath("(//*[@class='article_label_rejected'])[1]"), "Pending article" + i, 3);
            }
            String wordCount = getText(By.xpath("//span[contains(text(),'Word Count')]/../.."), "word count", 3);
            String count[] = wordCount.split("Word Count");

            String keywords = getText(By.xpath("//span[@class='keyword_span struct_key_vals']"), "Key words", 3);
            String keyword[] = keywords.split(",");
            String keywordDensity = getText(By.xpath("//div[contains(text(),'Keyword density')]/.."), "word count", 3);
            String keywordDensityCount[] = keywordDensity.split("Keyword density");
            switchToIframe("jander_ifr");
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
            scrollToWebelement(By.id("submit-content"));
            clickElement(By.id("submit-content"), "Submit", 3);
            String articleStatusResult = getText(By.id("article_status_result"), "article_status_result", 3);
            Assert.assertEquals(articleStatusResult, "Successfully submitted");
        }
    }



}
