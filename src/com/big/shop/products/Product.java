package com.big.shop.products;

import com.big.shop.domain.TotalPrice;

public class Product implements IProduct {

    private double price;
    private ProductName productName;

    public Product(double price, ProductName productName) {
        this.price = price;
        this.productName = productName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public ProductName getProductName() {
        return productName;
    }

    /**
     * Default implementation for calculating the price of a given product.
     * The default discount while calculating is 0%. If any product is eligible for discount, then {@link #setDiscount(double)} must be called before calculating the price
     * This method will use default sales tax, to change this behaviour subclasses must override {@link #getSalesTax()}
     **/
    @Override
    public TotalPrice calculatePrice(TotalPrice totalPrice, StringBuilder receiptBuilder) {

        totalPrice.setSubTotal(totalPrice.getSubTotal() + getPrice());

        receiptBuilder.append(String.format("%-20s= %8.2f", getProductName(), getPrice()));
        receiptBuilder.append("\n");

        //Calculate discount
        double priceWithDiscounts = getPriceWithDiscounts(totalPrice, receiptBuilder);

        //calculate sales tax
        double productSalesTax = getProductSalesTax(receiptBuilder, priceWithDiscounts);

        totalPrice.setTax(totalPrice.getTax() + productSalesTax);
        totalPrice.setPrice(totalPrice.getPrice() + (priceWithDiscounts + productSalesTax));

        return totalPrice;
    }

    /**
     * Default method for discount calculation, this will return original price. Discounted products must implement this method to calculate discount
     **/
    @Override
    public double getPriceWithDiscounts(TotalPrice totalPrice, StringBuilder receiptBuilder) {
        return getPrice();
    }

    /**
     * Default method to calculate sales tax, this will apply 12% sales tax.
     * Any other sales tax, like exemption, lower or higher sales tax products must override this method.
     **/
    @Override
    public double getProductSalesTax(StringBuilder receiptBuilder, double priceWithDiscounts) {
        return priceWithDiscounts * (getSalesTax() / 100d);
    }

}
