package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Initialization {

    public static String projectUrl = "https://subscribe.stctv.com/sa-en";
    public static WebDriver browser;
    @BeforeSuite
    public WebDriver LaunchBrowser() {

        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        System.out.println("Browser has been Launched");
        return browser;

    }

    public static String propertiesData(String propertyName) throws IOException {

        Properties prop = new Properties();
        String filePath = System.getProperty("user.dir") + "\\config.properties";
        File datafile = new File(filePath);
        FileInputStream readData = new FileInputStream(datafile);
        prop.load(readData);
        return prop.getProperty(propertyName);
    }

    public static String excelDataFetch (int rowNum, int colNum) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\Resources\\Test_Data.xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook dataLoad = new XSSFWorkbook(inputStream);
        XSSFSheet dataSheet = dataLoad.getSheet("Sheet1");
        XSSFRow row = dataSheet.getRow(rowNum);
        DataFormatter formatter = new DataFormatter();
        XSSFCell cell2 = row.getCell(colNum);
        String excelValue = formatter.formatCellValue(cell2);
        return excelValue;
    }

}
