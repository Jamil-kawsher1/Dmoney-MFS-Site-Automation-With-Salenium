package TestRunner;

import Pages.DashboardPage;
import Pages.SignInPage;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TransactionTestRunner extends Setup {
    public DashboardPage dashboardPage;
@BeforeTest
public void doLoginAsSystemAccount(){
    SignInPage signInPage=new SignInPage(driver);
    signInPage.doLogin("system@roadtocareer.net","1234");
}
@Test(priority = 1,description = "Deposit 2000 tk to the agent you just created and assert deposit successful ")
  public void depositSystemToAgent() throws IOException, ParseException, InterruptedException {
dashboardPage=new DashboardPage(driver);
    String filename = "./src/test/resources/CustomerList.json";
    String amount="2000";
   JSONObject userDetails= Utils.readJSONFile(filename,2);
dashboardPage.depositFromSystemAccount((String) userDetails.get("phone"),amount);

    Thread.sleep(3000);
    String msg = dashboardPage.successFailStatusTitle.getText();
    Assert.assertEquals(msg,"Deposit successful");
    dashboardPage.okButtonPassOrFail.click();
  }

}
