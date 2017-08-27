package com.linkedin.pages;

import com.linkedin.core.BasePage;
import org.openqa.selenium.By;

import static com.linkedin.PageObjectSupplier.page;

public class HomePage extends BasePage {

    public MyNetworkPage clickMyNetwork() {
        click(By.xpath("//li[@id='mynetwork-nav-item']/a"));
        return page(MyNetworkPage.class);
    }
}
