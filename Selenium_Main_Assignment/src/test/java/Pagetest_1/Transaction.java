package Pagetest_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Transaction {
        WebDriver driver;

        // constructor to initialize driver
        public Transaction(WebDriver driver)
        {
            this.driver=driver;
        }

        // method to validate transaction
        public String Transaction(int expect_amt_credit,int expect_amt_debit) throws Exception {
            String return_value=null;
            int actual_debt_amt = 0, actual_credit_amt = 0;
            Thread.sleep(1000);

            // getting credit text from transaction page
            String string_credit = driver.findElement(By.xpath("((//tr[@class='ng-scope'])[last()-1]//td[@class='ng-binding'])[3]")).getText();

            // getting debit text from transaction page
            String string_debt = driver.findElement(By.xpath("((//tr[@class='ng-scope'])[last()]//td[@class='ng-binding'])[3]")).getText();
            Thread.sleep(1000);

            // checking for text and amount updated in transaction page
            if (string_credit.equals("Credit")) {
                actual_credit_amt = Integer.parseInt(driver.findElement(By.xpath("((//tr[@class='ng-scope'])[last()-1]//td[@class='ng-binding'])[2]")).getText());
                if (actual_credit_amt == expect_amt_credit) {
                    if (string_debt.equals("Debit")) {
                        actual_debt_amt = Integer.parseInt(driver.findElement(By.xpath("((//tr[@class='ng-scope'])[last()]//td[@class='ng-binding'])[2]")).getText());
                        if (actual_debt_amt == expect_amt_debit) {
                            return_value="transaction sucess";
                        }
                    }
                }
            }
            return return_value;
        }
    }

