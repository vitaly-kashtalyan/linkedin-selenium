package com.linkedin.core;


import com.linkedin.pages.LoginPage;
import org.junit.After;
import org.junit.Before;

import static com.linkedin.PageObjectSupplier.page;

public abstract class BaseTest extends WebDriverRunner {

    @Before
    public void logIn() {
        page(LoginPage.class).logIn();
    }

    @After
    public void logOut() {
        page(LoginPage.class).logOut();
    }
}