package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage {
    WebDriver driver ;
    By filter = By.xpath("//select[@class=\"product_sort_container\"]");
    By shoppingCart = By.xpath("//a[@class=\"shopping_cart_link\"]");
    By shoppingCartBadge = By.xpath("//span[@class=\"shopping_cart_badge\"]");
    By getPrice = By.xpath("//div[@class=\"inventory_item_price\"]");


    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public void filterLowToHigh() {
        Select applyFilter = new Select(driver.findElement(filter));
        applyFilter.selectByVisibleText("Price (low to high)");
    }
    public void filterHighToLow() {
        Select applyFilter = new Select(driver.findElement(filter));
        applyFilter.selectByVisibleText("Price (high to low)");
    }
    public void filterAtoZ() {
        Select applyFilter = new Select(driver.findElement(filter));
        applyFilter.selectByVisibleText("Name (A to Z)");
    }
    public void filterZtoA() {
        Select applyFilter = new Select(driver.findElement(filter));
        applyFilter.selectByVisibleText("Name (Z to A)");
    }

    public void openShoppingCart() {
        driver.findElement(shoppingCart).click();
    }

    public int returnNumberOfItemsInCart(){
        return Integer.parseInt(driver.findElement(shoppingCartBadge).getText());
    }

    public void addToCartJacket(){
        driver.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
    }

    public void removeCartJacket(){
        driver.findElement(By.xpath("//button[@id=\"remove-sauce-labs-fleece-jacket\"]")).click();
    }

    public double getPrice() {
        String price = driver.findElement(getPrice).getText();
        price = price.replace("$","");
        return Double.parseDouble(price);
    }
    public void addItem() {
        driver.findElement(By.xpath("//button[text()=\"Add to cart\"]")).click();
    }
    public void removeItem() {
        driver.findElement(By.xpath("//button[text()=\"Remove\"]")).click();
    }

}
