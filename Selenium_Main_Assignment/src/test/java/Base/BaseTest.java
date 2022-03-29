package Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import PageTest.LoginPage;
import PageTest.NewCustomerPage;
import PageTest.WithdrawalPage;
import PageTest.NewAccountPage;
public class BaseTest {
        protected WebDriver driver;
        protected LoginPage login;
        protected NewCustomerPage ncustomer;
        protected NewAccountPage naccount;
        protected WithdrawalPage withdrawal;
        private String url = config.URL;
        private String browser = config.BROWSER;

        @BeforeClass
        public void SetUp(){
            getWebDriver();
            navigatetoPage();
            applyTimeOuts();
            createPageObjects();
        }

        @AfterMethod
        @AfterClass
        public void tearDown(){
            try{
                driver.quit();
            }catch(Exception ex){
                System.out.println("Exception: " + ex);
            }
        }

        private void createPageObjects() {
            login = new LoginPage(driver);
            ncustomer = new NewCustomerPage(driver);
            naccount = new NewAccountPage(driver);
            withdrawal = new WithdrawalPage(driver);
        }

        private void applyTimeOuts() {
            driver.manage().timeouts().pageLoadTimeout(config.PAGELOAD_WAIT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(config.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }

        private void navigatetoPage() {
            driver.navigate().to(url);
            driver.manage().window().maximize();
        }

        private void getWebDriver() {
            if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Selenium_assignment\\Selenium_Main_Assignment\\chromedriver.exe");
                driver = new ChromeDriver();
            }
        }

    }

