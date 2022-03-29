package Testcase_1;

import Pagetest_1.*;
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

public class Transaction_test {
        WebDriver driver;
        ExtentHtmlReporter reporter;
        ExtentReports extend;
        ExtentTest test1,test2,test3,test4,test5,test6,test7;
        int credit_amt,debit_amt;

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
            login.login("//button[@class='btn btn-primary btn-lg']");  // login as customer
            test1.pass("Logged In As Customer");

            // take screen shot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("TransactionTest case 1 Validate Customer Login");
        }

        // method to validate user login
        @Test(priority = 2)
        public void ValidateUserLogin() throws Exception
        {
            test2=extend.createTest("Validate Login","Validating User Login");
            test2.log(Status.INFO,"Test Case Starting");
            HomePage home=new HomePage(driver);
            home.homePageLogin("//select[@id='userSelect']","//button[@type='submit']"); // login as user
            test2.pass("Logged In As User");

            // take screen shot on sucess
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("TransactionTest case 2 Validate User Login");
        }

        // method to validate deposit
        @Test(priority = 3)
        public void ValidateDeposit()throws Exception
        {
            test3=extend.createTest("Validate Deposit","Validating Deposit");
            test3.log(Status.INFO,"Test Case Starting");
            Read read = new Read();                          // create object to Read from excel
            credit_amt=Integer.parseInt(read.NewCustomerDetails(5));
            WelcomePage welcome=new WelcomePage(driver);
            welcome.OptionSelection("//button[@ng-class='btnClass2']");
            test3.pass("Selected Deposit Option");
            try {
                welcome.Deposit("//input[@type='number']","//button[@type='submit']",credit_amt);
                test3.pass("Deposit Successful");

                // take screen shot on success
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("TransactionTest case 3 Validate Deposit");
            }catch (AssertionError ae)
            {
                test3.fail("Deposit Unsuccessful");

                // take screen shot on failure
                ScreenShot shot=new ScreenShot(driver);
                shot.FailShot("TransactionTest case 3 Validate Deposit");
            }
        }

        // method to validate withdraw
        @Test(priority = 4)
        public void ValidateWithdraw() throws Exception
        {
            test4=extend.createTest("Validate Withdraw","Validating Withdraw");
            test4.log(Status.INFO,"Test Case Starting");
            Read read = new Read();
            debit_amt=Integer.parseInt(read.NewCustomerDetails(4));
            WelcomePage welcome=new WelcomePage(driver);
            welcome.OptionSelection("//button[@ng-class='btnClass3']");
            test4.pass("Selected Option For Withdrawl");
            try {
                welcome.Withdrawl("//input[@type='number']","//button[@type='submit']",debit_amt);
                test4.pass("Withdraw Successful");

                // take screen shot on sucess
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("TransactionTest case 3 Validate Withdraw");
            }catch (AssertionError ae)
            {
                test4.pass("Withdraw Unsuccessful Because Insufficient Balance");

                // take screen shot on failure
                ScreenShot shot=new ScreenShot(driver);
                shot.FailShot("TransactionTest case 3 Validate Withdraw");
            }
        }

        // method to validate transaction option
        @Test(priority = 5)
        public void ValidateTransactionOption() throws Exception
        {
            test5=extend.createTest("Validate Option","Validating Transaction Option");
            test5.log(Status.INFO,"Test Case Starting");
            WelcomePage wecome=new WelcomePage(driver);
            wecome.OptionSelection("//button[@ng-class='btnClass1']");
            test5.pass("Selected Transaction Option");

            // take screen shot on success
            ScreenShot shot=new ScreenShot(driver);
            shot.SuccessShot("TransactionTest case 5 Validate Transaction Option");
        }

        // method to validate transaction
        @Test(priority = 6)
        public void ValidateTransaction()throws Exception
        {
            test6=extend.createTest("Validate Transaction","Validating Transaction");
            test6.log(Status.INFO,"Test Case Starting");
            Transaction trans=new Transaction(driver);
            String result=trans.Transaction(credit_amt,debit_amt);
            try {
                Assert.assertEquals(result,"transaction sucess","Transaction Sucess");
                test6.pass("Transaction Updated Successfully");

                // take screen shot on success
                ScreenShot shot=new ScreenShot(driver);
                shot.SuccessShot("TransactionTest case 6 Validate Transaction");
            }catch (AssertionError ae)
            {
                test6.fail("Transaction Not Updated");

                // take screen shot on failure
                ScreenShot shot=new ScreenShot(driver);
                shot.FailShot("TransactionTest case 6 Validate Transaction");
            }
        }

        // method to validate quit
        @AfterClass
        public void ValidateQuit()
        {
            test7=extend.createTest("Validate Quit","Validating Quitting");
            test7.log(Status.INFO,"Test Case Starting");
            driver.quit();
            test7.pass("Browser Closed");
            extend.flush();
        }
    }

