package TestCases;

import Selectors.HomePageSelectors;
import Utilities.Helpers;
import Utilities.Initialization;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTestCases extends Initialization {

    // Different Approach has been used in this class
    // This pattern can be followed for better Reporting of each Test Case
    // This class can be replicated for Other Countries

    Helpers hp = new Helpers();

    @BeforeClass
    public void accessIntegralWebsite() throws IOException {
        browser.get(propertiesData("projectUrl"));
        browser.manage().window().maximize();
    }

    @Test (priority = 1)
    public void countrySelected() {
        hp.selectElement("id", HomePageSelectors.countrySelection).click();
        hp.selectElement("xpath", HomePageSelectors.countryBahrain).click();
        System.out.println("Bahrain has been Selected");
        String countryName = hp.selectElement("xpath", HomePageSelectors.countryBahrain).getText();

    }

    @Test (priority = 2)
    public void validatePlans() throws IOException {

        System.out.println("Plan 1 is: "+hp.selectElement("xpath", HomePageSelectors.plan1).getText());
        Assert.assertEquals(excelDataFetch(1,1),hp.selectElement("xpath", HomePageSelectors.plan1).getText());
        System.out.println("Plan 2 is: "+hp.selectElement("xpath", HomePageSelectors.plan2).getText());
        Assert.assertEquals(excelDataFetch(2,1),hp.selectElement("xpath", HomePageSelectors.plan2).getText());
        System.out.println("Plan 3 is: "+hp.selectElement("xpath", HomePageSelectors.plan3).getText());
        Assert.assertEquals(excelDataFetch(3,1),hp.selectElement("xpath", HomePageSelectors.plan3).getText());
    }

    @Test (priority = 3)
    public void validatePrice() throws IOException {
        System.out.println("Price of Plan 1 is: "+hp.selectElement("xpath", HomePageSelectors.planPrice1).getText());
        Assert.assertEquals(excelDataFetch(1,2),hp.selectElement("xpath", HomePageSelectors.planPrice1).getText());
        System.out.println("Price of Plan 2 is: "+hp.selectElement("xpath", HomePageSelectors.planPrice2).getText());
        Assert.assertEquals(excelDataFetch(2,2),hp.selectElement("xpath", HomePageSelectors.planPrice2).getText());
        System.out.println("Price of Plan 3 is: "+hp.selectElement("xpath", HomePageSelectors.planPrice3).getText());
        Assert.assertEquals(excelDataFetch(3,2),hp.selectElement("xpath", HomePageSelectors.planPrice3).getText());
    }

    @Test (priority = 4)
    public void validateCurrency() throws IOException {

        System.out.println("Currency of Plan 1 is: "+hp.selectElement("xpath", HomePageSelectors.currency1).getText().substring(0,3));
        Assert.assertEquals(excelDataFetch(1,3),hp.selectElement("xpath", HomePageSelectors.currency1).getText().substring(0,3));
        System.out.println("Currency of Plan 2 is: "+hp.selectElement("xpath", HomePageSelectors.currency2).getText().substring(0,3));
        Assert.assertEquals(excelDataFetch(2,3),hp.selectElement("xpath", HomePageSelectors.currency2).getText().substring(0,3));
        System.out.println("Currency of Plan 3 is: "+hp.selectElement("xpath", HomePageSelectors.currency3).getText().substring(0,3));
        Assert.assertEquals(excelDataFetch(3,3),hp.selectElement("xpath", HomePageSelectors.currency3).getText().substring(0,3));
    }


}
