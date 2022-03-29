package Test;

import Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;



public class LoginTest {
    WebDriver driver;
    @BeforeMethod
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    @Test
    public void getCredentials() throws IOException, InterruptedException {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        String loginCredentials = "src/dataFiles/loginCredentials.xlsx";

        FileOutputStream fos = new FileOutputStream(loginCredentials, true);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet =wb.createSheet("Credentials");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Username");

        cell = row.createCell(1);
        cell.setCellValue("Password");
        wb.write(fos);

        fos.close();
        wb.close();

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);
        List<String> acceptedUserNames = Arrays.asList(driver.findElement(By.xpath("//div[@id=\"login_credentials\"]")).getText().split("\\r?\\n"));
        String acceptedPassword = Arrays.asList(driver.findElement(By.xpath("//div[@class=\"login_password\"]")).getText().split("\\r?\\n")).get(1);


        fos = new FileOutputStream(loginCredentials, true);
        wb = new XSSFWorkbook(loginCredentials);
        sheet = wb.getSheet("Credentials");

        int rowCount = 1;
        for (String s : acceptedUserNames){
            if(!s.equals("Accepted usernames are:")) {
            row = sheet.createRow(rowCount);
            cell= row.createCell(0);
            cell.setCellValue(s);

            cell = row.createCell(1);
            cell.setCellValue(acceptedPassword);
            wb.write(fos);
            rowCount += 1;
            }
        }

