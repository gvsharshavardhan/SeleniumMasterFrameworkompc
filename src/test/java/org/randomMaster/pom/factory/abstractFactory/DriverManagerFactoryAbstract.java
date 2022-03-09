package org.randomMaster.pom.factory.abstractFactory;

import org.randomMaster.pom.constants.DriverType;
import org.randomMaster.pom.factory.ChromeDriverManager;
import org.randomMaster.pom.factory.DriverManager;
import org.randomMaster.pom.factory.FirefoxDriverManager;

public final class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriverManagerAbstract();
            case FIREFOX:
                return new FirefoxDriverManagerAbstract();
            default:
                throw new IllegalStateException("Invalid browser name: " + driverType);
        }
    }
}