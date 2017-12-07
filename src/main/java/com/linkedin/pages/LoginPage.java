package com.linkedin.pages;

import com.linkedin.core.BasePage;
import com.linkedin.property.LinkedInProperty;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.qatools.properties.PropertyLoader;

import static com.linkedin.PageObjectSupplier.page;

public class LoginPage extends BasePage {

    @Step
    public HomePage logIn() {
        LinkedInProperty linkedInProperty = PropertyLoader.newInstance()
                .populate(LinkedInProperty.class);

        loadUrl(linkedInProperty.getUrl());
        type(By.id("login-email"), linkedInProperty.getUsername());
        type(By.id("login-password"), linkedInProperty.getPassword());
        click(By.id("login-submit"));
        waitVisibilityOfElementLocated(By.id("feed-tab-icon"));
        return page(HomePage.class);
    }

    @Step
    public void logOut() {
        click(By.id("nav-settings__dropdown-trigger"));
        waitVisibilityOfElementLocated(By.id("nav-settings__dropdown-options"));
        click(By.xpath("//a[@data-control-name='nav.settings_signout']"));
    }
}
