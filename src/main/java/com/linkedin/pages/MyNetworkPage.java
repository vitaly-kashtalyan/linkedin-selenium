package com.linkedin.pages;

import com.linkedin.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyNetworkPage extends BasePage {

    public List<WebElement> getConnectButtons() {
        waitLoadPeople();
        return findElements(By.xpath("//button[@data-control-name='invite']"));
    }

    public void waitLoadPeople() {
        waitInvisibilityOfElementLocated(By.cssSelector("span.blue.loader.svg-icon-wrap"));
    }

    public void waitAlert() {
        waitVisibilityOfElementLocated(By.xpath("//div[@role='alert']"));
        waitInvisibilityOfElementLocated(By.xpath("//div[@role='alert']"));
    }
}
