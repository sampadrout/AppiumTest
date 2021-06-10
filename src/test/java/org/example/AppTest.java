package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class AppTest
{
    private String ANDROID_APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.apk";
    private String IOS_APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.app.zip";

    private AppiumDriver driver;

    public static AppiumDriverLocalService appium;

    @BeforeTest
    public void setup() {

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);

        appium = AppiumDriverLocalService.buildService(builder);
        appium.start();

        DesiredCapabilities caps = new DesiredCapabilities();
/*      caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "14.5");
        caps.setCapability("deviceName", "iPhone 8");
        caps.setCapability("automationName", "XCUITest");*/
        caps.setCapability("platformName", "android");
        caps.setCapability("platformVersion", "10");
//        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("deviceName", "pixel_3");
        caps.setCapability("automationName", "Appium");

        caps.setCapability("app", ANDROID_APP); // change the app as per caps

        driver = new IOSDriver<MobileElement>(appium.getUrl(), caps);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        Assert.assertTrue( true );
    }

    @AfterTest
    public void teardown() {
        driver.quit();
        appium.stop();
    }
}
