package pageobjects.Manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.List;
import java.util.Map;

/**
 * Created by theem_Lakshith DV on 19-04-2018.
 * Comp
 *
 *
 */
public class SubmittedPage extends WebBasePage {
    WebDriver driver;
    public SubmittedPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void approveReworkAndWriterAssign() {
        staticWait(2000);
        List<WebElement> noOfArticle = driver.findElements(By.xpath("//*[@class='article_label_pending']"));

        for (int i = 1; i <=noOfArticle.size(); i++) {
            if (i > 1) {
                staticWait(1000);
                clickElement(By.xpath("(//*[@class='article_label_pending'])[1]"), "Pending article" + i, 3);
            }
            if (i == 1) {
                staticWait(1000);
                clickElement(By.id("approve"), "Approve", 3);
                staticWait(5000);
            } else if (i == 2) {
                staticWait(5000);
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);
                String msg = getText(By.xpath("//span[contains(text(),'You cant leave this empty')]"), "Validation message", 3);
                Assert.assertEquals(msg, "You cant leave this empty");
                enterElementVisible(By.id("comment"), "manager reject: testing purpose", "comment", 3);
                scrollToWebelement(By.id("reject"));
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);

            } else if (i >= 3) {
                clickElement(By.xpath("//span[contains(text(),'Editor')]"), "reject", 3);
                List<WebElement> noOfArticlePending = driver.findElements(By.xpath("//*[@class='article_label_pending']"));
                int size = noOfArticlePending.size();
                clickElement(By.xpath("//span[contains(text(),'Editor')]"), "Editor", 3);
                enterElementVisible(By.name("packet_size"), size + "", "Packet sixe", 10);
                searchEditor();
                verifyAlert(size);
                break;
            }
        }
    }

            public void searchEditor(){
                staticWait(2000);
                enterElementVisible(By.id("writer_search"),"lak","Editor Search",10);
                clickElementVisible(By.id("project_search_editor"),"User search button",10);
                //clickElementVisible(By.xpath("//label[text()='lakshdv4u@gmail.com']//preceding-sibling::input[1]"),"lakshdv4u",10);
                clickElementVisible(By.xpath("//input[@id='writer_search_1070']"),"lakshdv4u",10);   /* which i have change the code*/
                clickElementVisible(By.id("editor_allot_submit"),"Submit button",10);
            }
            public void verifyAlert(int packets){
                Alert alert=driver.switchTo().alert();
                String msg = alert.getText();
                Assert.assertTrue(msg.contains("You are allotting "+packets+" jobs"));
                alert.accept();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    public void approveRejectArticleSubmittedByEditor(){
        selectValueWithValue(By.id("project_article_filter"),"proofread","project_article_filter",3);
        staticWait(5000);
        List<WebElement> noOfArticle = driver.findElements(By.xpath("//*[@class='article_label_rejected']"));
        for (int i = 1; i <= noOfArticle.size(); i++) {
            if (i > 1) {
                clickElement(By.xpath("(//*[@class='article_label_rejected'])[1]"), "Pending article" + i, 3);
            }
            if (i >= 2) {
                staticWait(1000);
                clickElement(By.id("approve"), "Approve", 3);
                staticWait(5000);
            } else if (i==1) {
                staticWait(5000);
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);
                String msg = getText(By.xpath("//span[contains(text(),'You cant leave this empty')]"), "Validation message", 3);
                Assert.assertEquals(msg, "You cant leave this empty");
                enterElementVisible(By.id("comment"), "Editor reject: testing purpose", "comment", 3);
                scrollToWebelement(By.id("reject"));
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);
            }
        }
        staticWait(1000);
        selectValueWithValue(By.id("project_article_filter"),"proofread","project_article_filter",3);
        staticWait(5000);
        List<WebElement> noOfArticleApproved = driver.findElements(By.xpath("//*[@class='article_label_approved']"));
        for (int i = 1; i <= noOfArticleApproved.size(); i++) {
            if (i >= 1) {
                clickElement(By.xpath("(//*[@class='article_label_approved'])[1]"), "Approved article" + i, 3);
                staticWait(2000);

            }
            if (i >= 2) {
                staticWait(1000);
                clickElement(By.id("approve"), "Approve", 3);
                staticWait(5000);
            } else if (i==1) {
                staticWait(5000);
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);
                String msg = getText(By.xpath("//span[contains(text(),'You cant leave this empty')]"), "Validation message", 3);
                Assert.assertEquals(msg, "You cant leave this empty");
                enterElementVisible(By.id("comment"), "Editor reject: testing purpose", "comment", 3);
                scrollToWebelement(By.id("reject"));
                clickElement(By.id("reject"), "reject", 3);
                staticWait(5000);
            }
        }
    }
    public void approveAllProjectSubmittedByWriter() {
        staticWait(2000);
        List<WebElement> noOfArticle = driver.findElements(By.xpath("//*[@class='article_label_pending']"));

        for (int i = 1; i <= noOfArticle.size(); i++) {
            if (i > 1) {
                staticWait(1000);
                clickElement(By.xpath("(//*[@class='article_label_pending'])[1]"), "Pending article" + i, 3);
            }
            if (i >= 1) {
                staticWait(1000);
                clickElement(By.id("approve"), "Approve", 3);
                staticWait(5000);
            }







        }
    }
}
