package com.qa.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    protected abstract String getAppPackage();
    protected abstract String getAppActivity();

    @BeforeClass
    public void initialSetup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("deviceName", "Ismail's Phone");
        desiredCapabilities.setCapability("udid", "2269f779022d");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "11");
        desiredCapabilities.setCapability("appPackage", getAppPackage());
        desiredCapabilities.setCapability("appActivity", getAppActivity());

        // desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        // desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        ((CanRecordScreen) driver).startRecordingScreen();
    }

    @AfterClass
    public void tearDown() {
        stopRecording();
        driver.quit();
    }

    private void stopRecording() {
        String projectHomeDirectory = System.getProperty("user.dir");
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath = projectHomeDirectory + "/build/videos";
        File theDir = new File(destinationPath);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        String filePath = destinationPath + "/" + driver.getDeviceTime().replace(":", "_").replace("+", " ") + ".mp4";
        System.out.println("filePath : " + filePath);
        Path path = Paths.get(filePath);
        try {
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
