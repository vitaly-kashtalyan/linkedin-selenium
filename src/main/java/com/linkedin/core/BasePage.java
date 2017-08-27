package com.linkedin.core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.linkedin.core.WebDriverRunner.getDriver;


public abstract class BasePage {
    public static final long WAIT_DRIVER = 8;
    public static final long DEFAULT_SLEEP_TIMEOUT = 250L;
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage() {
        this.driver = getDriver();
        if (driver != null) {
            wait = new WebDriverWait(driver, WAIT_DRIVER, DEFAULT_SLEEP_TIMEOUT);
        }
    }

    public void loadUrl(final String url) {
        if (url != null) {
            driver.navigate().to(url);
        }
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public void waitInvisibilityOfElementLocated(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitVisibilityOfElementLocated(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
}
