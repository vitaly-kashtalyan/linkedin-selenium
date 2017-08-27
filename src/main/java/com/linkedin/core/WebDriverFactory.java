package com.linkedin.core;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final String TARGET_DRIVERS = "target/drivers";

    private WebDriverFactory() {
        throw new IllegalAccessError("Illegal access to private constructor");
    }

    public static WebDriver start() {
        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", getPathDriver());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setCapability(ChromeOptions.CAPABILITY, options);
        chrome.setCapability("acceptSslCerts", true);

        driver = new ChromeDriver(chrome);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static String currentBit() {
        String osArch = System.getProperty("os.arch");
        String sunArch = System.getProperty("sun.arch.data.model");
        return osArch != null && osArch.endsWith("64") || sunArch.contains("64") ? "64" : "32";
    }

    private static String getPathDriver() {
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + TARGET_DRIVERS + File.separator;
        String platform = currentPlatform();
        String bit = currentBit();
        String ext = platform.equals(Platform.WINDOWS.toString()) ? ".exe" : "";
        bit = platform.equals(Platform.WINDOWS.toString()) ? "32" : bit;
        return path + "chromedriver-" + platform.toLowerCase() + "-" + bit + "bit" + ext;
    }

    private static String currentPlatform() {
        if (Platform.getCurrent().is(Platform.MAC)) {
            return Platform.MAC.toString();
        } else if (Platform.getCurrent().is(Platform.WINDOWS) || Platform.getCurrent().is(Platform.WIN10)) {
            return Platform.WINDOWS.toString();
        } else if (Platform.getCurrent().is(Platform.LINUX)) {
            return Platform.LINUX.toString();
        }
        throw new IllegalStateException("Unsupported OS, OS is neither MAC, WINDOWS nor LINUX");
    }
}
