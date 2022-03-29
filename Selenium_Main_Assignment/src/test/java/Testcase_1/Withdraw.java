package Testcase_1;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import Pagetest_1.*;

import java.util.concurrent.TimeUnit;

public class Withdraw {
        WebDriver driver;
        ExtentHtmlReporter reporter;
        ExtentReports extend;
        ExtentTest test1,test2,test3,test4;
        //int wdl_amt=300;

        // method to validate quit
        @BeforeClass
        public void Validatesetup()
        {
            reporter=new ExtentHtmlReporter("C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Selenium_assignment\\Selenium_Main_Assignment\\src\\data\\Withdraw.html"); // creating reporter object
            extend=new ExtentReports();         // creating extent reports
            extend.attachReporter(reporter);    // attach reporter
            System.setProperty("webdriver.chrome.driver","C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe"); // setting driver location
            driver=new ChromeDriver();          // creating driver object
            driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");  // launch using link
            driver.manage().window().maximize(); // maximize window
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  // wait until window launch
        }

        // method to validate customer login
        @Test(priority = 1)
        public void ValidateCustomerLogin()
        {
            test1=extend.createTest("Validate Login","Validating Customer Login");
            test1.log(Status.INFO,"Test Case Starting");
            Login login=new Login(driver);
            login.login("//button[@class='btn btn-primary btn-lg']");
            test1.pass("Logged In As Customer");

            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("Withdraw case 1 Validate Customer Login");
        }

        // method to validate user login
        @Test(priority = 2)
        public void ValidateUserLogin()
        {
            test2=extend.createTest("Validate Login","Validating User Login");
            test2.log(Status.INFO,"Test Case Starting");
            HomePage home=new HomePage(driver);
            home.homePageLogin("//select[@id='userSelect']","//button[@type='submit']");
            test2.pass("Logged In As User");

            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("Withdraw case 2 Validate User Login");
        }

        // method to validate withdraw
        @Test(priority = 3)
        public void ValidateWithdraw() throws Exception
        {
            test3=extend.createTest("Validate Withdraw","Validating Withdraw");
            test3.log(Status.INFO,"Test Case Starting");
            Read read = new Read();
            int wdl_amt=Integer.parseInt(read.NewCustomerDetails(4));
            WelcomePage welcome=new WelcomePage(driver);
            welcome.OptionSelection("//button[@ng-class='btnClass3']");
            test3.pass("Selected Withdraw Option");
            try {
                welcome.Withdrawl("//input[@type='number']","//button[@type='submit']",wdl_amt);
                test3.pass("Withdraw Successful");

                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("Withdraw case 3 Validate Withdraw");
            }catch (AssertionError ae)
            {
                test3.fail("Withdraw Unsuccessful Due To Insufficient Balance");

                ScreenShot shot=new ScreenShot(driver);
                shot.FailShot("Withdraw case 3 Validate Withdraw");
            }
        }

        // method to validate quit
        @AfterClass
        public void ValidateQuit()
        {
            test4=extend.createTest("Validate Quit","Validating Quitting");
            test4.log(Status.INFO,"Test Case Starting");
            driver.quit();
            test4.pass("Browser Closed");
            extend.flush();
        }
    }

