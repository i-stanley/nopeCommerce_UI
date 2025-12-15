package org.nopeCommerce_ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingCartPage {

    private static final By PRODUCTS_ON_CART = By.cssSelector(".product-name");
    private static final String ROW_WITH_PRODUCT_NAME = "//a[@class='product-name' and contains(.,'%s')]/ancestor::tr";
    private static final By REMOVE_FROM_CART = By.cssSelector("button.remove-btn");
    private static final By UPDATE_CART = By.xpath("//button[normalize-space()='Update shopping cart']");
    private static final By TERMS_OF_SERVICE = By.cssSelector("input[id=termsofservice]");
    private static final By CHECKOUT_BUTTON = By.cssSelector("button[id=checkout]");

    public List<String> getProducts() {
        return $$(PRODUCTS_ON_CART).texts();
    }

    private void removeProduct(String productName) {
        String stringXpath = String.format(ROW_WITH_PRODUCT_NAME, productName);
        SelenideElement locator = $(By.xpath(stringXpath));
        locator.$(REMOVE_FROM_CART).click();
        locator.should(disappear);
    }

    public void keepOnlyChosenProduct(String productName) {
        getProducts().stream()
                .filter(p -> !p.equals(productName))
                .forEach(this::removeProduct);
    }

    public ShoppingCartPage checkTermsOfService(){
        $(TERMS_OF_SERVICE).click();
        return this;
    }

    public void checkout(){
        $(CHECKOUT_BUTTON).click();
    }
}
