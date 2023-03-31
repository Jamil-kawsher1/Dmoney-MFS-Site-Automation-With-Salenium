package TestRunner;

import Pages.SignInPage;
import Setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingUpTestRunner extends Setup {
    public SignInPage signInPage;

//    public SingUpTestRunner () {
//        SignInPage signInPage = new SignInPage(driver);
//    }
@Test(priority = 1,description = "Login As Admin with Valid credentials")
    public void doLoginAsAdmin () {
       signInPage=new SignInPage(driver);
        String email = "salman@roadtocareer.net";
        String password = "1234";
       signInPage.doLogin(email,password);
    String tittle=driver.findElement(By.tagName("h2")).getText();

    Assert.assertTrue(tittle.contains("Balance:"));

    }


}
