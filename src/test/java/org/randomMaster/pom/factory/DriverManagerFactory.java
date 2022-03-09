package org.randomMaster.pom.factory;

import org.randomMaster.pom.constants.DriverType;

public final class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriverManager();
            case FIREFOX:
                return new FirefoxDriverManager();
            default:
                throw new IllegalStateException("Invalid browser name: " + driverType);
        }
    }
}