package com.linkedin.pages;

import com.linkedin.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {


    public ProfilePage loadProfile(String url) {
        loadUrl(url);
        return this;
    }

    public ProfilePage clickConnectIfExists() {
        if (!findElements(By.xpath("//div[@class='pv-top-card-section__body']/descendant::button/span[text()='Connect']")).isEmpty()) {
            findElement(By.xpath("//div[@class='pv-top-card-section__body']/descendant::button/span[text()='Connect']")).click();
        }
        return this;
    }

    public void clickSendNow() {
        click(By.xpath("//button[text()='Send now']"));
    }

    public String getSuccessText() {
        return findElement(By.xpath("//div/li-icon[@type='success-pebble-icon']/../span")).getText();
    }

    public WebElement getName() {
        return findElement(By.xpath("//h1[contains(@class,'pv-top-card-section__name')]"));
    }
}