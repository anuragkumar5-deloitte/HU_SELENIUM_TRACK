package Pagetest_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login {
        WebDriver driver;

        // constructor to initialize driver
        public Login(WebDriver driver)
        {
            this.driver=driver;
        }

        // method for login page to select login options in login page
        public void login(String x_path)
        {
            // it will click the button at this x path
            driver.findElement(By.xpath(x_path)).click();
        }
    }

