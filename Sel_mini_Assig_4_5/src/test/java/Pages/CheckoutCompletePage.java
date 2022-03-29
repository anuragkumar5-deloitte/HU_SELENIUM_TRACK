package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {
    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyCheckout(){
        String h2  = driver.findElement(By.xpath("//h2")).getText();

        if(h2.equals("THANK YOU FOR YOUR ORDER")){
            return true;
        }

        return false;
    }
}
