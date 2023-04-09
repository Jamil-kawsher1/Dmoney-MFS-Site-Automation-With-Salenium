package TestRunner;

import Pages.DashboardPage;
import Pages.SignInPage;
import Setup.Setup;
import Utils.Utils;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardPageTestRunner extends Setup {
    @BeforeTest
    public void doLogin () {
        SignInPage sign = new SignInPage(driver);
        String email = "salman@roadtocareer.net";
        String password = "1234";
        sign.doLogin(email, password);
    }

    public DashboardPage dashboardPage;

    //    String name,String email,String password,String phone,String nidNumber,String role
    @Test(priority = 1, description = "create New Agent")
    public void createNewAgent () throws InterruptedException, IOException, ParseException {
        dashboardPage = new DashboardPage(driver);
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.regexify("\\016[0-9]{8}");
        String idNumber = faker.regexify("[0-9]{9}");
        ;
        Thread.sleep(3000);
        dashboardPage.doCreateNewUserWithDifferentRole(name, email, "user1234", phoneNumber, idNumber, "Agent");
        Thread.sleep(3000);
        String msg = dashboardPage.successMsg.getText();
//        System.out.println(msg);
        Thread.sleep(3000);
        Assert.assertEquals(msg, "User created");
        String filename = "./src/test/resources/AgentList.json";
        Utils.addIntoJsonList(filename, name, email, phoneNumber, "Agent");
        Thread.sleep(1000);
        dashboardPage.okButtonPassOrFail.click();
    }

    @Test(priority = 2, description = "create New Customer", enabled = true)
    public void createNewCustomer () throws InterruptedException, IOException, ParseException {
        driver.navigate().refresh();
        Thread.sleep(3000);
        dashboardPage = new DashboardPage(driver);
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.regexify("\\016[0-9]{8}");
        String idNumber = faker.regexify("[0-9]{9}");
        ;
        Thread.sleep(3000);
        dashboardPage.doCreateNewUserWithDifferentRole(name, email, "user1234", phoneNumber, idNumber, "Customer");
        Thread.sleep(3000);
        String msg = dashboardPage.successMsg.getText();
//        System.out.println(msg);
        Thread.sleep(3000);
        Assert.assertEquals(msg, "User created");
        String filename = "./src/test/resources/CustomerList.json";
        Utils.addIntoJsonList(filename, name, email, phoneNumber, "Customer");
        Thread.sleep(1000);
        dashboardPage.okButtonPassOrFail.click();
    }

    @Test(priority = 3, description = "Search Newly Created Customer")
    public void searchUser () throws InterruptedException, IOException, ParseException {
        dashboardPage = new DashboardPage(driver);
        String filename = "./src/test/resources/CustomerList.json";
        String phone = (String) Utils.readJSONFile(filename, 1).get("phone");
        dashboardPage.doSearch(phone,0);


    }
@Test(priority = 4,description = "Update User password")
    public void updatePassword() throws InterruptedException {
        dashboardPage=new DashboardPage(driver);
        String password="updatedPass123";
        dashboardPage.updatePassword(password);
    String msg = dashboardPage.successMsg.getText();
//        System.out.println(msg);
    Thread.sleep(3000);
    Assert.assertEquals(msg, "User Update Successfully!");
    dashboardPage.okButtonPassOrFail.click();
    }

}
