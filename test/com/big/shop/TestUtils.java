package com.big.shop;

import com.big.shop.factory.ProductFactory;
import com.big.shop.products.BuyOneGetOneProduct;
import com.big.shop.products.IProduct;
import com.big.shop.products.Product;
import com.big.shop.products.ProductName;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List<IProduct> getAllAvailableProducts() {
        return new ArrayList<>(ProductFactory.getAvailableProducts().values());
    }

    public static IProduct getSimCardProduct() {
        return ProductFactory.getAvailableProducts().get(ProductName.SIM_CARD);
    }

    public static IProduct getPhoneCaseProduct() {
        return ProductFactory.getAvailableProducts().get(ProductName.PHONE_CASE);
    }

    public static IProduct getPhoneInsuranceProduct() {
        return ProductFactory.getAvailableProducts().get(ProductName.PHONE_INSURANCE);
    }

    public static IProduct getWiredEarphonesProduct() {
        return ProductFactory.getAvailableProducts().get(ProductName.WIRED_EARPHONES);
    }

    public static IProduct getWirelessEarphonesProduct() {
        return ProductFactory.getAvailableProducts().get(ProductName.WIRELESS_EARPHONES);
    }
}
