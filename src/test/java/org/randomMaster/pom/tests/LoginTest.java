package org.randomMaster.pom.tests;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.randomMaster.pom.api.actions.CartApi;
import org.randomMaster.pom.api.actions.SignUpApi;
import org.randomMaster.pom.base.BaseTest;
import org.randomMaster.pom.objects.Product;
import org.randomMaster.pom.objects.User;
import org.randomMaster.pom.pages.CheckoutPage;
import org.randomMaster.pom.utils.FakerUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws IOException {
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
        injectCookiesIntoBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.clickLoginLink().login(user.getUsername(),user.getPassword());
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }
}
