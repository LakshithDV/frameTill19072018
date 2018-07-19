package pageobjects.Manager;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebBasePage;

import java.util.Map;
import java.util.Properties;

import static reporting.ComplexReportFactory.getTest;
/*
* Created by lakshith DV on 07/13/2018 time 02:56pm
* Functionality : Profile filling and verifying.
*
* */
public class TextmercatoProfilePage extends WebBasePage {

    WebDriver driver;
    public TextmercatoProfilePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void clickProfile(){
        clickElementVisible(By.xpath("//span[contains(text(),'Profile')]"), "Click Profile", 10);
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
        staticWait(5000);
    }
/*******************************************************************************************************
 *  Change Password
 * ****************************************************************************************************/
    public void OldPass(Map<String,String> data){

        clickElementVisible(By.xpath("//input[@placeholder='Old password']"),"Oldpassword",10);
    }

    public void newPass(Map<String,String> data){

        clickElementVisible(By.xpath("//input[@placeholder='New password']"),"Newpassword",10);
    }

    public void conformpass(Map<String,String> data){

        clickElementVisible(By.xpath("//input[@placeholder='Password Confirm']"),"comfmPassword",10);
    }

    public void passchangesubmit(){

        clickElementVisible(By.xpath("//input[@id='change_pwd_form_submit']"),"pass Submit",10);
    }

    /**********************************************************************************************************************************
*   Payment Details update
* ********************************************************************************************************************************/
    public void paymentDetails(){

        clickElementVisible(By.xpath("//a[@id='ui-id-2']"), "Payment Details", 10);
    }

    public void bankName(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='bank_name']"),data.get("Bank Name"),"Bank name",10);
    }

    public void bankBranch(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='bank_branch']"),data.get("Bank Branch"),"Bank Branch",10);
    }

    public void accname(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='account_holder_name']"),data.get("holder name"),"holder name",10);
    }

    public void accNumber(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='account_num']"),data.get("Acc Number"),"Acc Number",10);
    }

    public void IFIC(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='ifsc']"),data.get("IFSC Code"),"IFSC Code",10);
    }

    public void PAN(Map<String,String> data){

        enterElementVisible(By.xpath("//input[@name='pan_number']"),data.get("PAN"),"PAN number",10);
    }

    public void submitBankDetails(){

        clickElementVisible(By.xpath("//input[@id='bank_form_submit']"),"Submit bank details",10);
    }



    public TextmercatoProfilePage saveProfile(Map<String,String> data){
        clickProfile();
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

        /*OldPass(data);
        newPass(data);
        conformpass(data);
        passchangesubmit();*/

        paymentDetails();
        bankName(data);
        bankBranch(data);
        accname(data);
        accNumber(data);
        IFIC(data);
        PAN(data);
        submitBankDetails();

        return this;
    }

    public void verifySuccessMessage(){
        String source=driver.getPageSource();
        if(source.contains("User details updated successfully")){
            getTest().log(LogStatus.PASS,"Profile updated succesfully");
        }else{
            getTest().log(LogStatus.FAIL,"Profile not updated succesfully");
        }
    }

    public void verifyErrorMessage(){
        String source=driver.getPageSource();
        if(!source.contains("User details updated successfully")){
            getTest().log(LogStatus.PASS,"Profile not updated");
        }else{
            getTest().log(LogStatus.FAIL,"Profile still updated");
        }
    }

}
