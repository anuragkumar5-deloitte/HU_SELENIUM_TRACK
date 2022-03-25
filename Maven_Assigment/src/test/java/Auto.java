import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class Auto {
    public static WebDriver driver;
    @BeforeMethod
    public void initialSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}