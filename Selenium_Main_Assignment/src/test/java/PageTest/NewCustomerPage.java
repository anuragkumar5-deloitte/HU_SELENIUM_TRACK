package PageTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class NewCustomerPage {
    WebDriver driver;

    /**
     * This class will store all the locator and method of New Customer page
     */

    //New Customer form
    By new_customer_link = By.xpath("//a[contains(text(),'New Customer')]");
    By customer_name = By.xpath("//*[contains(text(),\"Customer Name\")]/../td/input");
    By submit_btn = By.xpath("//input[@value='Submit']");
    By message_success = By.xpath("//*[contains(text(),'Customer Registered Successfully')]");

    public NewCustomerPage(WebDriver driver) {

        this.driver = driver;
    }

    public void clickNewCustomerLink() {

        driver.findElement(new_customer_link).click();
    }

    public void typeName(String name) {

        driver.findElement(customer_name).sendKeys(name);

    }

    public void selectGender(String gvalue) {
        //Only 2 value "m" for male and "f" for female

        driver.findElement(By.xpath("//input[@value='" + gvalue + "']")).click();

    }

    public void clickSubmit() {

        driver.findElement(submit_btn).click();
    }

    public boolean verifyNewCustomerSuccess() {

        try {

            WebElement ele = driver.findElement(message_success);

            ele.isDisplayed();

            return true;

        } catch (Exception e) {

            e.getMessage();

        }

        return false;
    }
}

