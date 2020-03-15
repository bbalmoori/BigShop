package com.big.shop.factory;

import com.big.shop.products.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to populate available products.
 * This shall populate all the available products in the shop during startup. Also it sets up discounted products.
 * This can be achieved many ways using spring but using static initialisation for this simple application
 * **/
public class ProductFactory {

    private static Map<ProductName, IProduct> availableProducts = new HashMap<>();

    private static Map<Product, Double> discountEligibleProducts = new HashMap<>();

    private static final DiscountEligibleSalesTaxExemptProduct PHONE_INSURANCE_PRODUCT = new DiscountEligibleSalesTaxExemptProduct(120, ProductName.PHONE_INSURANCE);

    static {
        availableProducts.put(ProductName.SIM_CARD, new BuyOneGetOneProduct(20, ProductName.SIM_CARD));
        availableProducts.put(ProductName.PHONE_CASE, new Product(10, ProductName.PHONE_CASE));
        availableProducts.put(ProductName.PHONE_INSURANCE, PHONE_INSURANCE_PRODUCT);
        availableProducts.put(ProductName.WIRED_EARPHONES, new DiscountApplicableProduct(30, ProductName.WIRED_EARPHONES));
        availableProducts.put(ProductName.WIRELESS_EARPHONES, new DiscountApplicableProduct(50, ProductName.WIRELESS_EARPHONES));

        discountEligibleProducts.put(PHONE_INSURANCE_PRODUCT, 20d);
    }

    /**
     * returns all available products in Big Shop application
     * @return
     * returns Map of available products @{@link Map<ProductName , IProduct>}
     * **/
    public static Map<ProductName, IProduct> getAvailableProducts() {
        return availableProducts;
    }

    /**
     * returns all the available discounted products.
     * @return
     * returns Map of discounted available discounted products @{@link Map<Product, Double>}
     * **/
    public static Map<Product, Double> getDiscountEligibleProducts() {
        return discountEligibleProducts;
    }
}
