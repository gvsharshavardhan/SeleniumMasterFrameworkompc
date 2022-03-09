package org.randomMaster.pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerAbstract {

    protected WebDriver driver;

    protected abstract void startDriver();

    public WebDriver getDriver() {
        if (driver == null) {
            startDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}