        fos.close();
        wb.close();



    }

    @Test (priority = 0)
    public void  Login() throws IOException, InterruptedException {
        String loginCredentials = "src/dataFiles/loginCredentials.xlsx";

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ExtentTest test1 = extent.createTest("login", "logging in with different username and passwords");

        FileInputStream fis = new FileInputStream(loginCredentials);
        XSSFWorkbook wb = new XSSFWorkbook(loginCredentials);

        XSSFSheet sheet = wb.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        String username = null;
        String password = null;

        LoginPage login;


        for(int i = 1; i<= sheet.getLastRowNum();i++) {
            row = sheet.getRow(i);

            for (int j = 0; j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    username = cell.getStringCellValue();
                }
                if (j == 1) {
                    password = cell.getStringCellValue();
                }

            }

            //write login
            System.out.println("Logging in with username : " + username + " password : " + password);
            test1.log(Status.INFO, "Logging in with username : " + username + " password : " + password);
            test1.info("Opening www.saucedemo.com");
            driver.get("https://www.saucedemo.com/");
            Thread.sleep(1000);
            login = new LoginPage(driver);
            login.enterUserName(username);
            Thread.sleep(500);
            login.enterPassword(password);
            Thread.sleep(500);
            login.clickLogin();
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.saucedemo.com/inventory.html";

            ScreenShot ss = new ScreenShot(driver);


            if (currentUrl.equals(expectedUrl)) {
                System.out.println("Test - Login : Pass");
                test1.pass("Login Successful with username : "+username+" and password : "+password );
            } else {
                System.out.println("Test - Login : Fail");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"Login_failed"+"_"+date+".jpg";
                test1.fail("Test - Login : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                ss.takeSS("Login_failed");
                continue;
            }

            ExtentTest test2 = extent.createTest("Filter funtionality", "Checking whether the filter is working or not");

            InventoryPage inventoryPage = new InventoryPage(driver);
            Thread.sleep(2000);

            double priceBeforeSort = inventoryPage.getPrice();
            inventoryPage.filterHighToLow();
            Thread.sleep(2000);
            double priceAfterSort = inventoryPage.getPrice();

            if(priceAfterSort != priceBeforeSort){
                System.out.println("Test - Sorting High to Low : Pass");
                test2.pass("Filter High to low working ");
            }
            else{
                System.out.println("Test - Sorting High to Low : Fail");
                ss.takeSS("sorting_Failed");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"sorting_Failed"+"_"+date+".jpg";
                test2.fail("Test - Sorting High to Low : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                continue;
            }
            Thread.sleep(2000);

            inventoryPage.addItem();
            Thread.sleep(2000);
            inventoryPage.removeItem();
            Thread.sleep(2000);
            inventoryPage.addItem();
            Thread.sleep(2000);

            inventoryPage.openShoppingCart();
            Thread.sleep(2000);
            CartPage cartPage = new CartPage(driver);
            cartPage.continueShopping();
            Thread.sleep(2000);
            inventoryPage.filterLowToHigh();
            Thread.sleep(2000);
            int prevCount = inventoryPage.returnNumberOfItemsInCart();
            inventoryPage.addItem();
            Thread.sleep(2000);
            int presentCount = inventoryPage.returnNumberOfItemsInCart();
            ExtentTest test3 = extent.createTest("Cart Item Count", "Verifying that the count of items is updated");

            if(presentCount==prevCount+1){
                System.out.println("Test - Cart items count : Pass");
                test3.pass("Test - Cart items count : Pass");
            }
            else{
                System.out.println("Test - Cart items count : Fail");
                ss.takeSS("Cart_item_count");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"Cart_item_count"+"_"+date+".jpg";
                test3.fail("Test - Cart items count : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                continue;
            }

            ExtentTest test4 = extent.createTest("Cart items and Icon badge match", "Number of items in the cart match the cart icon badge");

            inventoryPage.openShoppingCart();
            Thread.sleep(2000);
            int cartCount = cartPage.getCartItemCount();
            if(cartCount==presentCount){
                System.out.println("Test - Cart items count same as cart Icon count badge: Pass");
                test4.pass("Test - Cart items count same as cart Icon count badge: Pass");
            }
            else{
                System.out.println("Test - Cart items count same as cart Icon count badge: Fail");
                ss.takeSS("cart_items_count_badge");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"cart_items_count_badge"+"_"+date+".jpg";
                test4.fail("Test - sum of cart Items cost is same on both pages : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                continue;
            }


            cartPage.chekOut();
            Thread.sleep(2000);

            CheckOutPage checkOutPage = new CheckOutPage(driver);
            checkOutPage.setFirstName(username);
            checkOutPage.setLastName(password);
            checkOutPage.setPostalCode(username+password);
            Thread.sleep(2000);
            checkOutPage.continueCheckout();
            Thread.sleep(2000);

            CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
            ExtentTest test5 = extent.createTest("cost Check", "cost of all the items is same on both the inventory and the checkout page");

            if(checkoutOverviewPage.sumTotalAmount()){
                System.out.println("Test - sum of cart Items cost is same on both pages : Pass");
                test5.pass("Test - sum of cart Items cost is same on both pages : Pass");
            }
            else{
                System.out.println("Test - sum of cart Items cost is same on both pages : Fail");
                ss.takeSS("selectedProduct_cartProduct_price");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"cart_items_count_badge"+"_"+date+".jpg";
                test5.fail("Test - sum of cart Items cost is same on both pages : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                continue;
            }

            ExtentTest test6 = extent.createTest("Total amount", "Verifying the calculation of the total amount");

            if(checkoutOverviewPage.totalAmount()){
                System.out.println("Test - Calculation of the total amount on Checkout overview page : Pass");
                test6.pass("Test - Calculation of the total amount on Checkout overview page : Pass");
            }
            else{
                System.out.println("Test - Calculation of the total amount on Checkout overview page : Fail");
                ss.takeSS("sumCalculationOverview");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String filename = "src/Screenshots/extent/"+"sumCalculationOverview"+"_"+date+".jpg";
                test6.fail("Test - Calculation of the total amount on Checkout overview page : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
                continue;
            }

            checkoutOverviewPage.finish();
            Thread.sleep(2000);

            CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
            ExtentTest test7 = extent.createTest("Checkout message", "verifying the checkout message displayed or not ");

            if(checkoutCompletePage.verifyCheckout()){
                System.out.println("Test - Checkout message displayed : Pass");
                test7.pass("Test - Checkout message displayed : Pass");
            }
            else{
                System.out.println("Test - Checkout message displayed : Fail");
                ss.takeSS("checkoutMessage");
                String date= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date())+".jpg";
                String filename = "src/Screenshots/extent/"+"checkoutMessage"+"_"+date;
                test7.fail("Test - Checkout message displayed : Fail", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
            }

//            driver.get("https://www.saucedemo.com/");

        }
        wb.close();
        fis.close();
        extent.flush();


    }


    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
