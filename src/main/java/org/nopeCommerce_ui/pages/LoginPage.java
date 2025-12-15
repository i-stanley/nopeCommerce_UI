package org.nopeCommerce_ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
//    private static final By LOGOUT_BUTTON = By.cssSelector(".ico-logout");
    private static final By EMAIL_INPUT = By.cssSelector("#Email");
    private static final By PASSWORD_INPUT = By.xpath("//*[@id='Password']");
    private static final By LOGIN_CONFIRM = By.cssSelector("[class='button-1 login-button']");

    public void login(String email, String password) {
        $(EMAIL_INPUT).setValue(email);
        $(PASSWORD_INPUT).setValue(password);
        $(LOGIN_CONFIRM).click();
    }

}
