package WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MiniAssignment2 {
        public static void main(String[] args) throws InterruptedException
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");

            //Creating the driver
            WebDriver driver = new ChromeDriver();

            launchingUrl(driver);
        }
        public static void launchingUrl(WebDriver driver) throws InterruptedException {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            Thread.sleep(4000);
            driver.navigate().to("https://phptravels.com/demo");
            if(driver.getTitle().contains("PHPTRAVELS"))
                //Pass
                System.out.println("PASS ");
            else
                //Fail
                System.out.println("FAIL ");
            Thread.sleep(4000);
            driver.navigate().to("https://phptravels.org/login");
            String Act = driver.getTitle();
            String Exp = "Login";
            if(Act.equals(Exp))
            {
                System.out.println("PASS");
            }
            else
            {
                System.out.println("FAIL");
            }
            Thread.sleep(4000);
            driver.navigate().back();
            String Actu = driver.getTitle();
            String Expe = "Demo Script Test drive - PHPTRAVELS";
            if(Actu.equals(Expe))
            {
                System.out.println("https://phptravels.com/demo");
            }
            else
            {
                System.out.println("FAIL");
            }
            Thread.sleep(4000);
            driver.findElement(By.linkText("Pricing")).click();
            Thread.sleep(4000);
            driver.navigate().back();
            Thread.sleep(4000);
            driver.navigate().refresh();
            Thread.sleep(4000);
            driver.close();

        }

    }

