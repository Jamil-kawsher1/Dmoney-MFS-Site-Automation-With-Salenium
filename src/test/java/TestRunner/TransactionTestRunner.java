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


    public void doLoginAsSystemAccount () {
        signInPage = new SignInPage(driver);
        signInPage.doLogin("system@roadtocareer.net", "1234");
    }

    @Test(priority = 1, description = "Deposit 2000 tk to the agent you just created and assert deposit successful ")
    public void depositSystemToAgent () throws IOException, ParseException, InterruptedException {
        doLoginAsSystemAccount();
        dashboardPage = new DashboardPage(driver);
        String filename = "./src/test/resources/CustomerList.json";
        String amount = "2000";
        JSONObject userDetails = Utils.readJSONFile(filename, 1);
        dashboardPage.depositToAccounts((String) userDetails.get("phone"), amount);

        Thread.sleep(3000);
        String msg = dashboardPage.successFailStatusTitle.getText();
        Assert.assertEquals(msg, "Deposit successful");
        dashboardPage.okButtonPassOrFail.click();
        dashboardPage.doLogout();
    }


    public void doLoginWithCustomerAccount () throws IOException, ParseException, InterruptedException {
        String fileName = "./src/test/resources/AgentList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        Thread.sleep(3000);
        signInPage.doLogin((String) userInfo.get("email"), "user1234");


    }
@Test(priority = 2,description = " Login to the agent account and deposit to the customer account ",enabled = true)
    public void depositToCustomerAccFromAgentAcc () throws IOException, ParseException, InterruptedException {
        doLoginWithCustomerAccount();
        dashboardPage = new DashboardPage(driver);
        String fileName = "./src/test/resources/CustomerList.json";
        JSONObject userInfo = Utils.readJSONFile(fileName, 1);
        Thread.sleep(3000);
        dashboardPage.depositToAccounts((String) userInfo.get("phone"),"1000");


    }


}
