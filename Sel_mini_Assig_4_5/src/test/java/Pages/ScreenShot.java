package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScreenShot {

    WebDriver driver;

    public ScreenShot(WebDriver driver){
        this.driver = driver;
    }

    public void takeSS(String message){
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File takeScreenShot = screenshot.getScreenshotAs(OutputType.FILE);
        String date= new SimpleDateFormat("yyyyMMddHHmmss",Locale.getDefault()).format(new Date());
        String filename = message+"_"+date;
        File saveScreenShot = new File("src/Screenshots/"+filename+".jpg");
        try {
            FileUtils.copyFile(takeScreenShot,saveScreenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
