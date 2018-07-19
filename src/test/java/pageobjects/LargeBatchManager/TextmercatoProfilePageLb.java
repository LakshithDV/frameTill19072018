package pageobjects.LargeBatchManager;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebBasePage;

import java.util.Map;

import static reporting.ComplexReportFactory.getTest;

public class TextmercatoProfilePageLb extends WebBasePage {

    WebDriver driver;
    public TextmercatoProfilePageLb(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void navigateToUrl(Map<String,String> data){
        open(data.get("Url"));
    }

    public void firstName(Map<String,String> data){
        enterElementVisible(By.name("first_name"),data.get("Firstname"),"Firstname field",10);
    }

    public void lastName(Map<String,String> data){
        enterElementVisible(By.name("last_name"),data.get("Lastname"),"Lastname field",10);
    }

    public void dob(Map<String,String> data){
        enterElementVisibleWC(By.name("inputdob"),data.get("Dob"),"Dob field",10);
    }

    public void mobile(Map<String,String> data){
        enterElementVisible(By.name("mobile_num"),data.get("Mobile"),"Mobile field",10);
    }

    public void address1(Map<String,String> data){
        enterElementVisible(By.name("address_line_1"),data.get("Address1"),"Address1 field",10);
    }

    public void address2(Map<String,String> data){
        enterElementVisible(By.name("address_line_2"),data.get("Address2"),"Address2 field",10);
    }

    public void pincode(Map<String,String> data){
        enterElementVisible(By.name("pincode"),data.get("Pincode"),"Pincode field",10);
    }

    public void city(Map<String,String> data){
        enterElementVisible(By.name("city"),data.get("City"),"City field",10);
    }

    public void country(Map<String,String> data){
        enterElementVisible(By.name("country"),data.get("Country"),"Country field",10);
    }

    public void submit(){
        clickElementVisible(By.name("personalform_submit"),"Submit",10);
    }

    public TextmercatoProfilePageLb saveProfile(Map<String,String> data){
        navigateToUrl(data);
        firstName(data);
        lastName(data);
        dob(data);
        mobile(data);
        address1(data);
        address2(data);
        pincode(data);
        city(data);
        country(data);
        submit();
        return this;
    }

    public void verifySuccessMessage(){
        String source=driver.getPageSource();
        if(source.contains("updated successfully")){
            getTest().log(LogStatus.PASS,"Profile updated succesfully");
        }else{
            getTest().log(LogStatus.FAIL,"Profile not updated succesfully");
        }
    }

    public void verifyErrorMessage(){
        String source=driver.getPageSource();
        if(!source.contains("updated successfully")){
            getTest().log(LogStatus.PASS,"Profile not updated");
        }else{
            getTest().log(LogStatus.FAIL,"Profile still updated");
        }
    }

}
