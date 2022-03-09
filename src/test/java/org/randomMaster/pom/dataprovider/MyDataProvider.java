package org.randomMaster.pom.dataprovider;

import org.randomMaster.pom.objects.Product;
import org.randomMaster.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "dataPump",parallel = false)
    public Object[] dataProvider() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
    }
}