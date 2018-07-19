package testcases;

import org.testng.annotations.Test;
import pageobjects.Manager.DashboardPage;
import pageobjects.Manager.TextmercatoLogin;
import utils.ExcelParsing;
import utils.WebTestBase;

import java.util.Map;

public class LoginTest extends WebTestBase {


    @Test
    public void login1(){
        Map<String,String> data=new ExcelParsing().getValueInDiffColumns(excelpath,"Validprofile","Testdata");
        new TextmercatoLogin(driver).login(data);
        new DashboardPage(driver).createProject(data);

        //new DashboardPage(driver).createTickets(data);
    }
}
