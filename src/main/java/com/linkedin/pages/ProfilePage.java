package com.linkedin.pages;

import com.linkedin.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    private static final By CONNECT_BUTTON = By.xpath("//div[@class='pv-top-card-section__body']/descendant::button/span[text()='Connect']");

    @Step
    public ProfilePage loadProfile(String url) {
        loadUrl(url);
        return this;
    }

    @Step
    public ProfilePage clickConnectButton() {
        findElement(By.xpath("//div[@class='pv-top-card-section__body']/descendant::button/span[text()='Connect']")).click();
        return this;
    }

    @Step
    public Boolean isConnectButton() {
        return !findElements(CONNECT_BUTTON).isEmpty();
    }

    @Step
    public void clickSendNow() {
        click(By.xpath("//button[text()='Send now']"));
    }

    @Step
    public String getSuccessText() {
        return findElement(By.xpath("//div/li-icon[@type='success-pebble-icon']/../span")).getText();
    }

    @Step
    public WebElement getName() {
        return findElement(By.xpath("//h1[contains(@class,'pv-top-card-section__name')]"));
    }
}