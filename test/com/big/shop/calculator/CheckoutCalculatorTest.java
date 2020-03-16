package com.big.shop.calculator;

import com.big.shop.TestUtils;
import com.big.shop.domain.TotalPrice;
import com.big.shop.products.IProduct;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutCalculatorTest {

    public static final double DELTA = 0.0000001;
    public static final String SALES_TAX_EXEMPT = "*Sales Tax Exempt";

    @Test
    public void testCalculateBasket_allProducts(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = TestUtils.getAllAvailableProducts();
        new CheckoutCalculator().calculateBasket(products, totalPrice, new StringBuilder());

        assertEquals("Discount calculated is incorrect", 24d, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 230d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",13.20, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",219.20, totalPrice.getPrice(), DELTA);
    }

    @Test
    public void testCalculateBasket_OneSimCard(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Collections.singletonList(TestUtils.getSimCardProduct());
        new CheckoutCalculator().calculateBasket(products, totalPrice, new StringBuilder());

        assertEquals("Discount calculated is incorrect", 0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 20d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",2.40, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",22.40, totalPrice.getPrice(), DELTA);
    }

   @Test
    public void testCalculateBasket_OnePhoneCase(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Collections.singletonList(TestUtils.getPhoneCaseProduct());
        new CheckoutCalculator().calculateBasket(products, totalPrice, new StringBuilder());

        assertEquals("Discount calculated is incorrect", 0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 10d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",1.20, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",11.20, totalPrice.getPrice(), DELTA);
    }

    @Test
    public void testCalculateBasket_ThreeSimCards(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Arrays.asList(TestUtils.getSimCardProduct(), TestUtils.getSimCardProduct(), TestUtils.getSimCardProduct());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 20d, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 40d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",4.80, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",44.80, totalPrice.getPrice(), DELTA);
        assertThat(receiptBuilder.toString(), CoreMatchers.containsString("*Buy One Get One free"));
    }

    @Test
    public void testCalculateBasket_TenSimCards(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = IntStream.rangeClosed(0, 9).mapToObj( i-> TestUtils.getSimCardProduct()).collect(Collectors.toList());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 100d, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 100d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",12.00, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",112.00, totalPrice.getPrice(), DELTA);
        assertThat(receiptBuilder.toString(), CoreMatchers.containsString("*Buy One Get One free"));
    }

    @Test(expected = RuntimeException.class)
    public void testCalculateBasket_ElevenSimCards(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = IntStream.rangeClosed(0, 10).mapToObj( i-> TestUtils.getSimCardProduct()).collect(Collectors.toList());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);
    }

    @Test
    public void testCalculateBasket_phoneInsurance_withoutEarPhones(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Collections.singletonList(TestUtils.getPhoneInsuranceProduct());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 120d, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",0.00, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",120.00, totalPrice.getPrice(), DELTA);
        assertThat(receiptBuilder.toString(), CoreMatchers.containsString(SALES_TAX_EXEMPT));
    }

    @Test
    public void testCalculateBasket_phoneInsurance_withWiredEarPhones(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Arrays.asList(TestUtils.getPhoneInsuranceProduct(), TestUtils.getWiredEarphonesProduct());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 24.0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 150.00, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",3.60, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",129.60, totalPrice.getPrice(), DELTA);
        assertThat(receiptBuilder.toString(), CoreMatchers.containsString(SALES_TAX_EXEMPT));
    }

    @Test
    public void testCalculateBasket_phoneInsurance_withWirelessEarPhones(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Arrays.asList(TestUtils.getPhoneInsuranceProduct(), TestUtils.getWirelessEarphonesProduct());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 24.0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 170.00, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",6.00, totalPrice.getTax(), DELTA);

        assertThat(receiptBuilder.toString(), CoreMatchers.containsString(SALES_TAX_EXEMPT));
    }

    @Test
    public void testCalculateBasket_phoneInsurance_withWired_and_WirelessEarPhones(){
        TotalPrice totalPrice = new TotalPrice();
        List<IProduct> products = Arrays.asList(TestUtils.getPhoneInsuranceProduct(),TestUtils.getWiredEarphonesProduct(), TestUtils.getWirelessEarphonesProduct());
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        assertEquals("Discount calculated is incorrect", 24.0, totalPrice.getDiscount(), DELTA);
        assertEquals("Sub-total calculated is incorrect", 200.00, totalPrice.getSubTotal(), DELTA);
        assertEquals("SalesTax calculated is incorrect",9.60, totalPrice.getTax(), DELTA);
        assertEquals("Total pay calculated is incorrect",185.60, totalPrice.getPrice(), DELTA);
        assertThat(receiptBuilder.toString(), CoreMatchers.containsString(SALES_TAX_EXEMPT));
    }

    @Test(expected = RuntimeException.class)
    public void testCalculateBasket_emptyBasket(){
        TotalPrice totalPrice = new TotalPrice();
        List products = Collections.EMPTY_LIST;
        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);
    }

    @Test
    public void testCalculateBasket_allProducts_receipt(){

        String receipt =
                "SIM_CARD            =    20.00\n" +
                "PHONE_CASE          =    10.00\n" +
                "PHONE_INSURANCE     =   120.00\n" +
                "*discount :         =   -24.00\n" +
                "*Sales Tax Exempt\n" +
                "WIRED_EARPHONES     =    30.00\n" +
                "WIRELESS_EARPHONES  =    50.00\n" +
                "----------------------------------\n" +
                "Total Items: 5\n" +
                "----------------------------------\n" +
                "SUB-TOTAL           =   230.00\n" +
                "----------------------------------\n" +
                "DISCOUNTS           =   -24.00\n" +
                "SALES TAX @%12      =    13.20\n" +
                "----------------------------------\n" +
                "TOTAL TO PAY        =   219.20";

        TotalPrice totalPrice = new TotalPrice();
        //make it ordered collection to always print in the same order
        List<IProduct> products = TestUtils.getAllAvailableProducts().stream().sorted(Comparator.comparing(IProduct::getProductName)).collect(Collectors.toList());

        StringBuilder receiptBuilder = new StringBuilder();
        new CheckoutCalculator().calculateBasket(products, totalPrice, receiptBuilder);

        String actualReceipt = receiptBuilder.toString();
        assertEquals("Receipt is incorrect", receipt, actualReceipt);
    }

}