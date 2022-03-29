package PageTest;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
public class WithdrawalPage extends BaseTest {

        WebDriver driver;

        /**
         *
         * This class will store all the locator and method of Withdrawal page
         */


        //Withdrawal form
        By withdrawal_link = By.xpath("/html/body/div/div/div[2]/div/div[3]/button[3]");
        By amount = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input");
        By submit_btn = By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button");

        public WithdrawalPage(WebDriver driver){

            this.driver=driver;
        }

        public void clickWithdrawalLink(){

            driver.findElement(withdrawal_link).click();
        }
        public void typeAmount(String value){

            driver.findElement(amount).sendKeys(value);
        }
        public void clickSubmit(){

            driver.findElement(submit_btn).click();
        }

        public void VerifyFailedPopUp(){

            String fail_str =  "Transaction Failed. Account Balance High then Available Balance";


            String text = driver.switchTo().alert().getText();


            assertTrue(text.contains(fail_str));

        }

        public void closePopUp(){

            driver.switchTo().alert().accept();
        }
    }

