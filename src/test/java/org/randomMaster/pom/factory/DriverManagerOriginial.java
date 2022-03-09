package org.randomMaster.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.randomMaster.pom.constants.DriverType;

import java.util.Optional;

public class DriverManagerOriginial {
    public WebDriver initDriver(String browser) {
        WebDriver driver;
        String localBrowser = Optional.ofNullable(System.getProperty("browser")).orElse(browser);
        switch (DriverType.valueOf(localBrowser.toUpperCase())) {
            case CHROME:
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: " + browser);
        }
        return driver;
    }
}