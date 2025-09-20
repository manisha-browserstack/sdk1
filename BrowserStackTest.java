package com.example.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        // Get BrowserStack credentials from environment variables
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        // Fallback (use only if env variables are not set)
        if (username == null || accessKey == null) {
            username = "manishasardar_HBC0EW";   // 🔹 replace with your BrowserStack Username
            accessKey = "9EPg5LWmsfP6VNDNXBjp"; // 🔹 replace with your BrowserStack Access Key
        }

        // Define capabilities
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "120.0");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("projectName", "My TestNG Project");
        bstackOptions.put("buildName", "Build 1");
        bstackOptions.put("sessionName", "Sample Test");
        caps.setCapability("bstack:options", bstackOptions);

        // Connect to BrowserStack RemoteWebDriver
        driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),
                caps
        );
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        System.out.println("Title is: " + driver.getTitle());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
