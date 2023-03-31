package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    //login Page fields

    @FindBy(id = "form3Example3")
  public  WebElement emailAddress;

    @FindBy(id = "form3Example4")
   public WebElement password;

    @FindBy(css = "[type=submit]")
   public WebElement button;

    //page Factory function to Load the element before selenium try to access the Web elements

    public SignInPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public void doLogin(String email,String password){
        emailAddress.sendKeys(email);
        this.password.sendKeys(password);

        button.click();
    }
}
