package WebDriver;
import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.font.TrueTypeFont;

public class Test {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        launchUrl(driver);

    }
    public static void Title(WebDriver driver) {
        if(driver.getTitle().contains("PHPTRAVELS"))
            //Pass
            System.out.println("PASS ");
        else
            //Fail
            System.out.println("FAIL ");
    }

    public static void Current(WebDriver driver) {
        driver.getCurrentUrl();
    }

    public static void Navigation(WebDriver driver) {
        driver.navigate().to("https://phptravels.com/demo");

    }

    public static void launchUrl(WebDriver driver) {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

    }
    private static void Login(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe  ");
        driver.manage().window().maximize();
        driver.get("https://phptravels.com/demo");
        WebElement username=driver.findElement(By.id("Anu-rag"));
        WebElement password=driver.findElement(By.id("123456"));
        WebElement login=driver.findElement(By.xpath("//button[text()='Sign in']"));
        username.sendKeys("anuragkumar5@deloitte.com"); password.sendKeys("123456");
        login.click(); String actualUrl="https://phptravels.com/demo";
        String expectedUrl= driver.getCurrentUrl(); if(actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println("Test passed"); } else { System.out.println("Test failed"); } }
}
   /* String Actual = driver.getTitle();
    String Expected = "Demo Script Test drive - PHPTRAVELS";
            if(Actual.equals(Expected))
                    {
                    System.out.println("PASS");
                    }
                    else
                    {
                    System.out.println("FAIL");
                    }*/


