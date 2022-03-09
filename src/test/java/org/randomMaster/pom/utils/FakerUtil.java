package org.randomMaster.pom.utils;

import com.github.javafaker.Faker;

public final class FakerUtil {

    private FakerUtil(){}

    public static Long generateRandomNumber() {
        return new Faker().number().randomNumber();
    }
}