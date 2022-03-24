import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class FramesAssignments {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");

        //Creating the driver
        WebDriver driver = new ChromeDriver();

        FramesAssignments(driver);
    }
    public static void FramesAssignments(WebDriver driver) throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='/frames']")).click();
        driver.findElement(By.xpath("//a[@href='/nested_frames']")).click();
        Thread.sleep(2000);
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String title=driver.findElement(By.xpath("/html/body")).getText();
        System.out.println(title);
        driver.quit();
    }
}
