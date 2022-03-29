package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage {
    WebDriver driver;
    By finish = By.xpath("//button[@id='finish']");

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean sumTotalAmount(){
        List<WebElement> we = driver.findElements(By.xpath("//div[@class=\"inventory_item_price\"]"));
        double sum = 0;

        for(WebElement w: we){
            sum += Double.parseDouble(w.getText().replace("$",""));
        }
        double itemTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]")).getText().replace("Item total: $",""));

//        System.out.println(sum+" "+itemTotal);
        return sum == itemTotal;

    }

    public boolean totalAmount(){
        double itemTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]")).getText().replace("Item total: $",""));
        double tax = Double.parseDouble(driver.findElement(By.xpath("//div[@class=\"summary_tax_label\"]")).getText().replace("Tax: $",""));
        double total = Double.parseDouble(driver.findElement(By.xpath("//div[@class=\"summary_total_label\"]")).getText().replace("Total: $",""));
        double sum = itemTotal+tax;
        sum = Math.round(sum*100.0)/100.0;

//        System.out.println(itemTotal+" "+tax+" "+total+" "+sum);
        return sum == total;
    }

    public void finish(){
        driver.findElement(finish).click();
    }

}