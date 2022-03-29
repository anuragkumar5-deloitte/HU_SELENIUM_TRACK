package Pagetest_1;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ScreenShot {
        WebDriver driver;

        // constructor to initialize driver
        public ScreenShot(WebDriver driver)
        {
            this.driver=driver;
        }

        // method to take screenshot on success
        public void SuccessShot(String ss)
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;    // creating screen shot object on success
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);   // creating file for photo
            File des = new File(System.getProperty("user.dir")+"/SuccessScreenshot/"+ss+".jpg");
            try {
                FileUtils.copyFile(src,des);  // making file copy to internal storage
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // method to take screenshot on failure
        public void FailShot(String ss)
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;     // creating screen shot object on failure
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);    // creating file for photo
            File des = new File(System.getProperty("user.dir")+"/FailScreenshot/"+ss+".jpg");
            try {
                FileUtils.copyFile(src,des);    // making file copy to internal storage
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
