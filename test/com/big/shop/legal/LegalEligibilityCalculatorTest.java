package com.big.shop.legal;

import com.big.shop.TestUtils;
import com.big.shop.products.IProduct;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LegalEligibilityCalculatorTest {

    @Test
    public void testValidSimCardPurchase_ForAllProductsShouldPass() {
        List<IProduct> products = TestUtils.getAllAvailableProducts();
        LegalEligibilityCalculator legalEligibilityCalculator = new LegalEligibilityCalculator();
        assertTrue(legalEligibilityCalculator.validSimCardPurchase(products));
    }

    @Test
    public void testValidSimCardPurchase_tenSimCardsShouldPass() {
        List<IProduct> products = IntStream.rangeClosed(0,9)
                .mapToObj(i -> TestUtils.getSimCardProduct())
                .collect(Collectors.toList());
        LegalEligibilityCalculator legalEligibilityCalculator = new LegalEligibilityCalculator();
        assertTrue(legalEligibilityCalculator.validSimCardPurchase(products));
    }

    @Test(expected = RuntimeException.class)
    public void testValidSimCardPurchase_elevenSimCardsShouldFail() {
        List<IProduct> products = IntStream.rangeClosed(0,10)
                .mapToObj(i -> TestUtils.getSimCardProduct())
                .collect(Collectors.toList());
        LegalEligibilityCalculator legalEligibilityCalculator = new LegalEligibilityCalculator();
        legalEligibilityCalculator.validSimCardPurchase(products);
    }

    @Test(expected = RuntimeException.class)
    public void testValidSimCardPurchase_emptyCartShouldFail() {
        List<IProduct> products = Collections.emptyList();
        LegalEligibilityCalculator legalEligibilityCalculator = new LegalEligibilityCalculator();
        legalEligibilityCalculator.validSimCardPurchase(products);
    }
}