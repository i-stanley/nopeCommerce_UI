package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.nopeCommerce_ui.base.TestBase;
import org.nopeCommerce_ui.config.Credentials;
import org.nopeCommerce_ui.constants.Currencies;
import org.nopeCommerce_ui.constants.TestConstants;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.pages.components.HeaderComponents;

public class HeaderComponentsTest extends TestBase {
    HeaderComponents headerComponents;

    @Tag("Regression")
    @Test
    public void changeCurrencyFromUsdToEuroTest() {
        headerComponents = new HeaderComponents();

        String actualCurrency = headerComponents
                .chooseCurrency(Currencies.EURO.getName());

        Assertions.assertEquals(actualCurrency, Currencies.EURO.getName());
    }

    @Tag("Regression")
    @Test
    public void changeCurrencyFromEuroToUsdTest() {
        headerComponents = new HeaderComponents();

        headerComponents.chooseCurrency(Currencies.EURO.getName());
        String actualCurrency = headerComponents.chooseCurrency(Currencies.US_DOLLAR.getName());

        Assertions.assertEquals(actualCurrency, Currencies.US_DOLLAR.getName());
    }

//    Test Topmenu sunt 6
}
