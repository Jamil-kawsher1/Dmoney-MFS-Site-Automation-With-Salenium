package Pages;

import Setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DashboardPage  {



    @FindBy(tagName = "li")
    List<WebElement> dashboardMenuList;
    //Input Field of Create user
    @FindBy(tagName = "input")
    List<WebElement> createUserFieldList;

    //targeting dropdown class
    @FindBy(className = "form-select")
   public WebElement dropDownClass;

//    Select dropDownOption=new Select(dropDownClass);
    @FindBy(css = "[type=submit]")
    public WebElement button;
    //success text
    @FindBy(id = "swal2-html-container")
   public WebElement successMsg;

    // success/fail ok button
    @FindBy(className = "swal2-confirm")
   public WebElement okButtonPassOrFail;
    //search button input
    @FindBy(tagName = "input")
    WebElement search;

    @FindBy(className = "fa-pencil")
    WebElement editPencil;

    public DashboardPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void doCreateNewUserWithDifferentRole (String name,String email,String password,String phone,String nidNumber,String role) throws InterruptedException {
        Thread.sleep(5000);
        dashboardMenuList.get(1).click();
        //name Field
        createUserFieldList.get(0).sendKeys(name);
        //email
        createUserFieldList.get(1).sendKeys(email);
        //password
        createUserFieldList.get(2).sendKeys(password);
        //phone
        createUserFieldList.get(3).sendKeys(phone);
        //nid
        createUserFieldList.get(4).sendKeys(nidNumber);
        dropDownClass.click();
        if (role.equals("Customer")){
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        }
        else if(role.equals("Agent")){
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        }
        else if(role.equals("Admin")){
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        }
        else if(role.equals("Merchant")){
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        }



//        Thread.sleep(5000);
//        dropDownOption.selectByValue(role);
        button.click();
    }

    public void doSearch(String phone) throws InterruptedException {
        dashboardMenuList.get(0).click();
        search.sendKeys(phone);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }

    public void updatePassword(String password) throws InterruptedException {
        editPencil.click();
        Thread.sleep(3000);
        createUserFieldList.get(2).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        Thread.sleep(1000);
        createUserFieldList.get(2).sendKeys(password);
        button.click();
    }
}


