package Selectors;

public class HomePageSelectors {

    public static String countrySelection = "country-btn";

    public static String countryName (String code) {

        return "//*[@id=\""+code+"-contry-lable\"]";
    }
    public static String plan = "(//*[@class='plan-title'])";
    public static String planPrice = "(//*[@class='price'])";
    public static String currency = "(//*[@class='price'])";
    public static String countryBahrain = "//*[@id=\"bh-contry-flag\"]/img";
    public static String countrySaudia = "//*[@id=\"sa-contry-flag\"]/img";
    public static String countryKuwait = "//*[@id=\"kw-contry-flag\"]/img";
    public static String plan1 = "(//*[@class='plan-title'])[1]";
    public static String plan2 = "(//*[@class='plan-title'])[2]";
    public static String plan3 = "(//*[@class='plan-title'])[3]";
    public static String planPrice1 = "(//*[@class='price'])[1]/b";
    public static String planPrice2 = "(//*[@class='price'])[2]/b";
    public static String planPrice3 = "(//*[@class='price'])[3]/b";

    public static String currency1 = "(//*[@class='price'])[1]/i";
    public static String currency2 = "(//*[@class='price'])[2]/i";
    public static String currency3 = "(//*[@class='price'])[3]/i";



}
