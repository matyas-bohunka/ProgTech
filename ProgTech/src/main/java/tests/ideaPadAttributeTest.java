package tests;

import Classes.Laptop_types.IdeaPad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ideaPadAttributeTest {
    @Mock
    IdeaPad ideaPad = new IdeaPad(100000);

    @Test
    public void ideaPadPriceTest() {
        int expectedPrice = 100000;
        int actualPrice = ideaPad.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void ideaPadTypeTest() {
        String expectedType = "ideapad";
        String actualType = ideaPad.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}
