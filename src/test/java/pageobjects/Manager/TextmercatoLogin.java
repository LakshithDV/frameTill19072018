package pageobjects.Manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebBasePage;

import java.util.Map;

/**
 * Created by theem_000 on 13-04-2018.
 *
 *
 */
public class TextmercatoLogin extends WebBasePage {

    public TextmercatoLogin(WebDriver driver){
        super(driver);
    }

    public void navigateToUrl(Map<String,String> data){
        open(data.get("Url"));
    }

    public void clickLogin(){
        clickElementVisible(By.className("login-click"),"Login",10);
    }

    public void clickLogout(){
        clickElementVisible(By.xpath("//span[contains(text(),'Log out')]"),"Logout",10);
    }

    public void enterUserName(Map<String,String> data){

        enterElementVisible(By.name("email"),data.get("Email"),"username field",10);
    }

    public void enterPassword(Map<String,String> data){
        enterElementVisible(By.name("password"),data.get("Password"),"Password",10);
    }

    public void clickSignIn(){
        clickElementVisible(By.name("login"),"Sign in",10);
    }

    public void login(Map<String,String> data){

        navigateToUrl(data);
        clickLogin();
        enterUserName(data);
        enterPassword(data);
        clickSignIn();
    }
}
