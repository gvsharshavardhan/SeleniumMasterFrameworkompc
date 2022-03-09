package org.randomMaster.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.randomMaster.pom.base.BasePage;

public class StorePage extends BasePage {

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartButton = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    private StorePage enterTextInSearchField(String text) {
        WebElement searchFieldElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        searchFieldElement.clear();
        searchFieldElement.sendKeys(text);
        return this;
    }

    private StorePage clickSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }

    public StorePage search(String text) throws InterruptedException {
        return enterTextInSearchField(text).clickSearchButton();
    }

    public String getTitle() {
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
//        return driver.findElement(title).getText();
    }

    public StorePage waitUntilSearchResultsTitleToBePresent() {
        shortWait.until(ExpectedConditions.textToBePresentInElementLocated(title, "Search results:"));
        return this;
    }


    private By getAddToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartButton(String productName) {
        By addToCartBtn = getAddToCartButtonElement(productName);
        driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart() {
        shortWait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        return new CartPage(driver);
    }
}