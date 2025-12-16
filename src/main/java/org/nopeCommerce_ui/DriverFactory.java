package org.nopeCommerce_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;


public class DriverFactory {

    public static void configure() {
        String browser = System.getProperty("browser", "chrome");
        String headless = System.getProperty("headless", "false");


        Configuration.browser = browser;
        Configuration.headless = Boolean.parseBoolean(headless);
        Configuration.timeout = 8000;
//        Configuration.reportsFolder = "build/reports/tests";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "normal";
        Configuration.screenshots = true;


        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .savePageSource(true)
                        .screenshots(true)
        );
        // Remote execution ready (Selenoid / Selenium Grid)
        String remote = System.getProperty("remote", "");
        if (!remote.isEmpty()) {
            Configuration.remote = remote;
        }
    }
}
