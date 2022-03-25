import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class TestAssignment extends Auto {
    @Test
    public void login() throws Exception {
        String excelPath = "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Selenium_assignment\\Maven_Assigment\\loginCred.xls";
        FileInputStream file = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        String username = null;
        String password = null;
        String firstname = null;
        String lastname = null;
        String postalcode = null;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (i == 2 && j == 0)
                    firstname = cell.getStringCellValue();
                if (i == 2 && j == 1)
                    lastname = cell.getStringCellValue();
                if (i == 3 && j == 0)
                    postalcode = cell.getStringCellValue();
                if (i == 1 && j == 0)
                    username = cell.getStringCellValue();
                if (i == 1 && j == 1)
                    password = cell.getStringCellValue();
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        boolean condition = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")).isEnabled();
        if(condition)
            System.out.println("Add to Cart option is Enabled and Clickable");
        else
            System.out.println("Add to Cart option is not Enabled and Clickable");
        WebElement dropDown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        Select dropdown = new Select(dropDown);
        Thread.sleep(2000);
        //Arrange the cart Category
        dropdown.selectByVisibleText("Price (high to low)");
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();
        //checking the remove button is enabled or not
        boolean cond =driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-fleece-jacket']")).isEnabled();
        if(cond)
            System.out.println("Remove Button is Enabled");
        else
            System.out.println("Remove Button is not Enabled");
        Thread.sleep(2000);
        //removing the product from the cart
        driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-fleece-jacket']")).click();
        System.out.println("Product is removed from the cart");
        Thread.sleep(2000);
        //Adding the product to the cart again
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();
        Thread.sleep(2000);
        //clicking on the cart icon
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        System.out.println("Cart is opened");
        boolean checkout = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium checkout_button']")).isEnabled();
        if(!checkout)
            driver.navigate().refresh();
        else
            System.out.println("Product is added to the cart");
        //checking count of the cart
        String count = driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).getText();
        System.out.println(count);
        System.out.println("Only one product is Added to the cart");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class=\"btn btn_action btn_medium checkout_button\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"first-name\"]")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id=\"last-name\"]")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id=\"postal-code\"]")).sendKeys(postalcode);
        driver.findElement(By.xpath("//input[@id=\"continue\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id=\"finish\"]")).click();
        Thread.sleep(2000);
        String final_output=driver.findElement(By.xpath("//h2[@class=\"complete-header\"]")).getText();
        System.out.println(final_output);
    }
}
