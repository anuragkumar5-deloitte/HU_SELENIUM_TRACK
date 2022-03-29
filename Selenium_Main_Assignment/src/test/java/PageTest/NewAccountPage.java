package PageTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class NewAccountPage {

        WebDriver driver;

        /**
         *
         * This class will store all the locator and method of account page
         */

        //New Account form
        By Bank_Manager_Login= By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button");
        By Add_Customer = By.xpath("//button[contains(text(),'Add Customer')]");
        By Open_Account = By.xpath("//button[contains(text(),'Open Account')]");
        By submit_btn = By.xpath("//input[@value='submit']");
        By message_success = By.xpath("//*[contains(text(),'Account Generated Successfully')]");

        public NewAccountPage(WebDriver driver){

            this.driver=driver;
        }

        public void clickNewAccount(){

            driver.findElement(Bank_Manager_Login).click();
        }


        public void typeCustomerId(String cupid){

            driver.findElement(Add_Customer).sendKeys(cupid);
            WebElement fname = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input"));
            fname.click();
            fname.sendKeys("Anurag");
            WebElement lname = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input"));
            lname.click();
            lname.sendKeys("kumar");
            WebElement Post_code = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input"));
            lname.click();
            lname.sendKeys("560024");

        }

        public void SelectAccountType(String value){
            //2 values: Savings, Current

            try {

                WebElement ele = driver.findElement(Open_Account);

                Select select = new Select(ele);

                select.selectByValue(value);

            }catch(Exception e){

                e.printStackTrace();
            }

        }

        public void clickSubmit(){

            driver.findElement(submit_btn).click();
        }

        public boolean verifyNewCustomerSuccess(){

            try {

                WebElement ele = driver.findElement(message_success);

                ele.isDisplayed();

                return true;

            }catch(Exception e){

                e.getMessage();

            }

            return false;
        }


        }
