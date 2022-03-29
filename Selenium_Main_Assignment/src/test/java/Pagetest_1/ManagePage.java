package Pagetest_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class ManagePage {
        WebDriver driver;

        // constructor to initialize driver
        public ManagePage(WebDriver driver)
        {
            this.driver=driver;
        }

        // method to select option in manager login
        public void ManageOptionSelection(String x_path)
        {
            driver.findElement(By.xpath(x_path)).click();
        }

        // adding customer details in add customer
        public String InputCustomerValuesToAdd(String first_name,String last_name,String pin_code)
        {
            String validate="";
            driver.findElement(By.xpath("("+"//input[@type='text']"+")[1]")).sendKeys(first_name); // send first name to textbox
            driver.findElement(By.xpath("("+"//input[@type='text']"+")[2]")).sendKeys(last_name);  // send last name to textbox
            driver.findElement(By.xpath("("+"//input[@type='text']"+")[3]")).sendKeys(pin_code);   // send pincode name to textbox
            driver.findElement(By.xpath("//button[@type='submit']")).click();                      // click submit button
            validate=driver.switchTo().alert().getText();                                          // validate alert message
            driver.switchTo().alert().accept();

            return validate;
        }

        // method to open account in manager login
        public void OpenAccount()
        {
            Select dropBox = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));  //create select object for dropdown
            dropBox.selectByIndex(dropBox.getOptions().size()-1);                                     // select option
            Select dropBox2 = new Select(driver.findElement(By.xpath("//select[@id='currency']")));   //create select object for dropdown
            dropBox2.selectByIndex(dropBox2.getOptions().size()-1);                                   // select option
            driver.findElement(By.xpath("//button[@type='submit']")).click();                         // click submit
            driver.switchTo().alert().accept();                                                       // alert accept
        }

    }

