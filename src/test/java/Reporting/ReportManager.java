package Reporting;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static String reportBaseDirectory;
    private static ExtentReports extent;
    public static final String OUTPUT_FOLDER_SCREENSHOTS ="/Screenshots/";
    public static final String REPORT_FILE_PATH =System.getProperty("user.dir")+ "/Report/";


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());}
    //Create an extent report instance
    public static void createInstance() {
        ReportManager.initDirectories();
        setReportBaseDirectory(REPORT_FILE_PATH);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(REPORT_FILE_PATH+timestamp()+"-Report.html");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Intigral Project Automation Test Results");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setJS("$('.brand-logo').text('FarEye');");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        // htmlReporter.config().setAutoCreateRelativePathMedia(true);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS",  System.getProperty("os.name"));
        extent.setSystemInfo("Java",  System.getProperty("java.specification.version"));
        extent.setSystemInfo("User",  System.getProperty("user.name"));
    }

    public synchronized static String getReportBaseDirectory() {
        return reportBaseDirectory;
    }
    public synchronized static void setReportBaseDirectory(String reportBaseDirectory) {
        ReportManager.reportBaseDirectory = reportBaseDirectory;
    }

    public static void initDirectories() {
        try {
            createFolder(REPORT_FILE_PATH
                    + OUTPUT_FOLDER_SCREENSHOTS
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) file.mkdirs();
    }
}
