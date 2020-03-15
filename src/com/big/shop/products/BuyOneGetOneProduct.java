package com.big.shop.products;

import com.big.shop.domain.TotalPrice;

/**
 * Simple class to calculate buy one get free during the checkout
 **/
public class BuyOneGetOneProduct extends Product {
    public BuyOneGetOneProduct(double price, ProductName productName){
        super(price, productName);
    }

    @Override
    public TotalPrice calculatePrice(TotalPrice totalPrice, StringBuilder receiptBuilder) {
        int buyOneGetOneProducts = totalPrice.getNoOfBuyOneGetOneProductsInCart();
        buyOneGetOneProducts++;
        totalPrice.setNoOfBuyOneGetOneProductsInCart(buyOneGetOneProducts);
        if (buyOneGetOneProducts % 2 == 0) {
            totalPrice.setTax(totalPrice.getTax() + 0);
            totalPrice.setPrice(totalPrice.getPrice() + 0);
            totalPrice.setDiscount(totalPrice.getDiscount() + getPrice());

            receiptBuilder.append(String.format("%-20s= %8.2f", getProductName(), -1 * getPrice()));
            receiptBuilder.append("\n");
            receiptBuilder.append("*Buy One Get One free");
            receiptBuilder.append("\n");
            return totalPrice;
        } else {
            return super.calculatePrice(totalPrice, receiptBuilder);
        }
    }
}
