package org.randomMaster.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        return new FirefoxDriver();
    }
}