package com.big.shop.products;

import com.big.shop.factory.ProductFactory;

/**
 * Use this class where another product depends on this for discount.
 * The @{@method setAdditionalDiscountForProduct} will loop through all the eligible items for discount and sets the applicable discount.
 **/
public class DiscountApplicableProduct extends Product {

    public DiscountApplicableProduct(double price, ProductName productName) {
        super(price, productName);
    }

    /**
     * This method must be called before the final checkout for each discounted product to set additional eligible discounted items if eligible.
     * Failing to do so will skip the additional discount calculation
     **/

    @Override
    public void setAdditionalDiscountForProduct() {
        ProductFactory.getDiscountEligibleProducts()
                .entrySet()
                .forEach(entry -> entry.getKey().setDiscount(entry.getValue()));
    }
}
