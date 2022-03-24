import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;;
public class AlertsAssignments {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AlertsAssignments(driver);
    }
    public static void AlertsAssignments(WebDriver driver) throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/javascript_alerts']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        Thread.sleep(2000);
        String alert = driver.switchTo().alert().getText();
        System.out.println(alert);
        String input="Ex-Test";
        driver.switchTo().alert().sendKeys(input);
        driver.switchTo().alert().accept();
        String result=driver.findElement(By.xpath("//p[@id=\"result\"]")).getText();
        System.out.println(result);
        if(result.contains(input))
        {
            System.out.println("PASS");
        }else{
            System.out.println("FAIL");
        }
        driver.quit();
    }
}
