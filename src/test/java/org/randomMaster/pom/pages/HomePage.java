package org.randomMaster.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.randomMaster.pom.base.BasePage;

public class HomePage extends BasePage {

    private final By StoreMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");

    private final StorePage storePage = new StorePage(driver);

    public StorePage getStorePage() {
        return storePage;
    }

    public HomePage(WebDriver driver){
        super(driver);
    }

    public StorePage NaviagateToStoreUsingMenu(){
      driver.findElement(StoreMenuLink).click();
      return new StorePage(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }
}