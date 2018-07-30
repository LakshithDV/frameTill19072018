package pageobjects.FrontEnd;

import org.bouncycastle.asn1.tsp.TSTInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.WebBasePage;

import java.util.Map;

/**
 * Created by Lakshith DV on 27/07/2018.
 *       FrontEnd Testing
 */
public class frontEnd extends WebBasePage {
    WebDriver driver;
    public frontEnd(WebDriver driver){

        super(driver);
    }

    public void navigateToUrl(Map<String,String> data){

        open(data.get("Url"));
        String msg = getText(By.xpath("//h3[contains(text(),'Proven & tested')]"), "Open textmercato web page", 3);
        Assert.assertEquals(msg, "Proven & tested");
        staticWait(2000);

    }
    public void whatWeDo(){

        clickElementVisible(By.xpath("//a[@id='first_whatwe_do_a']"),"Click the what we do tab",10);
        String msg = getText(By.xpath("//div[@class='tt']//h3[contains(text(),'Copywriting')]"), "Verifiy what we do", 3);
        Assert.assertEquals(msg, "Copywriting");
        staticWait(2000);
    }

    public void howItWorks(){

        clickElementVisible(By.xpath("//a[@id='first_how_it_works_a']")," Click the how it works tab",10);
        String msg = getText(By.xpath("//h3[contains(text(),'How it works')]"), "Verifiy How it works", 3);
        Assert.assertEquals(msg, "How it works");
        staticWait(2000);

    }

    public void pricing(){

        clickElementVisible(By.xpath("//a[@id='first_pricing_a']"),"Click the pricing tab",10);
        String msg = getText(By.xpath("//h3[contains(text(),'Pricing')]"), "Pricing", 3);
        Assert.assertEquals(msg, "Pricing");
        staticWait(2000);

    }

    public void resources(){
        clickElementVisible(By.xpath("//a[@id='first_resources_a']"),"Click the resources tab",10);
        String msg = getText(By.xpath("//h1[@class='coming-soon']"), "Verify resources", 3);
        Assert.assertEquals(msg, "Coming Soon");
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Close button of resources",10);
        staticWait(2000);
    }


    public void whoWeAre(){

        clickElementVisible(By.xpath("//a[@id='first_who_we_are_a']"),"Click the Who we are  tab",10);
        String msg = getText(By.xpath("//h3[contains(text(),'Who we are')]"), "Verify Who we are", 3);
        Assert.assertEquals(msg, "Who we are");
        staticWait(2000);
    }

