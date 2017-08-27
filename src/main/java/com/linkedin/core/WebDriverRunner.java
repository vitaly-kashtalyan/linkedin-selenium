package com.linkedin.core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;

public class WebDriverRunner {

    private static final Map<Long, WebDriver> WEB_DRIVER = new ConcurrentHashMap<>();

    @BeforeClass
    public static void setUp() {
        WEB_DRIVER.putIfAbsent(currentThread().getId(), WebDriverFactory.start());
    }

    @AfterClass
    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            WEB_DRIVER.remove(currentThread().getId());
        }
    }

    public static WebDriver getDriver() {
        return WEB_DRIVER.get(currentThread().getId());
    }
}
