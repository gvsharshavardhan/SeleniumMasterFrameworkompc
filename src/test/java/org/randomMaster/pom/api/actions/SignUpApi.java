package org.randomMaster.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.randomMaster.pom.objects.User;
import org.randomMaster.pom.utils.ConfigLoader;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public String fetchRegisterNONCValueUsingJsoup() {
        return Objects.requireNonNull(Jsoup.parse(getAccount().asString()).
                selectFirst("#woocommerce-register-nonce"), "Body from getAccount call is null!!").
                attr("value");
    }

    public Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = given().baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .log().all()
                .when()
                .get("/account")
                .then()
                .log().all()
                .extract().response();
        if (response.statusCode() != 200)
            throw new RuntimeException("Failed to fetch account info! status code: " + response.statusCode());
        return response;
    }

    public Response register(User user) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNONCValueUsingJsoup());
        formParams.put("register", "Register");
        Response response = given().baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .cookies(cookies)
                .formParams(formParams)
                .log().all()
                .when()
                .post("/account")
                .then()
                .log().all()
                .extract().response();
        if (response.statusCode() != 302 && response.statusCode() != 200) {
            throw new RuntimeException("Failed to create an account! status code: " + response.statusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}