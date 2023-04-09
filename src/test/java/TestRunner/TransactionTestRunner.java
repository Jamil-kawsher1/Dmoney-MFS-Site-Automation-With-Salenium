package TestRunner;

import Pages.DashboardPage;
import Pages.SignInPage;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TransactionTestRunner extends Setup {
    public DashboardPage dashboardPage;
    public SignInPage signInPage;


    public void doLoginWithDifferentRole (String email, String password) {
        signInPage = new SignInPage(driver);
        signInPage.doLogin(email, password);
    }

    @Test(priority = 1, description = "Deposit 2000 tk to the agent you just created and assert deposit successful ")
    public void depositSystemToAgent () throws IOException, ParseException, InterruptedException {
        doLoginWithDifferentRole("system@roadtocareer.net", "1234");

        dashboardPage = new DashboardPage(driver);
        String filename = "./src/test/resources/AgentList.json";
        String amount = "2000";
        JSONObject userDetails = Utils.readJSONFile(filename, 1);
        dashboardPage.depositToAccounts((String) userDetails.get("phone"), amount);

        Thread.sleep(3000);
        String msg = dashboardPage.successFailStatusTitle.getText();
        Assert.assertEquals(msg, "Deposit successful");
        dashboardPage.okButtonPassOrFail.click();
        dashboardPage.doLogout();
    }


    public void doLoginWithAgentAccount () throws IOException, ParseException, InterruptedException {
        String fileName = "./src/test/resources/AgentList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        Thread.sleep(3000);
        signInPage.doLogin((String) userInfo.get("email"), "user1234");


    }

    @Test(priority = 2, description = "Login to the agent account and deposit to the customer account ", enabled = true)
    public void depositToCustomerAccFromAgentAcc () throws IOException, ParseException, InterruptedException {
        doLoginWithAgentAccount();
        dashboardPage = new DashboardPage(driver);
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        Thread.sleep(3000);
        dashboardPage.depositToAccounts((String) userInfo.get("phone"), "1500");
        Thread.sleep(2000);
        String msg = dashboardPage.successFailStatusTitle.getText();
        Assert.assertEquals(msg, "Deposit successful");
        dashboardPage.okButtonPassOrFail.click();
        dashboardPage.doLogout();

    }

    public void loginWithCustomerInfo () throws IOException, ParseException {
        SignInPage signInPage = new SignInPage(driver);
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        signInPage.doLogin((String) userInfo.get("email"), "updatedPass123");

    }

    @Test(priority = 3, description = "Now login to the customer account and check if statement is showing ")
    public void checkCustomerStatement () throws IOException, ParseException, InterruptedException {
        loginWithCustomerInfo();
        DashboardPage dahsboardPage = new DashboardPage(driver);
        String phoneInfo = dahsboardPage.tableData.get(4).getText();
        Thread.sleep(3000);
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        String phoneNumber = (String) userInfo.get("phone");
        Assert.assertEquals(phoneInfo, phoneNumber);
    }

    @Test(priority = 4, description = "Now send 100 tk to another customer account and Check again the customer statement that 105 tk is debited. Assert with the expected total amount. ")
    public void doSendMoneyToOtheruser () throws IOException, ParseException, InterruptedException {
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 2);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doSendMoney((String) userInfo.get(("phone")), "100");


        int targetedIndexsize = dashboardPage.tableData.size() - 4;
        String message = dashboardPage.tableData.get(targetedIndexsize).getText();
        Assert.assertEquals(message, "105 TK");
        Thread.sleep(2000);
        String balance = dashboardPage.balanceField.getText();
        Assert.assertTrue(balance.contains("1,395 TK"));

    }

    @Test(priority = 5, description = "Withdraw tk 500 and assert the expected current balance after successful withdrawal ")
    public void moneyWithdrawal () throws IOException, ParseException, InterruptedException {
        String fileName = "./src/test/resources/AgentList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doWithdrawal((String) userInfo.get("phone"), "500");
        Thread.sleep(3000);
        String withdrwalInfo = dashboardPage.subHaddingBalanceanFeeTrasactionId.getText();
        Assert.assertTrue(withdrwalInfo.contains("890 TK"));
        String transactionId = withdrwalInfo.replaceAll(".*Trnx ID: (\\w+).*", "$1");
        Utils.addIntoJsonList("./src/test/resources/CustomerTransactionList.json", transactionId);
        dashboardPage.okButtonPassOrFail.click();
    }

    @Test(priority = 6, description = " Go to customer statement and assert trnx id is found in statement ")
    public void searchSpecificTransactionId () throws IOException, ParseException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.dashboardMenuList.get(0).click();
        Thread.sleep(3000);
        JSONObject transactionInfo = Utils.readJSONFile("./src/test/resources/CustomerTransactionList.json", 1);
        Thread.sleep(3000);
        int targetedIndexsize = dashboardPage.tableData.size() - 5;
        String message = dashboardPage.tableData.get(targetedIndexsize).getText();
        Assert.assertTrue(message.contains((String) transactionInfo.get("transactionId")));


    }

    @Test(priority = 7, description = "Payment to a merchant 100 tk and assert which current balance is expected")
    public void paymentToMerchant () throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doPaymentToMerchant("01686606905 ", "100");
        Thread.sleep(3000);
        String paymentInfo = dashboardPage.subHaddingBalanceanFeeTrasactionId.getText();
        Assert.assertTrue(paymentInfo.contains(" 785 TK"));

        dashboardPage.okButtonPassOrFail.click();
        Thread.sleep(1000);
        dashboardPage.doLogout();
    }

    @Test(priority = 8, description = " Logged in to the admin again, then in transaction menu Search Using Customer Mobile number to find payment in Transaction List")
    public void CheckingLastPaymentTransactionIdWithAdminLogin () throws IOException, ParseException, InterruptedException {
        doLoginWithDifferentRole("salman@roadtocareer.net", "1234");
        dashboardPage = new DashboardPage(driver);
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        Thread.sleep(1000);
        dashboardPage.doSearch((String) userInfo.get("phone"), 2);
        Thread.sleep(3000);
        int targetedIndexsize = dashboardPage.tableData.size() - 6;
        String message = dashboardPage.tableData.get(targetedIndexsize).getText();
        Assert.assertTrue(message.contains("Payment"));
    }

}

