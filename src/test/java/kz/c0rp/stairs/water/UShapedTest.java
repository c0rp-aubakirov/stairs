package kz.c0rp.stairs.water;

import java.util.Arrays;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class UShapedTest {

    @DataProvider(name = "Stairs")

    public static Object[][] credentials() {

        return new Object[][] {
            {new UShaped(0, 1,2, Arrays.asList(3,2,4))},
            {new UShaped(2, 3,4, Arrays.asList(4,1,2))},
        };

    }


    @Test(dataProvider = "Stairs")
    public void testCalculateAmountOfWaterInside(final UShaped uShaped) throws Exception {
        assertEquals(new Integer(1), uShaped.calculateAmountOfWaterInside(), "This stairs shapes has exactly one slot for water");
    }

}
