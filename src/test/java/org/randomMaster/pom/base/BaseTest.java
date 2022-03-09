package org.randomMaster.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.randomMaster.pom.constants.DriverType;
import org.randomMaster.pom.factory.abstractFactory.DriverManagerAbstract;
import org.randomMaster.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.randomMaster.pom.utils.CookieUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {

    protected ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    private void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    protected ThreadLocal<DriverManagerAbstract> threadLocalDriverManager = new ThreadLocal<>();

    protected DriverManagerAbstract getDriverManager() {
        return threadLocalDriverManager.get();
    }

    private void setDriverManager(DriverManagerAbstract driver) {
        threadLocalDriverManager.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional("CHROME") String browser) {
//        setDriver(new DriverManagerOriginial().initDriver(browser));
        String localBrowser = java.util.Optional.ofNullable(System.getProperty("browser")).orElse(browser);
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(localBrowser)).createDriver());
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(localBrowser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("Current thread: " + Thread.currentThread().getId() + " \t::\t" + "Driver = " + getDriver());
        getDriver().manage().window().maximize();
    }

    @Parameters("browser")
    @AfterMethod
    public void stopDriver(@Optional("CHROME") String browser, ITestResult result) throws InterruptedException, IOException {
        Thread.sleep(2000);
        File destFile = new File("src" + File.separator + browser
                + File.separator + result.getTestClass().getRealClass().getSimpleName() + "_" +
                result.getMethod().getMethodName() + ".png");
        takeScreenShot(destFile);
        getDriver().quit();
    }

    public void injectCookiesIntoBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtil().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    void takeScreenShot(File destFile) throws IOException {
        FileUtils.copyFile(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE), destFile);
    }
}