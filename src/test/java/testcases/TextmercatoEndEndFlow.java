package testcases;

import org.testng.annotations.Test;
import pageobjects.Editor.*;
import pageobjects.Manager.*;
import pageobjects.Manager.DashboardPage;
import pageobjects.Writer.SubmitArticlesPage;
import utils.ExcelParsing;
import utils.WebTestBase;

import java.util.Map;

public class TextmercatoEndEndFlow extends WebTestBase{

     //String projectId ="3918";
/*
*  Created by lakshith on 16/072018 time 3.22Pm
*   Functionality Manager profile page.
* */

  String projectId =null;
    @Test(priority = 0)
    public void manager_profilepage() {
        //Filling Manager profile page.
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        TextmercatoProfilePage profilePage = new TextmercatoProfilePage(driver);
        login.login(ManagerData);
        profilePage.saveProfile(ManagerData);
        //profilePage.verifySuccessMessage();
        //profilePage.verifyErrorMessage();

    }

   @Test(priority =1)
    public void manager_CreateProject() {
        //Manager login and create project and submit to writer.
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        Map<String, String> createProjData = new ExcelParsing().getValueInDiffColumns(excelpath, "Create", "Createproject");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        ProjectSummaryPage projectsummary = new ProjectSummaryPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);
        login.login(ManagerData);
        dashboard.createProject(createProjData);
        dashboard.currentProjects();
        dashboard.descendingCreatedData();
        projectId = dashboard.findCreatedProjects();
        projectsummary.uploadBreif();
        projectsummary.uploadSample();
        projectsummary.uploadData();
        projectsummary.allocationType();
        projectsummary.packetSizeMinusAndVerifyError(createProjData);
        projectsummary.packetSizeMoreAndVerifyError(createProjData);
        projectsummary.verifyWithValidPacketSize(createProjData);

        login.clickLogout();
    }
    @Test(priority = 2)
    public void writer_SubmitArticles() {
        Map<String, String> writerData = new ExcelParsing().getValueInDiffColumns(excelpath, "WriterProfile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        SubmitArticlesPage submitArt = new SubmitArticlesPage(driver);
        pageobjects.Writer.DashboardPage dashPage = new pageobjects.Writer.DashboardPage(driver);
        //writer login and accept project
        login.login(writerData);
        dashPage.selectProjInDashboard(projectId);
        submitArt.submitArticles();
        login.clickLogout();

    }
    @Test(priority = 3)
    public void manager_ReviewArticles() {
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        SubmittedPage submittedpage = new SubmittedPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);

        //Manager login and approves , rejects and allot to editor
        login.login(ManagerData);
        dashboard.currentProjects();
        dashboard.findSubmittedProject(projectId);
        submittedpage.approveReworkAndWriterAssign();
        login.clickLogout();
    }
    @Test(priority = 4)
    public void editor_ApproveAndRejectArticles() {
        Map<String, String> EditorData = new ExcelParsing().getValueInDiffColumns(excelpath, "EditorProfile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);

        //Editor login and accept project
        new TextmercatoLogin(driver).login(EditorData);
        new pageobjects.Editor.DashboardPage(driver).selectProject(projectId);
        //new pageobjects.Editor.DashboardPage(driver).downloadFiles();
        new ProjectsUnderReviewPage(driver).approveAndRejectProject();
        login.clickLogout();
    }
    @Test(priority = 5)
    public void manager_reviewArticlesSubmittedByEditor() {
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        SubmittedPage submittedpage = new SubmittedPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);

        //Manager login and approves , rejects article submitted by Editor
        login.login(ManagerData);
        dashboard.currentProjects();
        dashboard.findSubmittedProject(projectId);
        submittedpage.approveRejectArticleSubmittedByEditor();
        login.clickLogout();
    }
    @Test(priority = 6)
    public void writer_ReworkOnArticles() {
        Map<String, String> writerData = new ExcelParsing().getValueInDiffColumns(excelpath, "WriterProfile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);

        //writer login and rework all projects that rejected and submit
        login.login(writerData);
        new pageobjects.Writer.DashboardPage(driver).selectProjectAndApproveAllRejected(projectId);
        login.clickLogout();
    }
    @Test(priority = 7)
    public void manager_ApproveReworkArticles() {
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        SubmittedPage submittedpage = new SubmittedPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);


        //Manager login and approves , rejects
        login.login(ManagerData);
        dashboard.currentProjects();
        dashboard.findSubmittedProject(projectId);
        submittedpage.approveAllProjectSubmittedByWriter();
        dashboard.completedProjectTab();
        dashboard.findCompletedProject(projectId);
        login.clickLogout();
    }
    @Test(priority = 8)
    public void wrtiter_VerifyProjCompleted(){
        Map<String, String> writerData = new ExcelParsing().getValueInDiffColumns(excelpath, "WriterProfile", "Testdata");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        SubmitArticlesPage submitArt = new SubmitArticlesPage(driver);
        pageobjects.Writer.DashboardPage dashPage = new pageobjects.Writer.DashboardPage(driver);

        //writer login and rework all projects that rejected and submit
        login.login(writerData);
        submitArt.verifyCompletedProjecvts(projectId);
        dashPage.selectProjInDashboard(projectId);
        submitArt.downloadFiles(projectId);

    }

    // Full flow was created by Lakshith.
    @Test(priority =9)
    public void manager_Tickets(){
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        // from here i was giving the commend to pick the data from the excelsheet, In the excel sheet select the coloum name is "Validprofile" and page name is Testdata, from here we need to select the data.
        Map<String, String> Ticketsdata = new ExcelParsing().getValueInDiffColumns(excelpath, "Create", "Createproject");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        DashboardPage startTickets = new DashboardPage(driver);

        //Manager will login and open the tickets.

        login.login(ManagerData);
        startTickets.createTickets(Ticketsdata);
        //startTickets.closeTicketFrame();

    }

    @Test(priority =10)
    public void manager_Messages() {
        Map<String, String> ManagerData = new ExcelParsing().getValueInDiffColumns(excelpath, "Validprofile", "Testdata");
        // from here i was giving the commend to pick the data from the excelsheet, In the excel sheet select the coloum name is "Validprofile" and page name is Testdata, from here we need to select the data.
        Map<String, String> Messagedata = new ExcelParsing().getValueInDiffColumns(excelpath, "Create", "Createproject");
        TextmercatoLogin login = new TextmercatoLogin(driver);
        DashboardPage FAQMessage = new DashboardPage(driver);

        //Manager will login and open the tickets.

        login.login(ManagerData);
        FAQMessage.Message(Messagedata);
        FAQMessage.Inbox(Messagedata);

    }






}