package org.randomMaster.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.randomMaster.pom.base.BasePage;
import org.randomMaster.pom.objects.BillingAddress;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.cssSelector("#billing_first_name");
    private final By lastNameField = By.cssSelector("#billing_last_name");
    private final By address1Field = By.cssSelector("#billing_address_1");
    private final By cityField = By.cssSelector("#billing_city");
    private final By postCodeField = By.cssSelector("#billing_postcode");
    private final By emailField = By.cssSelector("#billing_email");
    private final By placeOrderButton = By.cssSelector("#place_order");
    private final By loginLink = By.cssSelector(".showlogin");
    private final By usernameField = By.cssSelector("#username");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("button[value='Login']");
    private final By loader = By.cssSelector(".blockUI.blockOverlay");
    private final By notificationMessage = By.cssSelector(".woocommerce-notice");
    private final By productName = By.xpath("//td[@class='product-name']");
    private final By title = By.cssSelector(".has-text-align-center");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return this;
    }

    public CheckoutPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return this;
    }

    public CheckoutPage enterUsername(String username) {
        WebElement usernameElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public CheckoutPage login(String username, String password) {
        return enterUsername(username).enterPassword(password).clickLoginButton();
    }

    public CheckoutPage enterFirstName(String firstName) {
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        driver.findElement(address1Field).clear();
        driver.findElement(address1Field).sendKeys(address);
        return this;
    }

    public CheckoutPage enterCityName(String city) {
        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postcode) {
        driver.findElement(postCodeField).clear();
        driver.findElement(postCodeField).sendKeys(postcode);
        return this;
    }

    public CheckoutPage enterEmailId(String emailId) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(emailId);
        return this;
    }

    public void clickPlaceOrderButton() throws InterruptedException {
        waitTillLoadersDisappear(loader);
        driver.findElement(placeOrderButton).click();
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .enterAddress(billingAddress.getAddressLineOne())
                .enterPostCode(billingAddress.getPostalcode())
                .enterCityName(billingAddress.getCity())
                .enterEmailId(billingAddress.getEmail());
    }

    public String getNotificationMessageFromCheckOutPage() {
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(notificationMessage)).getText();
    }

    public String getProductName() {
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }
}