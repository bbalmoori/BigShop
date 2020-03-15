package com.big.shop.products;

public class Product implements IProduct {

    private double price;
    private ProductName productName;

    public Product(double price, ProductName productName){
        this.price = price;
        this.productName = productName;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public ProductName getProductName(){
        return productName;
    }

}
