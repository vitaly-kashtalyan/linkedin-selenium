package com.linkedin.pages;

import com.linkedin.core.BasePage;
import com.linkedin.property.LinkedInProperty;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;

import static com.linkedin.PageObjectSupplier.page;

public class LoginPage extends BasePage {

    public HomePage logIn() {
        LinkedInProperty linkedInProperty = ConfigFactory.create(LinkedInProperty.class);

        loadUrl(linkedInProperty.url());
        type(By.id("session_key-login"), linkedInProperty.username());
        type(By.id("session_password-login"), linkedInProperty.password());
        click(By.id("btn-primary"));
        return page(HomePage.class);
    }

    public void logOut() {
        click(By.id("nav-settings__dropdown-trigger"));
        waitVisibilityOfElementLocated(By.id("nav-settings__dropdown-options"));
        click(By.xpath("//a[@data-control-name='nav.settings_signout']"));
    }
}
