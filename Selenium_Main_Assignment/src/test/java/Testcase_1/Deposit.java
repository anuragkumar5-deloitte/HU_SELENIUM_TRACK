package Testcase_1;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pagetest_1.*;

import java.util.concurrent.TimeUnit;
public class Deposit {
        WebDriver driver;
        ExtentHtmlReporter reporter;
        ExtentReports extend;
        ExtentTest test1,test2,test3,test4;

        // method to validate setup
        @BeforeClass
        public void Validatesetup()
        {
            reporter=new ExtentHtmlReporter("C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Selenium_assignment\\Selenium_Main_Assignment\\src\\data\\AddCustomer.xlsx"); // creating reporter object
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
            login.login("//button[@class='btn btn-primary btn-lg']");  // logging in as customer
            test1.pass("Logged In To Customer Login");

            // taking screenshot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("DepositTest case 1 Validate Customer Login");
        }

        // method to validate user login
        @Test(priority = 2)
        public void ValidateUserLogin()
        {
            test2=extend.createTest("Validate Login","Validating User Login");
            test2.log(Status.INFO,"Test Case Starting");
            HomePage home=new HomePage(driver);
            home.homePageLogin("//select[@id='userSelect']","//button[@type='submit']"); // logging in as user
            test2.pass("Logged In To User Login");

            // take screenshot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("DepositTest case 2 Validate User Login");
        }

        // method to validate deposit
        @Test(priority = 3)
        public void ValidateDeposit()throws Exception
        {
            test3=extend.createTest("Validate Deposit","Validating Deposit");
            test3.log(Status.INFO,"Test Case Starting");
            Read read=new Read();                                    // read data from excel
            int dep_amount=Integer.parseInt(read.NewCustomerDetails(5));
            WelcomePage welcome=new WelcomePage(driver);
            welcome.OptionSelection("//button[@ng-class='btnClass2']");  // selecting deposit option
            test3.pass("Clicked Deposit Option");
            welcome.Deposit("//input[@type='number']","//button[@type='submit']",dep_amount); // depositing amount by calling method
            test3.pass("Deposit Completed");

            // take screen shot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("DepositTest case 2 Validate Deposit");
        }

        // method to validate quit
        @AfterClass
        public void ValidateQuit()
        {
            test4=extend.createTest("Validate Quit","Validating Quitting");
            test4.log(Status.INFO,"Test Case Starting");
            driver.quit();               // quit browser
            test4.pass("Closed Browser");
            extend.flush();
        }
    }
