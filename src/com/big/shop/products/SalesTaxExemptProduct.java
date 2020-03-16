package com.big.shop.products;


/**
 * Use this class for SalesTax exempted products. This class will return salesTax as 0 e.g. insurance products are exempt from sales tax.
 **/
public class SalesTaxExemptProduct extends Product {

    private double discount;

    public SalesTaxExemptProduct(double price, ProductName productName) {
        super(price, productName);
    }


    public double getSalesTax() {
        return 0;
    }

    public double getProductSalesTax(StringBuilder receiptBuilder, double priceWithDiscounts) {
        receiptBuilder.append("*Sales Tax Exempt");
        receiptBuilder.append("\n");
        return 0;
    }

}
