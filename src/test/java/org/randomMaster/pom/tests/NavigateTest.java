package org.randomMaster.pom.tests;

import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.pages.HomePage;
import org.randomMaster.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigateTest extends BaseTest {

    @Test
    public void NavigateToHomePageToStoreUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver())
                .load()
                .NaviagateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }
}