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

        double priceWithDiscounts = getPrice();

        totalPrice.setSubTotal(totalPrice.getSubTotal() + getPrice());

        receiptBuilder.append(String.format("%-20s= %8.2f", getProductName(), getPrice()));
        receiptBuilder.append("\n");

        //Calculate discount
        double discount = 0;
        if (getDiscount() != 0) {
            discount = (1 - getDiscount() / 100d);
            priceWithDiscounts *= discount;
            totalPrice.setDiscount(totalPrice.getDiscount() + (getPrice() - priceWithDiscounts));
            printDiscountIfApplicable(receiptBuilder, priceWithDiscounts);
        }

        //calculate sales tax
        double productSalesTax = getProductSalesTax(receiptBuilder, priceWithDiscounts);

        totalPrice.setTax(totalPrice.getTax() + productSalesTax);
        totalPrice.setPrice(totalPrice.getPrice() + (priceWithDiscounts + productSalesTax));

        return totalPrice;
    }

    default double getProductSalesTax(StringBuilder receiptBuilder, double priceWithDiscounts) {
        double productSalesTax = 0;

        if (getSalesTax() != 0) {
            productSalesTax = priceWithDiscounts * (getSalesTax() / 100d);
        } else {
            receiptBuilder.append("*Sales Tax Exempt");
            receiptBuilder.append("\n");
        }
        return productSalesTax;
    }

    default void printDiscountIfApplicable(StringBuilder receiptBuilder, double priceWithDiscounts) {

            receiptBuilder.append(String.format("%-20s= %8.2f", "*discount : ", (-1 * (getPrice() - priceWithDiscounts))));
            receiptBuilder.append("\n");

    }
}
