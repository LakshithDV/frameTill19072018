package pageobjects.Manager;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.Map;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by Lakshith_on 12-07-2018.
 *
 *
 */
public class DashboardPage extends WebBasePage {

    /*
    *  Hare we are working with the dashabard  and we are trying to create large batch.
    *
    * */
    WebDriver driver;

    public  DashboardPage(WebDriver driver) {

        super(driver);
    }

    public void Dashboard() {

        clickElementVisible(By.id("create-project"), "Create Project", 10);
    }

    public void createProjectTab() {

        clickElementVisible(By.id("create-project"), "Create Project", 10);
    }

    public void client(Map<String, String> data) {
        selectValueWithText(By.id("project_end_client"), data.get("Client"), "Client", 10);
    }

    public void category(Map<String, String> data) {
        selectValueWithText(By.name("project_category"), data.get("Category"), "Category", 10);
    }

    public void clientCost(Map<String, String> data) {
        enterElementVisible(By.name("project_client_cost"), data.get("ClientCost"), "Client cost", 10);
    }

    public void language(Map<String, String> data) {
        selectValueWithText(By.id("project_lang_preference"), data.get("Language"), "Language", 10);
    }

    public void editingRate(Map<String, String> data) {
        enterElementVisible(By.name("project_editing_rate"), data.get("EditingRate"), "Editing Rate", 10);
    }

    public void contentManager(Map<String, String> data) {
        selectValueWithText(By.name("project_editor"), data.get("ContentManager"), "Content Manager", 10);
    }

    public void writerPay(Map<String, String> data) {
        enterElementVisible(By.name("project_writer_pay"), data.get("WriterPay"), "Writer Pay", 10);
    }

    public void writerRating(Map<String, String> data) {
        selectValueWithText(By.name("project_writer_rating"), data.get("WriterRating"), "Writer Rating", 10);
    }

    public void type(Map<String, String> data) {
        selectValueWithText(By.name("project_type"), data.get("Type"), "Type", 10);
    }

    public void deadline(Map<String, String> data) {
        clickElementVisible(By.name("project_due_date"), "Deadline textbox", 10);
        clickElementVisible(By.xpath("//td[contains(@class,' ui-datepicker-current-day')]"), "Dedline calender", 10);
    }

    public void paymentType(Map<String, String> data) {
        selectValueWithText(By.name("project_pay_type"), data.get("PaymentType"), "Projectpay type", 10);
    }

    public void advanceAmount(Map<String, String> data) {
        enterElementVisible(By.name("project_advance_amount"), data.get("AdvanceAmount"), "ProjectAdvance Amount", 10);
    }

    public void create() {
        clickElementVisible(By.name("project_submit"), "Create button", 10);
    }

    public void verifySuccessMsg() {
        findElementVisibility(By.xpath("//div[@class='success_msg' and(text()='Project is created')]"), 5);
    }

/**************************************************************************************************************************************/

    public void currentProjects() {
        clickElementVisible(By.xpath("//a[text()='Current projects']"), "Current projects Tab", 10);
    }

    public void createProject(Map<String, String> data) {

        this.Dashboard();
        this.createProjectTab();
        this.client(data);
        this.category(data);
        this.clientCost(data);
        this.language(data);
        this.editingRate(data);
        this.contentManager(data);
        this.writerPay(data);
        this.writerRating(data);
        this.type(data);
        this.deadline(data);
        this.paymentType(data);
        this.advanceAmount(data);
        this.create();
        this.verifySuccessMsg();
    }

    public String findCreatedProjects() {
        String getColText;
        String splitId = null;
        boolean projectFound = false;
        int tableCount = getRowCount(By.xpath("//table[@id='current_projects_table']//tr/td[1]"));
        for (int tabC = 1; tabC <= tableCount; tabC++) {
            getColText = getText(By.xpath("//table[@id='current_projects_table']//tr[rep]/td[3]".replace("rep", Integer.toString(tabC))), "Created table", 2);
            if (getColText.contains("test")) {
                splitId = getColText.trim().split("#")[1];
                getTest().log(LogStatus.PASS, "Latest project id - " + splitId);
                //clickElementVisible(By.xpath("//table[@id='current_table']//tr[rep]/td[6]".replace("rep",Integer.toString(tabC))),"Total column cell",10);
                clickElementVisible(By.xpath("//table[@id='current_projects_table']//tr[rep]/td[6]".replace("rep", Integer.toString(tabC))), "Total column cell", 10);
                staticWait(4000);
                projectFound = true;
                break;
            }
        }
        if (projectFound) {
            getTest().log(LogStatus.PASS, "Project is created ");
        } else {
            getTest().log(LogStatus.FAIL, "Project is not created  ");
        }
        return splitId;
    }

