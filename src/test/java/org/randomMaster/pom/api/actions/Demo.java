package org.randomMaster.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.randomMaster.pom.objects.User;

import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
//        Response response = new SignUpApi().getAccount();
//        Document body = Jsoup.parse(response.body().asString());
//        String value = Objects.requireNonNull(body.selectFirst("#woocommerce-register-nonce")).attr("value");
//        System.out.println(value);
        User user = new User();
        user.setUserid("201");
        user.setUsername("randomtest102");
        user.setPassword("randomtest102");
        user.setEmail("randomtest102@test.com");
        Cookies str = new SignUpApi().register(user).getDetailedCookies();
        System.out.println(str.toString());
    }
}