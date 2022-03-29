package Testcase_1;

import Pagetest_1.Read;
import Pagetest_1.Login;
import Pagetest_1.ManagePage;
import Pagetest_1.ScreenShot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddCustomer {
        WebDriver driver;
        ExtentHtmlReporter reporter;
        ExtentReports extend;
        ExtentTest test1,test2,test3;

        // method to validate setup
        @BeforeClass
        public void ValidateSetup()
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
            test1.log(Status.INFO,"Test Case Starting");
            Login login=new Login(driver);
            login.login("//button[text()='Bank Manager Login']");  // login as manager
            test1.pass("Logged In To Manager Login By Manager");

            // taking screenshot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("AddCustomerTest case 1 Validate Manager Login");
        }

        // method to validate add customer
        @Test(priority = 2)
        public void ValidateAddCustomer()
        {
            test2=extend.createTest("Validate Add Customer","Validating Adding Customer");
            test2.log(Status.INFO,"Test Case Starting");
            Read read=new Read();                     // read data from excel
            String first_name=read.NewCustomerDetails(0);
            String last_name=read.NewCustomerDetails(1);
            String pin=read.NewCustomerDetails(2);
            test2.pass("Getting Value From Excel");
            ManagePage manage=new ManagePage(driver);
            manage.ManageOptionSelection("//button[@ng-class='btnClass1']");  // select add customer option
            test2.pass("Selected Add Customer Option");
            String add_status=manage.InputCustomerValuesToAdd(first_name,last_name,pin);  // calling method for type input values
            try {
                Assert.assertNotEquals(add_status,"Please check the details. Customer may be duplicate.","Account added sucessfully");
                test2.pass("Account Added Successfully");

                // taking screenshot on success
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("AddCustomerTest case 2 Validate Add Customer");
            }catch (AssertionError ae)
            {
                test2.fail("Account Added UnSuccessful Because Details May Be Duplicated");

                // taking screenshot on failure
                ScreenShot shot=new ScreenShot(driver);
                shot.FailShot("AddCustomerTest case 2 Validate Add Customer");
            }
        }

        // method to validate quit
        @AfterClass
        public void ValidateQuit()
        {
            test3=extend.createTest("Validate Quit","Validating Quiting");
            test3.log(Status.INFO,"Test Case Starting");
            driver.close();   // quit browser
            test3.pass("Browser Closed");
            extend.flush();
        }
    }

