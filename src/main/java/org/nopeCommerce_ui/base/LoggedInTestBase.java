//package org.nopeCommerce_ui.base;
//
//import org.nopeCommerce_ui.config.Credentials;
//import org.nopeCommerce_ui.pages.LoginPage;
//import org.nopeCommerce_ui.pages.components.HeaderComponents;
//import org.nopeCommerce_ui.pages.components.TopMenuComponent;
//import org.testng.annotations.BeforeMethod;
//
//public class LoggedInTestBase extends TestBase {
//
//    @BeforeMethod
//    public void init() {
//        super.setUp();
//
//        LoginPage loginPage = new LoginPage();
//        HeaderComponents headerComponents = new HeaderComponents();
//        TopMenuComponent topMenuComponent = new TopMenuComponent();
//
//        loginPage.login(Credentials.email(), Credentials.password());
//        topMenuComponent.openMenuAndSubMenu("Computers","Notebooks");
//    }
//}
