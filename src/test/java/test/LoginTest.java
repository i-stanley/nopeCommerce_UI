package test;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.nopeCommerce_ui.base.TestBase;
import org.nopeCommerce_ui.config.Credentials;
import org.nopeCommerce_ui.pages.components.HeaderComponents;
import org.nopeCommerce_ui.pages.components.TopMenuComponent;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.constants.TestConstants;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    HeaderComponents headerComponents;
    TopMenuComponent topMenuComponent;

    @Tag("Smoke")
    @Test
    public void loginTest() {
        headerComponents = new HeaderComponents();

        headerComponents.clickLogInButton()
                .login(Credentials.email(), Credentials.password());
        Assertions.assertEquals(headerComponents.getLogOutText(), TestConstants.SUCCESSFUL_LOGIN_TEXT);
    }

    @Tag("Regression")
    @Test
    public void logoutTest() {
        loginPage = new LoginPage();
        headerComponents = new HeaderComponents();

        headerComponents.clickLogInButton();
        loginPage.login(Credentials.email(), Credentials.password());
        headerComponents.clickLogOut();

        Assertions.assertEquals(headerComponents.getLogInText(), TestConstants.SUCCESSFUL_LOG_OUT_TEXT);
    }
}
