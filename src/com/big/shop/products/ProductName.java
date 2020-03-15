package com.big.shop.products;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple enum class to represent all available products
 * In the actual application these should come database.
 * **/
public enum ProductName {
    SIM_CARD("SIM Card"),
    PHONE_CASE("phone case"),
    PHONE_INSURANCE("phone insurance"),
    WIRED_EARPHONES("wired earphones"),
    WIRELESS_EARPHONES("wireless earphones");

    public final String name;

    ProductName(String name){
        this.name = name;
    }

    public static List<String> availableProducts(){
       return Arrays.stream(ProductName.values())
               .map( productName -> productName.name)
               .collect(Collectors.toList());
    }

    public static ProductName fromString(String input){
        for (ProductName productName : ProductName.values()) {
            if (productName.name.equalsIgnoreCase(input)) {
                return productName;
            }
        }
        throw new RuntimeException("Cannot find the product from the name: " + input + ". Available products " + availableProducts());
    }
}
