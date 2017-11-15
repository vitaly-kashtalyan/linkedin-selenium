package com.linkedin.pages;

import com.linkedin.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    @Step
    private void waitLoadSearchResults() {
        waitInvisibilityOfElementLocated(By.xpath("//div[contains(@class,'search-is-loading')]"));
    }

    @Step
    public SearchPage clickLabel2nd() {
        click(By.xpath("//label[@for='sf-facetNetwork-S']"));
        waitLoadSearchResults();
        return this;
    }

    @Step
    public SearchPage pageDown() {
        WebElement element = findElement(By.id("extended-nav"));
        for (int i = 0; i < 20; i++) {
            element.sendKeys(Keys.END);
        }
        return this;
    }

    @Step
    public List<String> getContactLinks() {
        return findElements(By.xpath("//div[@class='search-result__wrapper']/descendant::button[text()='Connect']/../../../div[@class='search-result__image-wrapper']/a[@data-control-name='search_srp_result']"))
                .stream()
                .map(w -> w.getAttribute("href"))
                .collect(Collectors.toList());
    }

    @Step
    public Boolean isNext() {
        return !findElements(By.cssSelector("button.next")).isEmpty();
    }

    @Step
    public SearchPage clickNextIfExists() {
        if (isNext()) {
            click(By.cssSelector("button.next"));
        }
        waitLoadSearchResults();
        return this;
    }

    @Step
    public Boolean isNoResultsFound() {
        return !findElements(By.xpath("//h1[text()='No results found.']")).isEmpty();
    }
}
