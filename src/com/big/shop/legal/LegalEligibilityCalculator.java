package com.big.shop.legal;

import com.big.shop.products.IProduct;
import com.big.shop.products.ProductName;

import java.util.List;
import java.util.function.Predicate;

/**
 * This class shall be used to calculate any com.big.shop.legal obligations in the given country during the checkout of basket.
 * e.g. a user can not purchase more than 10 sim cards or a user can not buy more than one insurance for a give phone etc**/
public class LegalEligibilityCalculator {

    public boolean validSimCardPurchase(List<IProduct> products) {

        if(products.isEmpty() || products.size() ==0){
            throw new RuntimeException("No products in the shopping card to calculate com.big.shop.legal eligibility");
        }

        long noOfSimCards = products
                .stream()
                .filter(simCardPredicate())
                .count();

        if(noOfSimCards > 10){
            throw new RuntimeException("You can not purchase more than 10 Sim cards in single purchase");
        }

        return true;
    }

    private Predicate<IProduct> simCardPredicate() {
        return iProduct -> iProduct.getProductName()== ProductName.SIM_CARD;
    }
}
