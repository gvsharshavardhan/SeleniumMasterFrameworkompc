package org.randomMaster.pom.tests;

import io.restassured.response.Response;
import org.randomMaster.pom.api.actions.CartApi;
import org.randomMaster.pom.api.actions.SignUpApi;
import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.objects.BillingAddress;
import org.randomMaster.pom.objects.Product;
import org.randomMaster.pom.objects.User;
import org.randomMaster.pom.pages.CheckoutPage;
import org.randomMaster.pom.utils.FakerUtil;
import org.randomMaster.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        injectCookiesIntoBrowser(cartApi.getCookies());
        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .clickPlaceOrderButton();
        Assert.assertEquals(checkoutPage.getNotificationMessageFromCheckOutPage(),
                "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        SignUpApi signUpApi = new SignUpApi();
        User user = new User();
        String username = "randomTest" + FakerUtil.generateRandomNumber();
        user.setUsername(username);
        user.setPassword(username);
        user.setEmail(username + "@test.com");
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesIntoBrowser(signUpApi.getCookies());
        checkoutPage.load().setBillingAddress(billingAddress)
                .clickPlaceOrderButton();
        Assert.assertEquals(checkoutPage.getNotificationMessageFromCheckOutPage(),
                "Thank you. Your order has been received.");
    }
}