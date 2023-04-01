package Setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
public WebDriver driver;
@BeforeTest
    public void setup(){
    //fixing the issue with the latest Chrome version
    ChromeOptions chromeOptions=new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://dmoney-web.vercel.app/");
    }

@AfterTest
    public void close() throws InterruptedException {
    Thread.sleep(5000);
    driver.close();
}
}
