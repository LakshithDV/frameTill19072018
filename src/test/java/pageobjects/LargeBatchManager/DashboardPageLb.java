package pageobjects.LargeBatchManager;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebBasePage;

import java.util.Map;

import static reporting.ComplexReportFactory.getTest;

/**
 * Created by Lakshith_on 13-04-2018.
 *  Manager Cearting large Batch project
 *
 */
public class DashboardPageLb extends WebBasePage {


    public DashboardPageLb(WebDriver driver) {
        super(driver);
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

    public void clcikLargeBatch() {
        clickElementVisible(By.xpath("//input[@id='large_batch']"), "large_batch", 10);
    }

    public void create() {
        clickElementVisible(By.name("project_submit"), "Create button", 10);
    }

    public void verifySuccessMsg() {
        findElementVisibility(By.xpath("//div[@class='success_msg' and(text()='Project is created')]"), 5);
    }

    public void currentProjects() {
        clickElementVisible(By.xpath("//a[text()='Current projects']"), "Current projects Tab", 10);
    }

    public void createProject(Map<String, String> data) {

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
        this.clcikLargeBatch();
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
}

