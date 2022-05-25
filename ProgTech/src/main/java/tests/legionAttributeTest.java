package tests;

import Classes.Laptop_types.Legion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class legionAttributeTest {
    @Mock
    Legion legion = new Legion(100000);

    @Test
    public void legionPriceTest() {
        int expectedPrice = 120000;
        int actualPrice = legion.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void legionTypeTest() {
        String expectedType = "legion";
        String actualType = legion.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}
