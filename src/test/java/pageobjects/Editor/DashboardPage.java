package pageobjects.Editor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.List;

/**
 * Created by theem_000 on 19-04-2018.
 */
public class DashboardPage extends WebBasePage {
    WebDriver driver;
    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void selectProject(String projectId) {
        clickByJavascript(By.xpath("//*[@project_id='"+projectId+"']"));
    }
    public void downloadFiles(){
        clickElement(By.xpath("//a[contains(text(),'Brief')]"),"Brief",3);
        if(verifyElement(By.id("myModal"),"Brief content",3).isDisplayed()){
            clickElement(By.xpath("(//button[contains(text(),'Close')])[last()]"),"Close button",3);
        }
        clickElement(By.xpath("//a[contains(text(),'Sample')]"),"Sample",3);
        clickElement(By.xpath("//a[contains(text(),'Data')]"),"Data",3);
    }
}
