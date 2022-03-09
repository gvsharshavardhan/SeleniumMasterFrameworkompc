package org.randomMaster.pom.tests;

import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @Test
    public void searchWithPartialMatch() throws InterruptedException {
        String searchFor = "blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        Assert.assertEquals(storePage.waitUntilSearchResultsTitleToBePresent().getTitle(), "Search results: “" + searchFor + "”");
    }
}