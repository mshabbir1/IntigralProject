package TestCases;

import Selectors.HomePageSelectors;
import Utilities.Helpers;
import Utilities.Initialization;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllCountriesSelection extends Initialization {

    /* This class has been used to reduce lines of Code
 by utilizing parameterized method to run all test cases using a single method  */

    /*  Dynamic xPath Technique has been used to select Web elements */

    Helpers hp = new Helpers();
    @Description("Accessing Project Webiste")
    @Test(priority = 1)
    public void accessIntigralWebsite() throws IOException {
        browser.get(propertiesData("projectUrl"));
        browser.manage().window().maximize();
    }

    @Description("Validating Plan, Price and Currency for Bahrain")
    @Test(priority = 2)
    public void validate_Bahrain() throws IOException {
        executeScenario("bh","Bahrain", 1,2,3);
    }

    @Description("Validating Plan, Price and Currency for Saudi Arabia")
    @Test(priority = 3)
    public void validate_Saudi_Arabia() throws IOException {
        executeScenario("sa","Saudi Arabia", 5,6,7);
    }

    @Description("Validating Plan, Price and Currency for Kuwait")
    @Test(priority = 4)
    public void validate_Kuwait() throws IOException {
        executeScenario("kw","Kuwait", 9,10,11);
    }


    public void executeScenario(String countryCode, String countryName, int columnRank1, int columnRank2, int columnRank3 ) throws IOException {

        hp.selectElement("id", HomePageSelectors.countrySelection).click();
        hp.selectElement("xpath", HomePageSelectors.countryName(countryCode)).click();
                for (int k=1 ; k<=3 ;k++) {
                    Assert.assertEquals(excelDataFetch(k,(columnRank1)),hp.selectElement("xpath", HomePageSelectors.plan+"["+k+"]").getText());
                    System.out.println("Plan " + k + " for "+countryName+" is: " + hp.selectElement("xpath", HomePageSelectors.plan+"["+k+"]").getText());
                    Assert.assertEquals(excelDataFetch(k,(columnRank2)),hp.selectElement("xpath", HomePageSelectors.planPrice+"["+k+"]/b").getText());
                    System.out.println("Price of Plan "+k+" for "+countryName+" is: "+hp.selectElement("xpath", HomePageSelectors.planPrice+"["+k+"]/b").getText());
                    if (countryCode.equals("sa")) {
                        Assert.assertEquals(excelDataFetch(k,(columnRank3)),hp.selectElement("xpath", HomePageSelectors.currency+"["+k+"]/i[2]").getText().substring(0,3));
                        System.out.println("Currency of Plan "+k+" for "+countryName+" is: "+hp.selectElement("xpath", HomePageSelectors.currency+"["+k+"]/i[2]").getText().substring(0,3));
                    } else {
                    Assert.assertEquals(excelDataFetch(k,(columnRank3)),hp.selectElement("xpath", HomePageSelectors.currency+"["+k+"]/i").getText().substring(0,3));
                    System.out.println("Currency of Plan "+k+" for "+countryName+" is: "+hp.selectElement("xpath", HomePageSelectors.currency+"["+k+"]/i").getText().substring(0,3));
                    }
                    System.out.println("                 --                  ");
                }





    }
}
