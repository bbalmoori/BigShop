package com.big.shop.domain;

/**
 * This POJO class will hold the total calculations in Big Shop application cart.
 * The users of this class are expected to use this class as a place holder while calculating tax and any promotions.
 * Please note all the variables are @{@link Double} even though it is monetary calculation.
 * It will be an overkill to use @{@link java.math.BigDecimal} for this simple calculation considering the performance benefits
 * **/
public class TotalPrice {

    private double subTotal;
    private double price;
    private double discount;
    private double tax;
    private int noOfBuyOneGetOneProductsInCart;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getNoOfBuyOneGetOneProductsInCart() {
        return noOfBuyOneGetOneProductsInCart;
    }

    public void setNoOfBuyOneGetOneProductsInCart(int noOfBuyOneGetOneProductsInCart) {
        this.noOfBuyOneGetOneProductsInCart = noOfBuyOneGetOneProductsInCart;
    }
}
