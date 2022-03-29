package Pagetest_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class HomePage {
        WebDriver driver;

        // constructor to initialize driver
        public HomePage(WebDriver driver)
        {
            this.driver=driver;
        }

        // method for home page for user login
        public void homePageLogin(String x_path,String x_path_submit)
        {
            WebElement element = driver.findElement(By.xpath(x_path)); // creating web element for dropbox in login
            Select select=new Select(element);                         // creating select object to select
            select.selectByIndex(select.getOptions().size()- 1);       // selecting user name
            driver.findElement(By.xpath(x_path_submit)).click();       // click submit button
        }
    /* public void ReturnFirstLoginPage(String home_x_path)
    {
        driver.findElement(By.xpath(home_x_path)).click();
    }*/
    }

