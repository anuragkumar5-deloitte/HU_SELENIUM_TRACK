package testcase;

import Base.BaseTest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class VerifyFunction extends BaseTest {
    WebDriver driver;

    @Test
    public void login() { // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Mini_Assig_Q\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }
    @Test(dataProvider = "Authentication")

    public void getCredentials(String firstName, String Lastname, String Postcode)  throws IOException, InterruptedException {

        String loginCredentials = "C:\\Users\\anuragkumar5\\Desktop\\Files\\Selenium_track\\Selenium_assignment\\Selenium_Main_Assignment\\TestData.xlsx";

        FileOutputStream fos = new FileOutputStream(loginCredentials, true);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Credentials");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("firstName");

        cell = row.createCell(1);
        cell.setCellValue("Lastname");
        wb.write(fos);

        fos.close();
        wb.close();

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        Thread.sleep(1000);
        List<String> acceptedfirstNames = Arrays.asList(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")).getText().split("\\r?\\n"));
        String acceptedLastname= Arrays.asList(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")).getText().split("\\r?\\n")).get(1);


        fos = new FileOutputStream(loginCredentials, true);
        wb = new XSSFWorkbook(loginCredentials);
        sheet = wb.getSheet("Credentials");

        int rowCount = 1;
        for (String s : acceptedfirstNames) {
            if (!s.equals("Accepted usernames are:")) {
                row = sheet.createRow(rowCount);
                cell = row.createCell(0);
                cell.setCellValue(s);

                cell = row.createCell(1);
                cell.setCellValue(acceptedLastname);
                wb.write(fos);
                rowCount += 1;
            }
        }

        fos.close();
        wb.close();
    }
    @AfterMethod

    public void afterMethod() {

        driver.close();

    }

    @Test(priority = 2, dependsOnMethods = "VerifyLogin")
    public void VerifyCustomerPage() {
        ncustomer.clickNewCustomerLink();
        ncustomer.typeName("ductv");
        ncustomer.selectGender("f");
        ncustomer.clickSubmit();
        ncustomer.verifyNewCustomerSuccess();
    }

    @Test(priority = 3, dependsOnMethods = "VerifyCustomerPage")
    public void VerifyAccountPage() {
        naccount.clickNewAccount();
        naccount.clickSubmit();
        naccount.verifyNewCustomerSuccess();

    }

    @Test(priority = 4, dependsOnMethods = "VerifyAccountPage")
    public void VerifyWithdrawalPage() {
        withdrawal.clickWithdrawalLink();
        withdrawal.typeAmount("2000");
        withdrawal.clickSubmit();
        withdrawal.VerifyFailedPopUp();
        withdrawal.closePopUp();
    }
}
