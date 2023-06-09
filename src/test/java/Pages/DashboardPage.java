package Pages;

import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;

public class DashboardPage {


    @FindBy(tagName = "li")
    public List<WebElement> dashboardMenuList;

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
    // Success/Fail status Main title
    @FindBy(className = "swal2-title")
    public WebElement successFailStatusTitle;

    //deposit from account to Account
    //transaction confirm Button
    @FindBy(className = "swal2-confirm")
    WebElement confirmButton;
    @FindBy(className = "swal2-cancel")
    WebElement cancelButton;

    //deposit option filed targeting
    @FindBy(css = "input[placeholder='To Account']")
    WebElement toAccount;
    @FindBy(css = "input[placeholder='Enter Number of Amount']")
    WebElement depositAmount;

    //users control option button like SignOut profile
    @FindBy(className = "notifications")
    WebElement profileControl;
    //dropdown option list profile/logout
    @FindBy(className = "dropdown-item")

    List<WebElement> dropdownList;
    //transaction table Data
    @FindBy(css = "td")
    public List<WebElement> tableData;

    //sendMoney/payment option filed targeting

    @FindBy(css = "input[placeholder='Enter Your Account Number']")
    WebElement toAccountSendMoney;
    @FindBy(css = "input[placeholder='Enter Number of Amount']")
    WebElement depositAmountSend;
    //balance field
    @FindBy(tagName = "h2")
    public WebElement balanceField;
    //success fail big dialog box
    @FindBy(id = "swal2-html-container")
    public WebElement subHaddingBalanceanFeeTrasactionId;

    public DashboardPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doCreateNewUserWithDifferentRole (String name, String email, String password, String phone, String nidNumber, String role) throws InterruptedException {
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
        if (role.equals("Customer")) {
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        } else if (role.equals("Agent")) {
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        } else if (role.equals("Admin")) {
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            dropDownClass.sendKeys(Keys.ENTER);
        } else if (role.equals("Merchant")) {
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

    public void doSearch (String phone,int index) throws InterruptedException {
        dashboardMenuList.get(index).click();
        Thread.sleep(1000);
        search.sendKeys(phone);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }

    public void updatePassword (String password) throws InterruptedException {
        editPencil.click();
        Thread.sleep(3000);
        createUserFieldList.get(2).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        Thread.sleep(1000);
        createUserFieldList.get(2).sendKeys(password);
        button.click();
    }


    //Dashboard actions and options for System account
    public void depositToAccounts (String toAccount, String amount) throws InterruptedException {
        //deposit action Link(index 2)
        dashboardMenuList.get(2).click();
        this.toAccount.sendKeys(toAccount);
        depositAmount.sendKeys(amount);
        button.click();
        Thread.sleep(2000);
        confirmButton.click();
    }

    public void doLogout () {
        profileControl.click();
        //logout option from List
        dropdownList.get(1).click();
    }


    public void doSendMoney (String toAccount, String amount) throws InterruptedException {
        dashboardMenuList.get(1).click();
        Thread.sleep(3000);
        toAccountSendMoney.sendKeys(toAccount);
        depositAmount.sendKeys(amount);
        button.click();
        Thread.sleep(2000);
        confirmButton.click();
        Thread.sleep(2000);

        okButtonPassOrFail.click();
        Thread.sleep(2000);
        dashboardMenuList.get(0).click();
        Thread.sleep(2000);

    }

    public void doWithdrawal (String agentNumber, String amount) throws InterruptedException {
        Thread.sleep(1000);
        dashboardMenuList.get(2).click();
        Thread.sleep(2000);
        toAccountSendMoney.sendKeys(agentNumber);
        depositAmountSend.sendKeys(amount);
        button.click();
        Thread.sleep(2000);
        confirmButton.click();


    }
public void doPaymentToMerchant(String phoneNumber,String amount) throws InterruptedException {
        dashboardMenuList.get(3).click();
        Thread.sleep(2000);
        toAccountSendMoney.sendKeys(phoneNumber);
        depositAmountSend.sendKeys(amount);
        button.click();
    confirmButton.click();

}

}


