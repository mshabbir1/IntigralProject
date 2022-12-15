package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Helpers extends Initialization {


    public WebElement selectElement(String locatorType, String value) {
        WebElement getElement = null;

        if (locatorType == "id") {
            getElement = browser.findElement(By.id(value));
        } else if (locatorType == "xpath") {
            getElement = browser.findElement(By.xpath(value));
        } else if (locatorType == "css") {
            getElement = browser.findElement(By.cssSelector(value));
        } else if (locatorType == "name") {
            getElement = browser.findElement(By.name(value));
        } else if (locatorType == "className") {
            getElement = browser.findElement(By.className(value));
        } else if (locatorType == "tagName") {
            getElement = browser.findElement(By.tagName(value));
        } else if (locatorType == "linkText") {
            getElement = browser.findElement(By.linkText(value));
        } else {
            System.out.println("No WebElement Found");
        }
        return getElement;
    }

    public void waitingTime(int duration_in_Seconds) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration_in_Seconds));
    }

    public void explicitWait(int timeoutDuration, String locator) {
        WebDriverWait element = new WebDriverWait(browser, Duration.ofSeconds(timeoutDuration));
        element.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }



}