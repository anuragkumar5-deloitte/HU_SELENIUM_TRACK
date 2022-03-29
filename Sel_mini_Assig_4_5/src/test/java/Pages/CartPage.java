package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;
    By continueShopping = By.xpath("//button[@id=\"continue-shopping\"]");
    By checkOut = By.xpath("//button[@id=\"checkout\"]");
    By remove = By.xpath("//button[text()=\"Remove\"]");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void continueShopping(){
        driver.findElement(continueShopping).click();
    }
    public void chekOut(){
        driver.findElement(checkOut).click();
    }
    public void remove(){
        driver.findElement(remove).click();
    }

    public int getCartItemCount (){
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class=\"cart_item\"]"));
        return cartItems.size();
    }

}
