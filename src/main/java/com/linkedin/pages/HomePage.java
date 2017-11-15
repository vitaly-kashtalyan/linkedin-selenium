package com.linkedin.pages;

import com.linkedin.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.linkedin.PageObjectSupplier.page;

public class HomePage extends BasePage {

    @Step
    public MyNetworkPage clickMyNetwork() {
        click(By.xpath("//li[@id='mynetwork-nav-item']/a"));
        return page(MyNetworkPage.class);
    }

    @Step
    public SearchPage typeSearch(String search) {
        type(By.xpath("//div[@id='nav-typeahead-wormhole']/descendant::input"), search);
        click(By.xpath("//button[@data-control-name='nav.search_button']"));
        return page(SearchPage.class);
    }
}
