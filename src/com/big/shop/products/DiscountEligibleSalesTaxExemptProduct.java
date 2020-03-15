package com.big.shop.products;

/**
 * Any product which is eligible for discounts e.g. insurance product.
 * Please note this will extend @{@link SalesTaxExemptProduct}, which implies sales tax is also exempt.
 * If the application requires Discount with SalesTax then it should create a new class which extends Product class.
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

}
