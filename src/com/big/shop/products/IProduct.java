package com.big.shop.products;

import com.big.shop.domain.TotalPrice;

/**
 * Generic product interface where the implementors shall provide the name and price of the product**/
public interface IProduct {

    /**
     * Default sales tax for all products.
     * Implementors shall override this method return the sales tax appropriately if required. e.g. Insurance products are exempt from sales tax
     **/
    default double getSalesTax(){
        return 12;
    }

    /**
     * This method will return default discount for any given product, which is 0.
     * If any of the products are eligible for additional discount this method can be used.
     * **/
    default double getDiscount(){
        return 0;
    }

    double getPrice();

    ProductName getProductName();


    /**
     * Implementors of this method shall set additional eligible discounted items if eligible.
     * Failing to do so will skip the additional discount calculation
     * e.g. any earphone product in the cart will make 20% discount on insurance products
     **/
    default void setAdditionalDiscountForProduct(){
    }

    default void setDiscount(double discount){
    }

    /**
     * Default implementation for calculating the price of a given product.
     * The default discount while calculating is 0%. If any product is eligible for discount, then {@link #setDiscount(double)} must be called before calculating the price
     * This method will use default sales tax, to change this behaviour subclasses must override {@link #getSalesTax()}
     **/
    default TotalPrice calculatePrice(TotalPrice totalPrice, StringBuilder receiptBuilder) {

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
    default double getPriceWithDiscounts(TotalPrice totalPrice, StringBuilder receiptBuilder) {
       return getPrice();
    }

    /**
     * Default method to calculate sales tax, this will apply 12% sales tax.
     * Any other sales tax, like exemption, lower or higher sales tax products must override this method.
     **/
    default double getProductSalesTax(StringBuilder receiptBuilder, double priceWithDiscounts) {
        return  priceWithDiscounts * (getSalesTax() / 100d);
    }
}