    public void blogTab(){

        clickElementVisible(By.xpath("//a[@id='first_blog_a']"),"Click the blog tab",10);
        String msg = getText(By.xpath("//h1[@class='coming-soon']"), "Verify blog page", 3);
        Assert.assertEquals(msg, "Coming Soon");
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Close button of blogpage",10);
        staticWait(2000);
    }
        // Still i need to verify lot of things here mainly i need to cerify colore.
    public void contactTab(Map<String, String> data){

        clickElementVisible(By.xpath("//a[@id='first_second_contact_us_a']"),"Click the Contact us tab",10);
        String msg = getText(By.xpath("//h1[contains(text(),'Contact Us')]"), "Verify Contact Us page", 3);
        Assert.assertEquals(msg, "Contact Us");
        staticWait(2000);
        enterElementVisible(By.xpath("//input[@id='full_name'][@name='full_name']/."), data.get("Fullnam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='email_address'][@name='email_address']/."), data.get("Emailcon"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='contact_number'][@name='contact_number']/."), data.get("Connum"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='company'][@name='company']/."), data.get("CmpnyNam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.id("comments"), data.get("Cmd"), "Enter the full name to contact", 10);
        staticWait(2000);

        // here i left to write to i am not a robart
        clickElementVisible(By.xpath("//input[@id='contact_form_submitt_button'][@name='submit_quote']/."),"Click send button",10);
        String msg1 = getText(By.xpath("//div[@class='message-box']"), "Verify Contact Send ", 3);
        Assert.assertEquals(msg1, "We will get in touch with you as soon as possible. Please ring us on +919886192920 if urgent");

    }

    public void proventested(By by){

        // we use this code to move the mouse cursor on image.

       /* moveToElements(by.xpath(""),"Mouse over on myntra");
        staticWait(3000);
        moveToElements(By.xpath("//a[@id='first_zoomcar']"),"Mouse over on zoomcar");
        staticWait(3000);
        moveToElements(By.xpath("//a[@id='first_caratlane']"),"Mouse over on caratlane");
        staticWait(3000);
        moveToElements(By.xpath("//a[@id='first_cleartrip']"),"Mouse over on cleartrip");
        staticWait(5000);*/

       /* Actions move=new Actions (driver);
        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_myntra']"))).build().perform();  //Myntra
        driver.findElement(By.xpath("//a[@id='first_myntra']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_zoomcar']"))).build().perform();  //Zoom
        driver.findElement(By.xpath("//a[@id='first_zoomcar']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_caratlane']"))).build().perform();  //Caratlane
        driver.findElement(By.xpath("//a[@id='first_caratlane']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_cleartrip']"))).build().perform();  //Cleartrip
        driver.findElement(By.xpath("//a[@id='first_cleartrip']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_firstcry']"))).build().perform();  //Firstcry
        driver.findElement(By.xpath("//a[@id='first_firstcry']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_housing']"))).build().perform();  //Housing
        driver.findElement(By.xpath("//a[@id='first_housing']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_urbanladder']"))).build().perform(); //Urben ledder
        driver.findElement(By.xpath("//a[@id='first_urbanladder']")).isEnabled();
        staticWait(2000);

        move.moveToElement(driver.findElement(By.xpath("//a[@id='first_amazon']"))).build().perform();  //Amazon
        driver.findElement(By.xpath("//a[@id='first_amazon']")).isEnabled();
        staticWait(2000);*/
    }

    public void contactUs(Map<String, String> data){

        clickElementVisible(By.xpath("//a[@id='first_contact_us_a']"),"Click first Image contact us",10);
        String msg = getText(By.xpath("//h1[contains(text(),'Contact Us')]"), "Verify Contact Us page", 3);
        Assert.assertEquals(msg, "Contact Us");
        staticWait(2000);
        enterElementVisible(By.xpath("//input[@id='full_name'][@name='full_name']/."), data.get("Fullnam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='email_address'][@name='email_address']/."), data.get("Emailcon"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='contact_number'][@name='contact_number']/."), data.get("Connum"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='company'][@name='company']/."), data.get("CmpnyNam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.id("comments"), data.get("Cmd"), "Enter the full name to contact", 10);
        staticWait(2000);

        clickElementVisible(By.xpath("//div[@id='contactus-content']//a[@class='cls-btn']"),"Click contact us close button",10);

    }

    public void getquote(Map<String, String> data){

        clickElementVisible(By.xpath("//a[@id='first_get_quote_a']"),"Click first Image contact us",10);
        String msg = getText(By.xpath("//h1[contains(text(),'Contact Us')]"), "Verify Contact Us page", 3);
        Assert.assertEquals(msg, "Contact Us");
        staticWait(2000);
        enterElementVisible(By.xpath("//input[@id='full_name'][@name='full_name']/."), data.get("Fullnam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='email_address'][@name='email_address']/."), data.get("Emailcon"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='contact_number'][@name='contact_number']/."), data.get("Connum"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='company'][@name='company']/."), data.get("CmpnyNam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.id("comments"), data.get("Cmd"), "Enter the full name to contact", 10);
        staticWait(2000);

        clickElementVisible(By.xpath("//div[@id='contactus-content']//a[@class='cls-btn']"),"Click contact us close button",10);
        staticWait(2000);
    }

    public void translation(){

        clickElementVisible(By.xpath("//a[@id='first_translation_a']"),"Click translation Image",10);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Click close button of translation",10);
        staticWait(2000);
    }

    public void copywrite(){

        clickElementVisible(By.xpath("//a[@id='first_copywrite_a']"),"Click copywriter Image",10);
        staticWait(2000);
        String msg = getText(By.xpath("//div[@class='tt']//h3[contains(text(),'Copywriting')]"), "Verify copywriter page", 3);
        Assert.assertEquals(msg, "Copywriting");
        staticWait(2000);
    }

    public void editor(){

        clickElementVisible(By.xpath("//a[@id='first_editing_a']"),"Click Editor Image",10);
        staticWait(2000);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Click close button of editor",10);
        staticWait(2000);

    }

    public void whatWeDocontactUs(Map<String, String> data){

        clickElementVisible(By.xpath("//a[@id='first_whatwe_do_a']"),"Click first Image contact us",10);
        String msg = getText(By.xpath("//div[@class='tt']//h3[contains(text(),'Copywriting')]"), "Verify whatWeDo page", 3);
        Assert.assertEquals(msg, "Copywriting");
        staticWait(2000);
        clickElementVisible(By.xpath("//p[@id='what_we_do_first_contact_us']"),"Click whatWeDo contact us",10);
        enterElementVisible(By.xpath("//input[@id='full_name'][@name='full_name']/."), data.get("Fullnam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='email_address'][@name='email_address']/."), data.get("Emailcon"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='contact_number'][@name='contact_number']/."), data.get("Connum"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.xpath("//input[@id='company'][@name='company']/."), data.get("CmpnyNam"), "Enter the full name to contact", 10);
        staticWait(2000);

        enterElementVisible(By.id("comments"), data.get("Cmd"), "Enter the full name to contact", 10);
        staticWait(2000);

        clickElementVisible(By.xpath("//div[@id='contactus-content']//a[@class='cls-btn']"),"Click contact us close button",10);
        staticWait(2000);

    }

    public void fotterdataclick() {

        clickElementVisible(By.xpath("//a[contains(text(),'Translation')]"),"Click fotter clink",10);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Click close button",10);
        staticWait(2000);
        clickElementVisible(By.xpath("//a[contains(text(),'Copywriting')]"),"Click fotter clink",10);
        String msg = getText(By.xpath("//div[@class='tt']//h3[contains(text(),'Copywriting')]"), "Verify whatWeDo page", 3);
        Assert.assertEquals(msg, "Copywriting");
        staticWait(2000);
        scrollDown();
        clickElementVisible(By.xpath("//a[contains(text(),'Content writing')]"),"Click fotter clink",10);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"Click close button",10);
        staticWait(2000);
        clickElementVisible(By.xpath("//a[contains(text(),'Editing')]"),"Click fotter Editor",10);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"close editor tab",10);
        staticWait(2000);
        clickElementVisible(By.xpath("//a[contains(text(),'Design')]"),"Click designe button",10);
        clickElementVisible(By.xpath("//div[@id='comingsoon-content']//a[@class='cls-btn']"),"close editor tab",10);
        staticWait(2000);
        clickElementVisible(By.xpath("//a[contains(text(),'Terms of Use')]"),"Click terms and condition button",10);
        staticWait(5000);
        /*clickElementVisible(By.xpath("//a[contains(text(),'Privacy Policy')]"),"Click Privacy Policy button",10);
        staticWait(5000);
        clickElementVisible(By.xpath("//img[@src='https://textmercato.com/newimages/in.png']"),"Click linked in icone",10);
        staticWait(2000);
        switchToIframe("//img[@src='https://textmercato.com/newimages/logo_2.png']");*/

    }






    public void Frontendtest(Map<String,String> data){

        navigateToUrl(data);
        whatWeDo();
        howItWorks();
        pricing();
        resources();
        whoWeAre();
        blogTab();
        contactTab(data);
        //proventested();
        contactUs(data);
        getquote(data);
        translation();
        copywrite();
        editor();
        whatWeDocontactUs(data);
        fotterdataclick();


    }

}