    public void descendingCreatedData() {
        staticWait(4000);
        clickElementVisible(By.xpath("(//p[text()='Name'])[1]"), "Name column", 10);
        staticWait(2000);
        clickElementVisible(By.xpath("(//p[text()='Name'])[1]"), "Name column", 10);
        staticWait(2000);
    }

    //To find project in submitted submitted status
    public void findSubmittedProject(String projectId) {
        clickElementVisible(By.xpath("(//td[@project_id='" + projectId + "'])[4]"), "Submitted project", 10);
    }

    public String findNoOfProjectsInEditing(String projectId) {
        String noofProj = getText(By.xpath("(//td[@project_id='" + projectId + "'])[4]"), "Submitted project", 10);
        return noofProj;
    }

    public void completedProjectTab() {

        clickElementVisible(By.xpath("//span[contains(text(),'Dashboard')]"), "Dashboard", 10);
        //clickElementVisible(By.id("ui-id-2"),"Completed Project",10);
        clickElementVisible(By.xpath("//li[@id='1']"), "completed_projects_tab", 10);   // lakshith Changed code

    }

    public void findCompletedProject(String projectId) {

           /* try{
                Assert.assertTrue(verifyElement(By.xpath("//div[@id='tabs-2']//tbody//tr[1]//td[3][@project_id='"+projectId+"']"),"Completed project Id",10).isDisplayed());
                //Assert.assertTrue(verifyElement(By.xpath("//td[@project_id='"+projectId+"']"),"Completed project Id",10).isDisplayed());
                //clickElementVisible(By.xpath("//td[@project_id='"+projectId+"']"),"Completed project Id",10);
                //clickElementVisible(By.xpath("//img[@project-id='"+projectId+"']"),"Completed project download button",10);
                clickElementVisible(By.xpath("//div[@id='tabs-2']//tbody//tr[1]//td[3][@project_id='"+projectId+"']"),"Completed project download button",10);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }*/

    }
/***************************************************************************************************************/

/*
* Created by Lakshith DV on 07/13/2018 Time 10.15AM
* Functionality: Manager dashboard Tickets.
*
* */


   public void ticketsTab() {
       //Click Dashboard.
       clickElementVisible(By.xpath("//span[contains(text(),'Dashboard')]"), "Dashboard", 10);
       // Clcik tickets option.
       clickElementVisible(By.xpath("//a[@id='ticket_new']"), "Dashboard", 10);
      /* staticWait(1000);
       String msg = getText(By.xpath("//h3[@class='sec_title center']"), "Number of open tickets", 3);
       Assert.assertEquals(msg, "Number of open tickets 1");*/
       //To register new tickets clcik open button.
       clickElementVisible(By.xpath("//input[@id='register_new_ticket']"), "Open", 10);
       staticWait(3000);
       String msg1 = getText(By.xpath("//div[contains(text(),'Create New Ticket')]"), "Create New Ticket", 3);
       Assert.assertEquals(msg1, "Create New Ticket");
   }


