package test;

import org.nopeCommerce_ui.base.TestBase;
import org.nopeCommerce_ui.config.Credentials;
import org.nopeCommerce_ui.constants.Currencies;
import org.nopeCommerce_ui.constants.TestConstants;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.pages.components.HeaderComponents;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderComponentsTest extends TestBase {
    HeaderComponents headerComponents;

    @Test(groups = {TestConstants.REGRESSION})
    public void changeCurrencyFromUsdToEuroTest() {
        headerComponents = new HeaderComponents();

        String actualCurrency = headerComponents
                .chooseCurrency(Currencies.EURO.getName());

        Assert.assertEquals(actualCurrency, Currencies.EURO.getName());
    }

    @Test(groups = {TestConstants.REGRESSION})
    public void changeCurrencyFromEuroToUsdTest() {
        headerComponents = new HeaderComponents();

        headerComponents.chooseCurrency(Currencies.EURO.getName());
        String actualCurrency = headerComponents.chooseCurrency(Currencies.US_DOLLAR.getName());

        Assert.assertEquals(actualCurrency, Currencies.US_DOLLAR.getName());
    }

//    Test Topmenu sunt 6
}
