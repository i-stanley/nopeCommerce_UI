package org.nopeCommerce_ui.pages.components;

import com.codeborne.selenide.SelenideElement;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.pages.ShoppingCartPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponents {
    private static final By LOG_IN_BUTTON = By.cssSelector(".ico-login");
    private static final By LOG_OUT_BUTTON = By.cssSelector(".ico-logout");
    private static final By CURRENCY = By.id("customerCurrency");
    private static final By SHOPPING_CART_LABEL = By.cssSelector("#topcartlink a");
    private static final By SHOPPING_CART_BUTTON = By.xpath("//*[text()='Go to cart']");


    public LoginPage clickLogInButton() {
        $(LOG_IN_BUTTON).click();
        return new LoginPage();
    }

    public String getLogInText() {
        return $(LOG_IN_BUTTON).getText();
    }

    public void clickLogOut() {
        $(LOG_OUT_BUTTON).click();
    }

    public String getLogOutText() {
        return $(LOG_OUT_BUTTON).getText();

    }

    public String chooseCurrency(String currency) {
        SelenideElement dropdown = $(CURRENCY);
        dropdown.selectOption(currency);
        return dropdown.getSelectedOption().getText();
    }

    public ShoppingCartPage hoverShoppingCardLabel(){
        $(SHOPPING_CART_LABEL).hover();
        return new ShoppingCartPage();
    }

    public ShoppingCartPage hoverShoppingCardButton(){
        $(SHOPPING_CART_BUTTON).hover();
        return new ShoppingCartPage();
    }
}
