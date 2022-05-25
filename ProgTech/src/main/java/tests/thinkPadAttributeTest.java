package tests;

import Classes.Laptop_types.ThinkPad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class thinkPadAttributeTest {
    @Mock
    ThinkPad thinkPad = new ThinkPad(100000);

    @Test
    public void thinkPadPriceTest() {
        int expectedPrice = 110000;
        int actualPrice = thinkPad.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void thinPadTypeTest() {
        String expectedType = "thinkpad";
        String actualType = thinkPad.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}
