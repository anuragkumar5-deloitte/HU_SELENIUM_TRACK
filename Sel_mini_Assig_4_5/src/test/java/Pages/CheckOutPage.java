package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    WebDriver driver;
    By firstName = By.xpath("//input[@id='first-name']");
    By lastName = By.xpath("//input[@id='last-name']");
    By postalCode = By.xpath("//input[@id='postal-code']");

    public CheckOutPage(WebDriver driver){
        this.driver = driver;

    }

    public void setFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
    }
    public void setLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
    }
    public void setPostalCode(String code){
        driver.findElement(postalCode).sendKeys(code);
    }

    public void continueCheckout(){
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }
}
