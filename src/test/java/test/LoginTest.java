package test;

import lombok.extern.java.Log;
import org.nopeCommerce_ui.base.TestBase;
import org.nopeCommerce_ui.config.Credentials;
import org.nopeCommerce_ui.pages.components.HeaderComponents;
import org.nopeCommerce_ui.pages.components.TopMenuComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.constants.TestConstants;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    HeaderComponents headerComponents;
    TopMenuComponent topMenuComponent;


    @Test(groups = {TestConstants.SMOKE})
    public void loginTest() {
        headerComponents = new HeaderComponents();

        headerComponents.clickLogInButton()
                .login(Credentials.email(), Credentials.password());
        Assert.assertEquals(headerComponents.getLogOutText(), TestConstants.SUCCESSFUL_LOGIN_TEXT);
    }

    @Test(groups = {TestConstants.REGRESSION})
    public void logoutTest() {
        loginPage = new LoginPage();
        headerComponents = new HeaderComponents();

        headerComponents.clickLogInButton();
        loginPage.login(Credentials.email(), Credentials.password());
        headerComponents.clickLogOut();

        Assert.assertEquals(headerComponents.getLogInText(), TestConstants.SUCCESSFUL_LOG_OUT_TEXT);
    }
}
