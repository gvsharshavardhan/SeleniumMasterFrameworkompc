package org.randomMaster.pom.tests;

import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.objects.BillingAddress;
import org.randomMaster.pom.objects.Product;
import org.randomMaster.pom.objects.User;
import org.randomMaster.pom.pages.CartPage;
import org.randomMaster.pom.pages.CheckoutPage;
import org.randomMaster.pom.pages.HomePage;
import org.randomMaster.pom.pages.StorePage;
import org.randomMaster.pom.utils.ConfigLoader;
import org.randomMaster.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = null;
        try {
            billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Product product = new Product(1215);
        billingAddress
                .setFirstName("demotest")
                .setLastName("user")
                .setAddressLineOne("Texas")
                .setCity("Texas")
                .setPostalcode("94188")
                .setEmail("testme@yahoo.com");
        StorePage storePage = new HomePage(getDriver())
                .load()
                .NaviagateToStoreUsingMenu()
                .search("blue");
        Assert.assertEquals(storePage.waitUntilSearchResultsTitleToBePresent().getTitle(), "Search results: “blue”");
        storePage.clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckOutButton();
        checkoutPage.setBillingAddress(billingAddress)
                .clickPlaceOrderButton();
        Assert.assertEquals(checkoutPage.getNotificationMessageFromCheckOutPage(),
                "Thank you. Your order has been received.");
    }

    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "blue";
        BillingAddress billingAddress =
                new BillingAddress("demotest", "user", "texas", "texas", "94188", "happy@jd.com");
        StorePage storePage = new HomePage(getDriver())
                .load()
                .NaviagateToStoreUsingMenu();
        storePage.search(searchFor);
        Assert.assertEquals(storePage.waitUntilSearchResultsTitleToBePresent().getTitle(), "Search results: “" + searchFor + "”");
        storePage.clickAddToCartButton("Blue Shoes");
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
        CheckoutPage checkoutPage = cartPage.clickCheckOutButton();
        checkoutPage.clickLoginLink();
        User user = new User(ConfigLoader.getInstance().getUserId());
        checkoutPage
                .login(user.getUsername(), user.getPassword()).setBillingAddress(billingAddress)
                .clickPlaceOrderButton();
        Assert.assertEquals(checkoutPage.getNotificationMessageFromCheckOutPage(),
                "Thank you. Your order has been received.");
    }
}