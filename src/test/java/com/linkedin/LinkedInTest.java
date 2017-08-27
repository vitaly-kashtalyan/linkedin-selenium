package com.linkedin;

import com.linkedin.core.BaseTest;
import com.linkedin.pages.HomePage;
import com.linkedin.pages.MyNetworkPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.linkedin.PageObjectSupplier.page;
import static org.junit.Assert.assertEquals;


public class LinkedInTest extends BaseTest {


    @Test
    public void myNetwork() {
        List<WebElement> elements = page(HomePage.class).clickMyNetwork().getConnectButtons();

        for (WebElement element : elements) {
            element.click();
            page(MyNetworkPage.class).waitAlert();
            assertEquals("Contact should be", false, element.isDisplayed());
        }
    }

}
