package com.linkedin;

import com.linkedin.core.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.linkedin.LinkedInTest.getListUrlOfContacts;

@Epic("LinkedIn")
@Feature("Automated adding contacts")
public class FileTest extends BaseTest {

    private static final String PATH_CONTACTS = System.getProperty("user.home") + File.separator + "in_contacts.txt";

    @Test
    public void writeContactsToFile() {
        Set<String> listUrlOfContacts = getListUrlOfContacts();

        if (!listUrlOfContacts.isEmpty()) {
            try {
                FileUtils.writeLines(new File(PATH_CONTACTS), listUrlOfContacts, true);
                System.out.println(PATH_CONTACTS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static Set<String> readContactsToFile() {
        List<String> listUrlOfContacts = null;
        try {
            listUrlOfContacts = Files.readAllLines(Paths.get(PATH_CONTACTS));
            System.out.println(PATH_CONTACTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>(listUrlOfContacts);
    }
}
