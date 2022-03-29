package Pagetest_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class WelcomePage {
        WebDriver driver;

        // constructor to initialize driver
        public WelcomePage(WebDriver driver)
        {
            this.driver=driver;
        }

        // method to select options in user login page
        public void OptionSelection(String option_x_path) throws Exception
        {
            Thread.sleep(1000);
            driver.findElement(By.xpath(option_x_path)).click();  // click option
        }

        // method to deposit
        public void Deposit(String x_path_amt,String x_path_submit,int amt) throws Exception
        {
            int previous=0,actual_amt=0;
            Thread.sleep(1000);
            previous=Integer.parseInt(driver.findElement(By.xpath("(//strong[@class='ng-binding'])[2]")).getText());  // get previous amount
            driver.findElement(By.xpath(x_path_amt)).sendKeys(String.valueOf(amt));                                   // enter amount to deposit
            Thread.sleep(1000);
            driver.findElement(By.xpath(x_path_submit)).click();              // click submit button
            Thread.sleep(1000);
            actual_amt=Integer.parseInt(driver.findElement(By.xpath("(//strong[@class='ng-binding'])[2]")).getText());  // get actual amount after deposit
            Assert.assertEquals(actual_amt,(previous+amt),"Deposit Successful");        // check deposit updated
        }

        // method to withdraw
        public void Withdrawl(String x_path_amt,String x_path_submit,int amt) throws Exception
        {
            int previous=0,actual_amt=0;
            Thread.sleep(1000);
            previous=Integer.parseInt(driver.findElement(By.xpath("(//strong[@class='ng-binding'])[2]")).getText());   // get previous amount
            driver.findElement(By.xpath(x_path_amt)).sendKeys(String.valueOf(amt));                                    // enter amount to deposit
            Thread.sleep(1000);
            driver.findElement(By.xpath(x_path_submit)).click();
            Thread.sleep(1000);
            actual_amt=Integer.parseInt(driver.findElement(By.xpath("(//strong[@class='ng-binding'])[2]")).getText());   // get actual amount after deposit
            Assert.assertEquals((previous-amt),(actual_amt),"Both Are Equal");         // check deposit updated
        }
    }

