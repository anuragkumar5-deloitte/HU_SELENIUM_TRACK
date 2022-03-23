package WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MiniAssignHeadless {
        public static void main(String[] args) throws InterruptedException
        {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");

            //headless
            WebDriver driver;
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            System.out.println("Chrome window size is maximized");
            driver.get("https://phptravels.com/demo");
            System.out.println("navigation to a given url is successful!");
            Thread.sleep(3000);

            //declare handle window
            String parentWindow = driver.getWindowHandle();

            //to get title
            String actualTitle = driver.getTitle();
            System.out.println("if the title of the url is same as 'PHPTRAVELS' then return pass else fail: ");
            if(actualTitle.contains("PHPTRAVELS")){
                System.out.println("PASS");
            }else{
                System.out.println("FAIL");
            }
            Thread.sleep(3000);

            //login option
            By loginBtn = By.xpath("//a[text()='Login']");
            driver.findElement(loginBtn).click();
            System.out.println("clicking on login button is successful!");
            Thread.sleep(3000);
            String actualTitle1 = driver.getTitle();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("if the title of the currenturl is same as previousurl title then return pass else fail: ");
            if(actualTitle1.contains(actualTitle)){
                System.out.println("PASS");
            }else{
                System.out.println("FAIL");
            }
            Thread.sleep(3000);


            //navigate back to previous page
            driver.switchTo().window(parentWindow);
            System.out.println();
            System.out.println("switching back to the parent window is successful");
            Thread.sleep(3000);

            //get current url
            String currentUrl = driver.getCurrentUrl();
            System.out.println("current url: "+currentUrl);



            //click on price option
            By priceOpt = By.xpath("//a[text()='Pricing']");
            driver.findElement(priceOpt).click();
            System.out.println();
            System.out.println("clicking on price is successful!");
            Thread.sleep(3000);

            //Navigate backwards in the same
            driver.navigate().back();
            System.out.println("navigate backwords in the existing window is successful!");
            Thread.sleep(3000);

            //refresh
            driver.navigate().refresh();
            System.out.println("reloading the current webpage is successful!");
            Thread.sleep(3000);

            //close
            driver.close();
            System.out.println("driver is closed successfully...");

        }
    }