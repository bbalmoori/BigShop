package com.big.shop;

import com.big.shop.domain.TotalPrice;
import com.big.shop.factory.ProductFactory;
import com.big.shop.calculator.CheckoutCalculator;
import com.big.shop.products.IProduct;
import com.big.shop.products.ProductName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main entry class to th application. The main method will read shopping items and creates a list, calculates the prices according to the given requirements.
 * User will pass commandline arguments in double quotes. e.g. "SIM Card" "phone case" "phone insurance" "phone insurance" "wireless earphones" "wired earphones"
 ***/
public class Main {

    public static void main(String[] args) {

        List<IProduct> products = getInputProducts(args);

        final TotalPrice totalPrice = new TotalPrice();

        final StringBuilder receiptBuilder = new StringBuilder();

        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        System.out.println(receiptBuilder.toString());
    }

    private static List<IProduct> getInputProducts(String[] args) {

        if(args.length == 0){
            printUsage();
        }

        Map<ProductName, IProduct> availableProducts = ProductFactory.getAvailableProducts();

        return Arrays.stream(args)
                .map(input -> availableProducts.get(ProductName.fromString(input)))
                .collect(Collectors.toList());
    }

    private static void printUsage ()
    {
        StringBuilder buf = new StringBuilder();
        System.out.println("Please add at least one item to checkout");
        System.out.println("Available products: " + ProductName.availableProducts());
        System.out.println("Arguments must be passed in double quotes(\"\") e.g. \"Sim Card\" (They are case insensitive)");
        System.exit(1);
    }
}
