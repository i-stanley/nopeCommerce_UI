package org.nopeCommerce_ui.base;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.nopeCommerce_ui.core.DriverFactory;

public class TestBase {

    @BeforeEach
    public void setUp() {
        DriverFactory.configure();
        Selenide.open("https://demo.nopcommerce.com/");

    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
