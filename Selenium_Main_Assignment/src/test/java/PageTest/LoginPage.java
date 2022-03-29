package PageTest;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage extends BaseTest {

        WebDriver driver;

        /**
         *
         * This class will store all the locator and method of login page
         */


        //Login form

        public LoginPage(WebDriver driver){

            this.driver=driver;
        }
        public boolean  loginSuccess(String user){

            try {

                WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'Manger Id : "+user+"')]"));

                ele.isDisplayed();

            }catch(Exception e){

                e.getMessage();
            }

            return true;
        }

    public void loginSuccess(
            String anurag,
            String kumar,
            String s) {
    }
}

