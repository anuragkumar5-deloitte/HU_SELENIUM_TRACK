import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Roundwaytrip {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Thread.sleep(3000);

        driver.get("https://www.goibibo.com/ ");
        Actions action = new Actions(driver);

        WebElement roundTripButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/ul/li[2]/span[1]"));
        roundTripButton.click();
        WebElement From = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/div[1]/div/div/p"));
        From.click();
        WebElement FromText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/div[1]/div/div[2]/div/input"));
        FromText.click();
        FromText.sendKeys("New York (NYC)");
        FromText.sendKeys(Keys.TAB);
        action.click(FromText).build().perform();

    }
}

