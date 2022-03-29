import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Selenium_Main_Assignment {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        launch_url(driver);           // launch the url
        first_case(driver);           // add customer
        Thread.sleep(1000);
        second_case(driver);          // add new account
        Thread.sleep(1000);
        third_case(driver);           // deposit into new account
    }
    public static void launch_url(WebDriver driver) throws InterruptedException{
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }
    public static void first_case(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]")).click();
        Thread.sleep(1000);
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust");
        Thread.sleep(2000);
        WebElement fname = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input"));
        fname.click();
        fname.sendKeys("Anurag");
        Thread.sleep(1000);
        WebElement lname = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input"));
        lname.click();
        lname.sendKeys("kumar");
        Thread.sleep(1000);
        WebElement zip = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input"));
        zip.click();
        zip.sendKeys("560024");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/button[1]")).click();
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
    public static void second_case(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")).click();
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/openAccount");
        Thread.sleep(1000);
        WebElement e1 = driver.findElement(By.xpath("//*[@id=\"userSelect\"]"));
        e1.click();
        Thread.sleep(1000);
        Select s1 = new Select(e1);
        s1.selectByVisibleText("Anurag kumar");
        Thread.sleep(1000);
        WebElement e2 = driver.findElement(By.xpath("//*[@id=\"currency\"]"));
        e2.click();
        Thread.sleep(1000);
        Select s2 = new Select(e2);
        s2.selectByVisibleText("Rupee");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/button[1]")).click();
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
    public static void third_case(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")).click();
        Thread.sleep(1000);
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        Thread.sleep(1000);
        WebElement e = driver.findElement(By.xpath("//*[@id=\"userSelect\"]"));
        e.click();
        Thread.sleep(1000);
        Select s = new Select(e);
        s.selectByVisibleText("Anurag kumar");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
        Thread.sleep(1000);
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account");

        // code for deposit

        driver.findElement(By.xpath("/html/body/div/div/div[1]/button[1]")).click();
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
}
