import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Roundwaytrip {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");

        //Creating the driver
        WebDriver driver = new ChromeDriver();

       Roundwaytrip(driver);
    }
    public static void Roundwaytrip(WebDriver driver) throws InterruptedException {
        driver.navigate().to(" https://www.goibibo.com/ ");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[contains(text(),'Round-trip')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/ul/li[2]/span[2]]")).sendKeys("New York");
    }
}

