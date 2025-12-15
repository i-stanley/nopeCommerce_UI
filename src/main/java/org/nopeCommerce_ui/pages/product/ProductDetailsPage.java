package org.nopeCommerce_ui.pages.product;

import org.nopeCommerce_ui.pages.ShoppingCartPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage {
    private static final By CPU_TYPE  = By.cssSelector("tbody tr:nth-child(3) td:nth-child(2)");
    private static final By MEMORY  = By.cssSelector(".even .spec-value");
    private static final By PRODUCT_NAME  = By.cssSelector(".product-name h1");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("[id^='add-to-cart-button']");
    private static final By ADDED_TO_CART = By.cssSelector(".bar-notification.success p");
    private static final By CLOSE_ADDED_TO_CART_MESSAGE = By.cssSelector(".close");


    public String getProductName(){
        return $(PRODUCT_NAME).getText();
    }

    public String getMemory(){
        return $(MEMORY).getText();
    }

    public String getCPUType(){
        return $(CPU_TYPE).should(visible).getText();
    }

    public void addToCart(){
        $(ADD_TO_CART_BUTTON).shouldBe(visible).click();
    }

    public String isProductAddedToCart(){
        return $(ADDED_TO_CART).getText();
    }

    public ProductDetailsPage closeAddedToCartMessage(){
        $(CLOSE_ADDED_TO_CART_MESSAGE).click();
        return this;
    }
}
