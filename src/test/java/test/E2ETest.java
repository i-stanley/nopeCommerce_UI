package test;

//import org.nopeCommerce_ui.base.LoggedInTestBase;

import com.codeborne.selenide.Selenide;
import org.nopeCommerce_ui.base.TestBase;
import org.nopeCommerce_ui.config.Credentials;
import org.nopeCommerce_ui.constants.TestConstants;
import org.nopeCommerce_ui.pages.LoginPage;
import org.nopeCommerce_ui.pages.ShoppingCartPage;
import org.nopeCommerce_ui.pages.components.HeaderComponents;
import org.nopeCommerce_ui.pages.components.TopMenuComponent;
import org.nopeCommerce_ui.pages.product.CategoryPage;
import org.nopeCommerce_ui.pages.product.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class E2ETest extends TestBase {

    @BeforeMethod
    public void setup() {
        HeaderComponents headerComponents = new HeaderComponents();
        headerComponents.clickLogInButton();
        LoginPage loginPage = new LoginPage();
        loginPage.login(Credentials.email(), Credentials.password());
    }

    @Test
    public void e2eTest() {
        TopMenuComponent topMenuComponent = new TopMenuComponent();
        CategoryPage categoryPage = new CategoryPage();

        topMenuComponent.openMenuAndSubMenu("Computers", "Notebooks");
        ProductDetailsPage productDetailsPage = categoryPage.clickOnFirstProduct();


        String cpuType = productDetailsPage.getCPUType();
        String memory = productDetailsPage.getMemory();
        String productName = productDetailsPage.getProductName();

        Selenide.back();
        categoryPage.selectCPUType(cpuType)
                .selectMemory(memory)
                .selectManufacturer(productName)
                .clickOnProduct();

        productDetailsPage.addToCart();

        Assert.assertEquals(productDetailsPage.isProductAddedToCart(),
                TestConstants.ADDED_TO_CART_MESSAGE, "Product was not added to cart");

        productDetailsPage.closeAddedToCartMessage();
        HeaderComponents headerComponents = new HeaderComponents();
        headerComponents.hoverShoppingCardLabel();
        headerComponents.hoverShoppingCardButton();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.keepOnlyChosenProduct(productName);
        List<String> products = shoppingCartPage.getProducts();

        Assert.assertEquals(products.size(), 1, "Cart didn't contain only 1 product");
        Assert.assertEquals(products.get(0), productName, "Product in cart is not the chosen one");


    }
}
