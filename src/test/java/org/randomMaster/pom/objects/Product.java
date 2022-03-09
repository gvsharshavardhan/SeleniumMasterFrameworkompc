package org.randomMaster.pom.objects;

import org.randomMaster.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {

    private int id;
    private String name;

    public Product() {
    }

    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        for (Product p : products) {
            if (p.getId() == id) {
                this.id = p.getId();
                this.name = p.getName();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
