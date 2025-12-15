package org.nopeCommerce_ui.pages.product;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage {
//    on https://demo.nopcommerce.com/notebooks
//    go to first product, extract from the product cpu type, memory, manufacturer
//    and put it in Filter by attributes

//    private static final By PRODUCTS = By.cssSelector(".item-box h2:nth-of-type(1)");
    private static final By FIRST_PRODUCT_NAME = By.cssSelector(".item-box:nth-of-type(1) .product-title a");
    private static final String CPU_CHECKBOX = "//label[contains(., '%s')]/preceding-sibling::input";
    private static final String MEMORY_CHECKBOX = "//label[contains(., '%s')]/preceding-sibling::input";
    private static final String MANUFACTURER_CHECKBOX = "//label[contains(., '%s')]/preceding-sibling::input";

    public ProductDetailsPage clickOnFirstProduct() {
        $(FIRST_PRODUCT_NAME).should(visible).click();
        return new ProductDetailsPage();
    }

    public CategoryPage selectCPUType(String cpuName){
        String locator = String.format(CPU_CHECKBOX, cpuName);
        $(By.xpath(locator)).click();
        return new CategoryPage();
    }

    public CategoryPage selectMemory(String memoryName){
        String locator = String.format(MEMORY_CHECKBOX, memoryName);
        $(By.xpath(locator)).click();
        return new CategoryPage();
    }


    public CategoryPage selectManufacturer(String manufacturerName){
        String firstWord = manufacturerName.split(" ")[0];
        String locator = String.format(MANUFACTURER_CHECKBOX, firstWord);
        $(By.xpath(locator)).click();
        return new CategoryPage();
    }

    public ProductDetailsPage clickOnProduct() {
        $(FIRST_PRODUCT_NAME).should(visible).click();
        return new ProductDetailsPage();
    }
}
