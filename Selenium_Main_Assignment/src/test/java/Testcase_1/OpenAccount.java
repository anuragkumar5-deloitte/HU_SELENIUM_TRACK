package Testcase_1;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import Pagetest_1.Read;
import Pagetest_1.Login;
import Pagetest_1.ManagePage;
import Pagetest_1.ScreenShot;

import java.util.concurrent.TimeUnit;

public class OpenAccount {
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

        // method to validate manager login
        @Test(priority = 1)
        public void ValidateManagerLogin()
        {
            test1=extend.createTest("Validate Login","Validating Manager Login");
            test1.log(Status.INFO,"Test case Starting");
            Login login=new Login(driver);
            login.login("//button[text()='Bank Manager Login']");      // login as manager
            test1.pass("Logged In To Manager Login");

            // take screen shot om success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("OpenAccountTest case 1 Validate Manager");
        }

        // method to validate add customer
        @Test(priority = 2)
        public void ValidateAddCustomer() throws Exception
        {
            test2=extend.createTest("Validate Add Customer","Validating Adding Customer");
            test2.log(Status.INFO,"Test Case Starting");
            Read read=new Read();                     // create object to read data from excel
            String first_name=read.NewCustomerDetails(0);
            String last_name=read.NewCustomerDetails(1);
            String pin=read.NewCustomerDetails(2);
            test2.pass("Reading Details From Excel");
            ManagePage manage=new ManagePage(driver);
            manage.ManageOptionSelection("//button[@ng-class='btnClass1']");   // click add customer
            test2.pass("Selected Add Customer Option");
            String add_status=manage.InputCustomerValuesToAdd(first_name,last_name,pin);
            try {
                Assert.assertNotEquals(add_status,"Please check the details. Customer may be duplicate.","Account added sucessfully");
                test2.pass("Account Added Successfully");

                // take screen shot on success
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("OpenAccountTest case 2 Validate Add Customer");
            }catch (AssertionError ae)
            {
                test2.fail("Account Added UnSuccessful Because Details May Be Duplicated");

                // take screen shot on failure
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("OpenAccountTest case 2 Validate Add Customer");
            }
        }

        // method to validate open account
        @Test(priority = 3)
        public void ValidateOpenAccount() throws Exception
        {
            test3=extend.createTest("Validate Open Account","Validating Account Opening");
            test3.log(Status.INFO,"Test Case Starting");
            Read read=new Read();                     // create object to read data from excel
            String currency=read.NewCustomerDetails(3);
            test3.pass("Reading From Excel");
            ManagePage manage=new ManagePage(driver);
            manage.ManageOptionSelection("//button[@ng-class='btnClass2']");  // click open account
            manage.OpenAccount();
            test3.pass("Account Opened Successfully");

            // take screenshot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("OpenAccountTest case 3 Validate Open Account");
        }

        // method to validate quit
        @AfterClass
        public void ValidateQuit()
        {
            test4=extend.createTest("Validate Quit","Validating Quitting");
            test4.log(Status.INFO,"Test Case Starting");
            driver.quit();                // quit browser
            test4.pass("Browser Closed");
            extend.flush();
        }
    }
