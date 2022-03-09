package org.randomMaster.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.randomMaster.pom.factory.DriverManager;

public class FirefoxDriverManagerAbstract extends DriverManagerAbstract {
    @Override
    protected void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        driver =  new FirefoxDriver();
    }
}