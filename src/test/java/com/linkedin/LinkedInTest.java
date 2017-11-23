package com.linkedin;

import com.linkedin.core.BaseTest;
import com.linkedin.pages.HomePage;
import com.linkedin.pages.MyNetworkPage;
import com.linkedin.pages.ProfilePage;
import com.linkedin.pages.SearchPage;
import com.linkedin.property.SearchProperty;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.qatools.properties.PropertyLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.linkedin.FileTest.readContactsToFile;
import static com.linkedin.PageObjectSupplier.page;
import static org.junit.Assert.assertEquals;

@Epic("LinkedIn")
@Feature("Automated adding contacts")
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

    @Test
    public void search() {
        Set<String> listUrlOfContacts = getListUrlOfContacts();
        sendInvites(listUrlOfContacts);
    }

    @Test
    public void sendInvitesFromFile() {
        Set<String> listUrlOfContacts = readContactsToFile();
        sendInvites(listUrlOfContacts);
    }

    static Set<String> getListUrlOfContacts() {
        SearchProperty searchProperty = PropertyLoader.newInstance().populate(SearchProperty.class);

        page(HomePage.class).typeSearch(searchProperty.getSearchRequest()).clickLabel2nd();
        if (!searchProperty.getSearchCountry().equals("")) {
            page(SearchPage.class).typeCountry(searchProperty.getSearchCountry());
        }

        Set<String> contacts = new HashSet<>();
        do {
            if (page(SearchPage.class).isNoResultsFound()) {
                break;
            }

            List<String> links = page(SearchPage.class).pageDown().getContactLinks();
            contacts.addAll(links);
            System.out.println(String.join(System.getProperty("line.separator"), links));
            page(SearchPage.class).clickNextIfExists();
        } while (page(SearchPage.class).isNext() && contacts.size() < searchProperty.getCountProfile());

        return contacts;
    }

    private void sendInvites(final Set<String> listUrl) {
        for (String url : listUrl) {
            String name = page(ProfilePage.class).loadProfile(url).getName().getText().split("\\s+")[0];
            getDriver().navigate().refresh();

            if (page(ProfilePage.class).isConnectButton()) {
                page(ProfilePage.class).clickConnectButton().clickSendNow();

                assertEquals("Text should be",
                        String.format("Your invitation to %s was sent.", name),
                        page(ProfilePage.class).getSuccessText());

                System.out.println(url + " - done");
            }
        }
    }
}