    public void subjectToSend(Map<String, String> data) {

        // Compose Subject to send.
        try {
            staticWait(2000);
            clickElementVisible(By.xpath("//textarea[@name='subject']"), "Subject", 10);
            enterElementVisible(By.xpath("//textarea[@name='subject']"), data.get("Subject"), "Subject", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void messageToSend(Map<String, String> data) {
        try {
            // Compose Message to send.
            clickElementVisible(By.xpath("//textarea[@name='message']"), "message", 10);
            enterElementVisible(By.xpath("//textarea[@name='message']"), data.get("Message"), "message", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Sendto(Map<String, String> data) {
        try {
            // Select the Manager name you want to send message.
            selectValueWithText(By.xpath("//select[@id='rased_to']"), data.get("SendTo"), "rased_to", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTicket() {
        try {
            // By clicking this button you are goignt to create ticket.
            clickElementVisible(By.xpath("//input[@name='create_ticket'][@type='submit']"), "create_ticket", 10);
            staticWait(1000);
            String msg = getText(By.xpath("//div[contains(text(),'Ticket issued successfully')]"), "success_msg", 3);
            Assert.assertEquals(msg, "Ticket issued successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTickets(){
       // by Clicking this you are going to close your tickets.
        clickElementVisible(By.xpath("//tr[1]//td[@class='details-control sorting_1']"), "Expand Ticket tab", 10);
        clickElementVisible(By.xpath("//td[@colspan='8']//input[@value='Comment']"), "Click Comment button", 10);
    }

    public void ticketCommend(Map<String, String> data){

        String msg = getText(By.xpath("//div[contains(text(),'Add New Comment')]"), "Add new Comment", 3);
        Assert.assertEquals(msg, "Add New Comment");
        enterElementVisible(By.xpath("//textarea[@name='comment']"), data.get("Comment"), "message", 10);
        clickElementVisible(By.xpath("//input[@name='create_comment'][@type='submit']"), "Create Comment", 10);
        //clickElementVisible(By.xpath("//div[@id='ticket_card_new_comment_light_box']//a[@class='cls-btn']"), "Close Comment form", 10);

    }

    public void verfiyComment(){

        clickElementVisible(By.xpath("//tr[1]//td[@class='details-control sorting_1']"), "explore ticket", 10);
        String msg = getText(By.xpath("//td[@colspan='8']//h4[contains(text(),'Comments')]"), "Check Comment", 3);
        Assert.assertEquals(msg, "Comments");

    }

    public void statusUpdate(Map<String, String> data){

        clickElementVisible(By.xpath("//tr[1]//td[@class='details-control sorting_1']"), "explore ticket", 10);
        //clickElementVisible(By.xpath("//select[@id='status_change_for_ticket'][@class='select_box_small']"), "Expand Ticket tab", 10);
        selectValueWithText(By.xpath("//select[@id='status_change_for_ticket'][@class='select_box_small']"), data.get("Status"), "Status", 10);
        String msg = getText(By.xpath("//div[@id='success_message_from_javascript']"), "Successfully States updated", 3);
        Assert.assertEquals(msg, "Successfully updated");
        clickElementVisible(By.xpath("//tr[1]//td[@class='details-control sorting_1']"), "explore ticket", 10);
    }

    public void checkSerchbutton(Map<String, String> data){


       try {

           enterElementVisible(By.xpath("//input[@type='search'][@aria-controls='ticket_data_table']"), data.get("Search"), "Search Perticular person", 10);
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    public void searchFromTo(Map<String, String> data){

       try {
           clickElementVisible(By.xpath("//span[contains(text(),'Dashboard')]"), "Click Dashboard", 10);
           clickElementVisible(By.xpath("//a[@id='ticket_new']"), "Click tickets tab", 10);
           selectValueWithText(By.xpath("//select[@id='filter_tickets_with_to'][@class='custom-select']"), data.get("To"), "Search to", 10);
           staticWait(2000);
           selectValueWithText(By.xpath("//select[@id='filter_tickets_with_from'][@class='custom-select']"), data.get("From"), "Search From", 10);
       }catch(Exception e){

        e.printStackTrace();
       }
    }

/*--------------------------------------------------------------------*/
    //Calling all function in this class "createTickets".

    public void createTickets(Map<String, String> data) {

       this.ticketsTab();
       this.subjectToSend(data);
       this.messageToSend(data);
       this.Sendto(data);
       this.createTicket();
       this.clearTickets();
       this.ticketCommend(data);
       this.verfiyComment();
       this.statusUpdate(data);
       //this.checkSerchbutton(data);
       this.searchFromTo(data);



    }

/*************************************************************************************************************************
 *  Created by Lakshith DV on 07/17/2018 time 03:51PM
 *  Dashboard Message tob FAQ functionality
 * ************************************************************************************************************************/



    public void DashboaedMessageTab() {
        //Click Dashboard.
        clickElementVisible(By.xpath("//ul[@class='cbp-vimenu']//li[7]//a[1]"), "Dashboard Message", 10);
        String msg = getText(By.xpath("//h2[contains(text(),'Messages')]"), "Message Icone", 3);
        Assert.assertEquals(msg, "Messages");
    }

    public void faqOfAllotment(Map<String, String> data) {
        try {
            clickElementVisible(By.xpath("//label[@for='allotment']"), "clcik Allotment icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("AllotmentQ1"), "FAQS Choose a Question from Allotment", 10);
            String msg = getText(By.xpath("//p[@id='projallotlink']"), "Answer for FAQ question", 3);
            Assert.assertEquals(msg, "Only writers can view this answer.");
            // For second Question.
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("AllotmentQ2"), "FAQS Choose a Question", 10);
            staticWait(2000);
             /*String msg1 =getText(By.xpath("//textarea[@id='answer_space']/."), "msg_body", 3);
            staticWait(2000);
            Assert.assertEquals(msg1, "The allotment is on a first come first serve basis. If the project is not accepted in-time, the allotment will move to the next person.");
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void faqOfRegistration (Map<String, String> data) {

        try {
            clickElementVisible(By.xpath("//label[@for='registration']"), "Clcik Registration icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("regidtration"), "FAQS Choose a Question from Registration", 10);
           /* String msg = getText(By.xpath("//textarea[@id='answer_space']"), "FAQS Answer for Registration", 3);
            Assert.assertEquals(msg, data.get("RegiAnsw"));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void faqsOFPayments (Map<String, String> data){

        try{
            clickElementVisible(By.xpath("//label[@for='payments']"), "Click Payments icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("Payments"), "FAQS Choose a Question from Payments", 10);
            /*String msg = getText(By.xpath("//textarea[@id='answer_space']"), "FAQS Answer for Payments", 3);
            Assert.assertEquals(msg, "This is in-line with business payment cycles.");*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void faqsOfTests (Map<String, String> data){
        try{
            clickElementVisible(By.xpath("//label[@for='tests']"), "Clcik Tests icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("Tests"), "FAQS Choose a Question from Tests", 10);
            /*String msg = getText(By.xpath("//textarea[@id='answer_space']"), "FAQS Answer for Tests", 3);
            Assert.assertEquals(msg, data.get("TestAnsw"));*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void faqOfProject (Map<String, String> data){
        try {
            clickElementVisible(By.xpath("//label[@for='projects']"), "Clcik Project icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("Projects"), "FAQS Choose a Question from Project", 10);
           /* String msg = getText(By.xpath("//textarea[@id='answer_space']"), "FAQS Answer for Project", 3);
            Assert.assertEquals(msg, data.get("ProjectAnsw"));*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void faqOfTax(Map<String, String> data){
        try{
            clickElementVisible(By.xpath("//label[@for='tax']"), "Clcik Tax icon", 10);
            staticWait(2000);
            selectValueWithText(By.xpath("//select[@id='faqs_questions']"), data.get("Tax"), "FAQS Choose a Question from Tax", 10);
            /*String msg = getText(By.xpath("//textarea[@id='answer_space']"), "FAQS Answer for Tax", 3);
            Assert.assertEquals(msg, data.get("TaxAnsw"));*/

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void faq(Map<String, String> data) {

        DashboaedMessageTab();
        faqOfAllotment(data);
        faqOfRegistration(data);
        faqsOFPayments(data);
        faqsOfTests(data);
        faqOfProject(data);
        faqOfTax(data);

    }
/************************************************************************************************************
 *  Crested by Lakshith DV on 18/07/2018
 *  Functionality : Message Compose tab.
 * ***********************************************************************************************************/

    public void MessageComposetab(Map<String, String> data) {

        try {
            clickElementVisible(By.xpath("//a[@id='ui-id-3']"), "clcik Compose icon", 10);
            staticWait(2000);
            String msg = getText(By.xpath("//label[contains(text(),'Subject')]"), "Verify the compose tab open", 3);
            Assert.assertEquals(msg, "Subject");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void composeToHr(Map<String, String> data) {


        clickElementVisible(By.xpath("//label[@for='hr']"), "clcik Hr icon", 10);
        enterElementVisible(By.xpath("//input[@id='subject']"), data.get("HrSubject"), "Compose MEssage Hr Subject", 10);
        enterElementVisible(By.xpath("//form[@id='msg_form']//textarea[@name='msg_body']"), data.get("HrMatter"), "Compose Message Matter", 10);
        clickElementVisible(By.xpath("//input[@id='msg_submit'][@type='submit']"), "Clcik Send message to Hr", 10);

    }

    public void composeToAccounts (Map<String, String> data) {


        clickElementVisible(By.xpath("//label[@for='accounts']"), "clcik Accounts icon", 10);
        enterElementVisible(By.xpath("//input[@id='subject']"), data.get("AccountSub"), "Compose Message Accountent Subject", 10);
        enterElementVisible(By.xpath("//form[@id='msg_form']//textarea[@name='msg_body']"), data.get("AccontsMatter"), "Compose Message Matter", 10);
        clickElementVisible(By.xpath("//input[@id='msg_submit'][@type='submit']"), "Clcik Send message to Accountent", 10);

    }

    public void composeToEditor (Map<String, String> data) {

        clickElementVisible(By.xpath("//label[@for='editor_radio']"), "clcik Editor icon", 10);
        selectValueWithText(By.xpath("//select[@id='projects_dropdown']"),data.get("Editor"), "editor_name", 10);
        enterElementVisible(By.xpath("//input[@id='subject']"), data.get("EditorSub"), "Compose Message Accountent Subject", 10);
        staticWait(2000);
        enterElementVisible(By.xpath("//form[@id='msg_form']//textarea[@name='msg_body']"), data.get("EditorMatr"), "Compose Message Matter", 10);
        clickElementVisible(By.xpath("//input[@id='msg_submit'][@type='submit']"), "Clcik Send message to Editor", 10);

    }

    public void composeToWriter (Map<String, String> data) {


        clickElementVisible(By.xpath("//label[@for='writer_radio']"), "clcik writer icon", 10);
        // For writer data lakshit we have taken from editor only insted of creating new excel cell.
        enterElementVisible(By.xpath("//input[@id='msg_writer_search']"), data.get("Editor"), "Compose Message Editor Subject", 10);
        staticWait(4000);
        //enterElementVisible(By.xpath("//input[@id='msg_writer_search']"), data.get("Editor"), "Compose Message Editor Subject", 10);
        //selectValueWithText(By.xpath("//input[@id='msg_writer_search']"),data.get("SecWriter"), "Compose Message Accountent Subject", 10);
        enterElementVisible(By.xpath("//input[@id='subject']"), data.get("AccountSub"), "Compose Message Editor Subject", 10);
        enterElementVisible(By.xpath("//form[@id='msg_form']//textarea[@name='msg_body']"), data.get("AccontsMatter"), "Compose Message Matter", 10);
        clickElementVisible(By.xpath("//input[@id='msg_submit'][@type='submit']"), "Clcik Send message to Editor", 10);

    }

    public void Uplodfile (Map<String, String> data) {


        findElementVisibility(By.xpath("//label[@for='attachment']"),10);
        String filename=System.getProperty("user.dir")+"//src//main//resources//uploadfiles//sample.doc";
        WebElement uploadFile = driver.findElement(By.xpath("//label[@for='attachment']"));
        ((RemoteWebDriver) driver).executeScript("arguments[0].style =''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", uploadFile);
        uploadFile.sendKeys(filename);
        staticWait(1000);

    }

    public void Compose(Map<String, String> data) {

        MessageComposetab(data);
        composeToHr(data);
        MessageComposetab(data);
        composeToAccounts(data);
        MessageComposetab(data);
        composeToEditor(data);
        MessageComposetab(data);
        composeToWriter(data);
        Uplodfile(data);
        }
    public void Message (Map<String, String> data){

        faq(data);
        Compose(data);

    }

    /*************************************************************************************************************************
     *  Created By lakshith DV on 07/18/2018 at 12.50PM
     *  Message Inbox Testing.
     *
     * ************************************************************************************************************************/

    public void MessaheInboxTab() {
            try {
                clickElementVisible(By.xpath("//a[@id='ui-id-4']"), "clcik Inbox icon", 10);
                staticWait(2000);
                String msg = getText(By.xpath("//p[@class='mes-attach']"), "Verify the compose tab open", 3);
                Assert.assertEquals(msg, "Attachment");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void MessaheRepaly(Map<String, String> data) {

        //clickElementVisible(By.xpath("//div[contains(@class,'active')]"), "clcik to Replay message", 10);
        enterElementVisible(By.xpath("//textarea[contains(@name,'msg_thread_body')]"), data.get("MsgBody"), "Replaying for message", 10);

    }

    public void upload_Replay(Map<String, String> data) {

        findElementVisibility(By.xpath("//label[contains(@for,'attachment1')]//img[contains(@class,'mCS_img_loaded')]"),10);
        String filename=System.getProperty("user.dir")+"//src//main//resources//uploadfiles//sample.doc";
        WebElement uploadFile = driver.findElement(By.xpath("//label[contains(@for,'attachment1')]//img[contains(@class,'mCS_img_loaded')]"));
        ((RemoteWebDriver) driver).executeScript("arguments[0].style =''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", uploadFile);
        uploadFile.sendKeys(filename);
        staticWait(1000);


    }

    public void MessaheInbox(Map<String, String> data) {

        MessaheInboxTab();
        MessaheRepaly(data);
        //upload_Replay(data);

    }

    public void Inbox(Map<String, String> data) {

        MessaheInbox(data);

    }

}

