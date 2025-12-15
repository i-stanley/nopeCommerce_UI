package org.nopeCommerce_ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.nopeCommerce_ui.DriverFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void setUp() {
        DriverFactory.configure();
        Selenide.open("https://demo.nopcommerce.com/");

    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
