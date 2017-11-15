package com.linkedin.pages;

import com.linkedin.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyNetworkPage extends BasePage {

    @Step
    public List<WebElement> getConnectButtons() {
        waitLoadPeople();
        return findElements(By.xpath("//button[@data-control-name='invite']"));
    }

    @Step
    public void waitLoadPeople() {
        waitInvisibilityOfElementLocated(By.cssSelector("span.blue.loader.svg-icon-wrap"));
    }

    @Step
    public void waitAlert() {
        waitVisibilityOfElementLocated(By.xpath("//div[@role='alert']"));
        waitInvisibilityOfElementLocated(By.xpath("//div[@role='alert']"));
    }
}
