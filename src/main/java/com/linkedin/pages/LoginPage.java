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
        type(By.id("session_key-login"), linkedInProperty.getUsername());
        type(By.id("session_password-login"), linkedInProperty.getPassword());
        click(By.id("btn-primary"));
        return page(HomePage.class);
    }

    @Step
    public void logOut() {
        click(By.id("nav-settings__dropdown-trigger"));
        waitVisibilityOfElementLocated(By.id("nav-settings__dropdown-options"));
        click(By.xpath("//a[@data-control-name='nav.settings_signout']"));
    }
}
