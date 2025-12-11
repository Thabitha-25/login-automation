package com.login.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public static Properties prop;

    public void initSetup() {
        prop = ConfigReader.initProperties();

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath"));
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        // 🔹 Add implicit wait (global wait)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("url"));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

