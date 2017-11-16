package com.linkedin.core;


import io.qameta.allure.Attachment;
import org.openqa.selenium.NoSuchWindowException;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.linkedin.core.WebDriverRunner.getDriver;

class AttachmentAllure {

    private AttachmentAllure() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] saveScreenshot() throws IOException {
        BufferedImage image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
                .takeScreenshot(getDriver()).getImage();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();
        return bytes;
    }

    @Attachment(value = "Page source", type = "text/html")
    static String savePageSource() {
        try {
            return getDriver().getPageSource();
        } catch (NoSuchWindowException e) {
            System.out.println("Window not found. Error getPageSource(): " + e.getMessage());
            return null;
        }
    }
}