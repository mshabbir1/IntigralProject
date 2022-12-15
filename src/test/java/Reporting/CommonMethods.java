package Reporting;
import java.io.File;

//import io.qameta.allure.Attachment;
import Utilities.Initialization;
import Utilities.Initialization;
import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CommonMethods extends Initialization {

    //static DriverFactory df = new DriverFactory();
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
    public static void captureScreenShot(WebDriver driver, String name )

    {
        try {
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File screenshot=scrShot.getScreenshotAs(OutputType.FILE);
            File path = new File(System.getProperty("user.dir")+ "/Screenshots/"+name+timestamp()+".jpg");
            FileUtils.copyFile(screenshot,path);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static String takeScreenShot(String name) throws IOException {

        //create a string variable which will be unique always
        String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

        //create object variable of TakeScreenshot class
        TakesScreenshot ts = (TakesScreenshot)browser;

        //create File object variable which holds the screen shot reference
        File source = ts.getScreenshotAs(OutputType.FILE);

        //store the screen shot path in path variable. Here we are storing the screenshots under screenshots folder
        String path = System.getProperty("user.dir") + "/Report/Screenshots/fail/"+timestamp() +"_"+name+".png";

        //create another File object variable which points(refer) to the above stored path variable
        File destination = new File(path);

        //use FileUtils class method to save the screen shot at desired path
        FileUtils.copyFile(source, destination);

        //return the path where the screen shot is saved
        return path;
    }
    public static String passtakeScreenShot(String name) throws IOException {

        //create a string variable which will be unique always
        String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

        //create object variable of TakeScreenshot class
        TakesScreenshot ts = (TakesScreenshot)browser;

        //create File object variable which holds the screen shot reference
        File source = ts.getScreenshotAs(OutputType.FILE);

        //store the screen shot path in path variable. Here we are storing the screenshots under screenshots folder
        String path = System.getProperty("user.dir") + "/Report/Screenshots/pass/"+timestamp() +"_"+name+".png";

        //create another File object variable which points(refer) to the above stored path variable
        File destination = new File(path);

        //use FileUtils class method to save the screen shot at desired path
        FileUtils.copyFile(source, destination);

        //return the path where the screen shot is saved
        return path;
    }
}

