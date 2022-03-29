package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver ;
    By userName = By.xpath("//input[@id='user-name']");
    By password = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//input[@id='login-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String validUserName){
        WebElement userNameField = driver.findElement(userName);
        userNameField.sendKeys(validUserName);
    }
    public void enterPassword(String validPassword){
        WebElement userNameField = driver.findElement(password);
        userNameField.sendKeys(validPassword);
    }
    public void clickLogin(){
        WebElement login = driver.findElement(loginButton);
        login.click();
    }

}
