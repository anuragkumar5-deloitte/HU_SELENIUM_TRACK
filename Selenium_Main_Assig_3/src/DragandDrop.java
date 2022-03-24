import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragandDrop {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");

        //Creating the driver
        WebDriver driver = new ChromeDriver();

        DragandDrop(driver);
}
    public static void DragandDrop(WebDriver driver) throws InterruptedException {
        driver.navigate().to("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        Actions act = new Actions(driver);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
        act.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).perform();
        System.out.println("Successfully Drag And Drop");
        driver.quit();
    }
}
