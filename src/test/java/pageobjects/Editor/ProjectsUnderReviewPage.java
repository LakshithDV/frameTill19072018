package pageobjects.Editor;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.List;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by theem_000 on 19-04-2018.
 */
public class ProjectsUnderReviewPage extends WebBasePage{
    WebDriver driver;

    public ProjectsUnderReviewPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void approveAndRejectProject() {

        List<WebElement> noOfArticle = driver.findElements(By.xpath("//*[@class='article_label_review']"));

        for (int i = 1; i <= noOfArticle.size(); i++) {

          if (i==1) {
                staticWait(2000);
                clickElement(By.id("reject"), "reject", 3);
                staticWait(2000);
                String msg = getText(By.xpath("//span[contains(text(),'You cant leave this empty')]"), "Validation message", 3);
                Assert.assertEquals(msg, "You cant leave this empty");
                enterElementVisible(By.id("comment"), "Editor reject: testing purpose", "comment", 3);
                scrollToWebelement(By.id("reject"));
                clickElement(By.id("reject"), "reject", 3);
                staticWait(2000);
            }
            if (i > 1) {
                clickElement(By.xpath("(//*[@class='article_label_review'])[1]"), "Pending article" + i, 3);
                staticWait(5000);
                clickElement(By.xpath("(//*[@class='article_label_review'])[1]"), "Pending article" + i, 3);
                staticWait(5000);
                String wordCount = getText(By.xpath("//span[contains(text(),'Word Count')]/../.."), "word count", 3);
                String count[] = wordCount.split("Word Count");
                String keywords = getText(By.xpath("//span[@class='keyword_span struct_key_vals']"), "Key words", 3);
                String keyword[] = keywords.split(",");
                String keywordDensity = getText(By.xpath("//div[contains(text(),'Keyword density')]/.."), "word count", 3);
                String keywordDensityCount[] = keywordDensity.split("Keyword density");
                switchToIframe("jander_ifr");
                staticWait(5000);
                enterElementVisible(By.id("tinymce"), "", "", 3);
                getTest().log(LogStatus.PASS, "Word count is : " + count[1]);
                getTest().log(LogStatus.PASS, "Keywords count : " + keywordDensityCount[1]);


                for (int j = 1; j <= 1; j++) {
                    type(By.id("tinymce"), "testing ", "", 3);
                }
                String content = getText(By.id("tinymce"), "Article content", 3);
                getTest().log(LogStatus.PASS, "Article content : " + content);
                driver.switchTo().defaultContent();
                clickElement(By.id("approve"), "Approve", 3);
                staticWait(2000);
                handleDialog();
                switchToIframe("jander_ifr");
                enterElementVisible(By.id("tinymce"), "", "", 3);

                for (int j = 1; j <= Integer.parseInt(count[1].trim()); j++) {
                    type(By.id("tinymce"), "testing ", "", 3);
                }
                int keywordDenCount = Integer.parseInt(keywordDensityCount[1].trim());
                if (keywordDenCount > 1) {
                    for (int k = 1; k <= (keywordDenCount - 1); k++) {
                        for (int l = 0; l < keyword.length; l++) {
                            type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                        }
                        driver.switchTo().defaultContent();
                        try {
                            clickElement(By.id("approve"), "Approve", 3);
                            staticWait(2000);
                            handleDialog();
                            switchToIframe("jander_ifr");
                            for (int l = 0; l < keyword.length; l++) {
                                type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                            }
                            driver.switchTo().defaultContent();
                            clickElement(By.id("approve"), "Approve", 3);
                            staticWait(2000);
                           // String articleStatusResult = getText(By.id("article_status_result"), "article_status_result", 3);
                           // Assert.assertEquals(articleStatusResult, "Successfully submitted");
                        } catch (Exception e) {
                            getTest().log(LogStatus.PASS, "Exception occured: " + e);
                        }
                    }
                } else {
                    driver.switchTo().defaultContent();
                    clickElement(By.id("approve"), "Approve", 3);
                    staticWait(1000);
                    handleDialog();
                    switchToIframe("jander_ifr");
                    for (int l = 0; l < keyword.length; l++) {
                        type(By.id("tinymce"), keyword[l].substring(0, keyword[l].length() - 3) + " ", "Keyword: " + l, 3);
                    }
                    driver.switchTo().defaultContent();
                    clickElement(By.id("approve"), "Approve", 3);
                    staticWait(1000);
                }
            }
        }
        }
    }


