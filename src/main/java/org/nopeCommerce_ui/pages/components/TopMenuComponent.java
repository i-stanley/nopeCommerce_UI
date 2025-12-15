package org.nopeCommerce_ui.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TopMenuComponent {
    private static final By MENU_TYPES = By.cssSelector("nav.menu-container .menu__item.menu-dropdown[role='menuitem'] > .menu__item-toggle > a.menu__link");
    private static final By SUBMENU_CONTAINER = By.cssSelector("div.menu__list-view");
    private static final By SUBMENU_ITEMS = By.cssSelector("div.menu__item > a.menu__link");
    private static final By MENU_TYPES_CONTAINER = By.cssSelector("nav.menu-container");

    public SelenideElement openMenu(String menu) {
        SelenideElement menuChosen = $$(MENU_TYPES)
                .findBy(exactText(menu))
                .shouldBe(Condition.visible);

        menuChosen.hover();
        return menuChosen;

    }

    public void openMenuAndSubMenu(String menu, String subMenu) {
        $(MENU_TYPES_CONTAINER).exists();
        SelenideElement chosenMenu = openMenu(menu);

        SelenideElement parentContainer = chosenMenu.parent().parent();

        ElementsCollection subMenuElements = parentContainer
                .shouldBe(visible)
                .$(SUBMENU_CONTAINER).shouldBe(visible)
                .$$(SUBMENU_ITEMS);

        subMenuElements.findBy(exactText(subMenu))
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();
    }
}
