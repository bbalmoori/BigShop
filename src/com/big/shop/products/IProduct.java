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
     * calculates the price for a given product by applying discount (if any) and adding sales tax (if applicable)
     **/
    TotalPrice calculatePrice(TotalPrice totalPrice, StringBuilder receiptBuilder);

    /**
     * Calculates discount for the given product
     * @param @{@link TotalPrice} TotalPrice object to hold discount across all the products
     * @param @{@link StringBuilder} builds receipt for a given shopping list
     * @return Double
     **/
    double getPriceWithDiscounts(TotalPrice totalPrice, StringBuilder receiptBuilder);

    /**
     * Calculates sales tax for the given product.
     * @param @{@link StringBuilder} builds receipt for a given shopping list
     * @param @{@link Double} price after discount
     * @return Double sales tax
     **/
    double getProductSalesTax(StringBuilder receiptBuilder, double priceWithDiscounts);
}
