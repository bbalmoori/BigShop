package com.big.shop.products;

import com.big.shop.domain.TotalPrice;

/**
 * Any product which is eligible for discounts e.g. insurance product.
 * Please note this will extend @{@link SalesTaxExemptProduct}, which implies sales tax is also exempt.
 * If the application requires Discount without SalesTax then it should create a new class which extends Product class.
 **/
public class DiscountEligibleSalesTaxExemptProduct extends SalesTaxExemptProduct {

    private double discount;

    public DiscountEligibleSalesTaxExemptProduct(double price, ProductName productName) {
        super(price, productName);
    }

    @Override
    public double getDiscount() {
        return this.discount;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double getPriceWithDiscounts(TotalPrice totalPrice, StringBuilder receiptBuilder) {

        double priceWithDiscounts = getPrice() * (1 - getDiscount() / 100d);

        totalPrice.setDiscount(totalPrice.getDiscount() + (getPrice() - priceWithDiscounts));
        receiptBuilder.append(String.format("%-20s= %8.2f", "*discount : ", (-1 * (getPrice() - priceWithDiscounts))));
        receiptBuilder.append("\n");

        return priceWithDiscounts;
    }

}
