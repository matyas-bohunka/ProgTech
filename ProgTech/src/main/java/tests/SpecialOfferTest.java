package tests;

import Classes.Laptop_types.ThinkPad;
import Classes.Laptop_types.Legion;
import Classes.Laptop_types.IdeaPad;
import Classes.Decorator.SpecialOffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpecialOfferTest {
    SpecialOffer testOffer;
    ThinkPad testThinkPad;
    Legion testLegion;
    IdeaPad testIdeaPad;

    @Test
    void testCouponWithThinkPad() {
        testThinkPad = new ThinkPad(100000);
        testOffer = new SpecialOffer(testThinkPad);
        testOffer.setCoupon(20);
        int expectedPrice = (int) Math.round(testThinkPad.getPrice() * 0.8);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }

    @Test
    void testCouponWithLegion() {
        testLegion = new Legion(100000);
        testOffer = new SpecialOffer(testLegion);
        testOffer.setCoupon(20);
        int expectedPrice = (int) Math.round(testLegion.getPrice() * 0.8);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }

    @Test
    void testCouponWithIdeaPad() {
        testIdeaPad = new IdeaPad(100000);
        testOffer = new SpecialOffer(testIdeaPad);
        testOffer.setCoupon(20);
        int expectedPrice = (int) Math.round(testIdeaPad.getPrice() * 0.8);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }
}
