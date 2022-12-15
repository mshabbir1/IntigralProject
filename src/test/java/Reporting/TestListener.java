package Reporting;

import Utilities.Initialization;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class TestListener extends Initialization implements ITestListener {
    private static long endTime;
    // public String browserName;

    @Attachment(type = "image/png")
    public  byte[]  takeScreen(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    private static void setStartTime(long startTime) {
    }
    private static void setEndTime(long endTime) {
        TestListener.endTime = endTime;
    }
    @Override
    public synchronized void onStart(ITestContext context) {
    }
    @Override
    public synchronized void onFinish(ITestContext context) {
        setStartTime(context.getStartDate().getTime());
        setEndTime(context.getEndDate().getTime());
    }
    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println("--------- Executing :- " + getSimpleMethodName(result) + " ---------");
        ReportTestManager.createTest(result.getName(),result.getMethod().getDescription());
        ReportTestManager.setCategoryName(getSimpleClassName(result));
    }
    @Parameters("browserName")
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        try {
            WebDriver browser = Initialization.browser;
            System.out.println("I am in OnTestSuccess method " + getSimpleMethodName(result) + " Passed");
            // Allure ScreenShot and SaveTestLog
            if (browser instanceof WebDriver) {
                takeScreen(browser);
            }
            ReportTestManager.getTest().assignCategory(getSimpleClassName(result));
            ReportTestManager.getTest().addScreenCaptureFromPath(CommonMethods.passtakeScreenShot(result.getName())).getModel().getScreenCaptureList().get(0);
            addExtentLabelToTest(result);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ReportTestManager.endTest();

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        try {
            WebDriver browser = Initialization.browser;
            System.out.println("I am in onTestFailure method " + getSimpleMethodName(result) + " failed");
            // Allure ScreenShot and SaveTestLog
            if (browser instanceof WebDriver) {
                takeScreen(browser);
            }

            ReportTestManager.getTest().addScreenCaptureFromPath(CommonMethods.takeScreenShot(result.getName())).getModel().getScreenCaptureList().get(0);
            ReportTestManager.getTest().assignCategory(getSimpleClassName(result));
            ReportTestManager.getTest().log(Status.FAIL, result.getName() + " Test is failed \n" +result.getThrowable());
            addExtentLabelToTest(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReportTestManager.endTest();
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ReportTestManager.getTest().assignCategory(getSimpleClassName(result));
        ReportTestManager.getTest().log(Status.SKIP, result.getName() + " Test is Skipped" +  result.getThrowable());
        addExtentLabelToTest(result);
        ReportTestManager.endTest();
    }
    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
    }
    private synchronized String getSimpleMethodName(ITestResult result) {
        return result.getName();
    }

    @Parameters("browserName")
    private synchronized void addExtentLabelToTest(ITestResult result) {

        String browserName = "chrome";
        if (result.getStatus() == ITestResult.SUCCESS) {

            ReportTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
            ReportTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed on "+browserName, ExtentColor.GREEN));
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            ReportTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
            ReportTestManager.getTest().pass(MarkupHelper.createLabel("Test Failed on "+browserName, ExtentColor.RED));
        } else {
            ReportTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
            ReportTestManager.getTest().pass(MarkupHelper.createLabel("Test Skipped on "+browserName, ExtentColor.ORANGE));
        }
    }

}
