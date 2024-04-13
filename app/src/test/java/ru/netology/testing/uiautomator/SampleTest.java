// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client
package ru.netology.testing.uiautomator;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setUp() {
        var options = new DesiredCapabilities();
        options.setCapability("platformName", "Android");
        options.setCapability("appium:deviceName", "Some name");
        options.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        options.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        options.setCapability("appium:automationName", "uiautomator2");
        options.setCapability("appium:ensureWebviewsHavePages", true);
        options.setCapability("appium:nativeWebScreenshot", true);
        options.setCapability("appium:newCommandTimeout", 3600);
        options.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(this.getUrl(), options);
    }

    @Test
    public void EmptyStringTest() {
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("   ");
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        Assertions.assertEquals("Hello UiAutomator!", el3.getText());
    }

    @Test
    public void TextInActivityTest() {
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("Netology");
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el3.isDisplayed();
        Assertions.assertEquals("Netology", el3.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

