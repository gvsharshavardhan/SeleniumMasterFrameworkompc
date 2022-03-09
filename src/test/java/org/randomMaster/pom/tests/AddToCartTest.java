package org.randomMaster.pom.tests;

import org.junit.Assert;
import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.dataprovider.MyDataProvider;
import org.randomMaster.pom.objects.Product;
import org.randomMaster.pom.pages.CartPage;
import org.randomMaster.pom.pages.HomePage;
import org.randomMaster.pom.pages.StorePage;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .clickAddToCartButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "dataPump",dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProducts(Product product){
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getStorePage()
                .clickAddToCartButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}