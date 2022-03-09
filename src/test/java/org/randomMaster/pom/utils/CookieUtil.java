package org.randomMaster.pom.utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtil {

    public List<Cookie> convertRestAssuredCookiesToSeleniumCookies(Cookies cookies) {
        List<Cookie> seleniumCookies = new ArrayList<>();
        List<io.restassured.http.Cookie> restAssuredCookies = cookies.asList();
        for (io.restassured.http.Cookie restCookie : restAssuredCookies) {
            seleniumCookies.add(new Cookie(restCookie.getName(), restCookie.getValue()
                    , restCookie.getDomain(), restCookie.getPath(), restCookie.getExpiryDate(), restCookie.isSecured()
                    , restCookie.isHttpOnly(), restCookie.getSameSite()));
        }
        return seleniumCookies;
    }
}
