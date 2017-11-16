package com.linkedin.core;

import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;

import static com.linkedin.core.AttachmentAllure.savePageSource;
import static com.linkedin.core.AttachmentAllure.saveScreenshot;

public class AllureJunit4Custom extends AllureJunit4 {

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        saveScreenshot();
        savePageSource();
    }
}