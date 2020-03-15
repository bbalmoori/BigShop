package com.big.shop.calculator;

import com.big.shop.domain.TotalPrice;
import com.big.shop.legal.LegalEligibilityCalculator;
import com.big.shop.products.IProduct;

import java.util.List;

/**
 * Simple class which calculates total price and appends the final receipt of the bill.
**/
public class CheckoutCalculator {

    public void calculateBasket(List<IProduct> products, TotalPrice totalPrice, StringBuilder receiptBuilder) {

        new LegalEligibilityCalculator().validSimCardPurchase(products);

        //This has to be called first to check if there are any items eligible for discounts
        //TODO may be we can sort the items in a order such that discounted products appear at the end
        products.stream().forEach(iProduct -> iProduct.setAdditionalDiscountForProduct());

        products.stream().forEach(product -> product.calculatePrice(totalPrice, receiptBuilder));

        receiptBuilder.append("Total Items: " + products.size());
        receiptBuilder.append("\n");
        receiptBuilder.append("----------------------------------");
        receiptBuilder.append("\n");
        receiptBuilder.append(String.format("%-20s= %8.2f", "SUB-TOTAL", totalPrice.getSubTotal()));
        receiptBuilder.append("\n");
        receiptBuilder.append("----------------------------------");
        receiptBuilder.append("\n");

        if(totalPrice.getDiscount() > 0) {
            receiptBuilder.append(String.format("%-20s= %8.2f", "DISCOUNTS", (-1 * totalPrice.getDiscount())));
            receiptBuilder.append("\n");
        }

        receiptBuilder.append(String.format("%-20s= %8.2f", "SALES TAX @%12", totalPrice.getTax()));
        receiptBuilder.append("\n");
        receiptBuilder.append("----------------------------------");
        receiptBuilder.append("\n");
        receiptBuilder.append(String.format("%-20s= %8.2f", "TOTAL TO PAY", totalPrice.getPrice()));
    }
}
