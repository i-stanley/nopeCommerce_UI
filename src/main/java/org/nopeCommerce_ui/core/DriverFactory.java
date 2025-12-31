package org.nopeCommerce_ui.core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.UUID;

public class DriverFactory {

    public static void configure() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.timeout = 8000;
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "normal";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.webdriverLogsEnabled = true;

        String remote = System.getProperty("remote", "");
        if (remote != null && !remote.isBlank()) {
            Configuration.remote = remote;

            String videoName = UUID.randomUUID() + ".mp4";
            VideoContext.setVideoName(videoName);

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("enableVideo", true);
            caps.setCapability("videoName", videoName);
            caps.setCapability("videoScreenSize", "1920x1080");

            Configuration.browserCapabilities = caps;
        }
    }
}
