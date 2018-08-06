package kz.c0rp.stairs.water;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class StairwaysManagerTest {

    @DataProvider(name = "Stairs")

    public static Object[][] credentials() {

        return new Object[][] {
            {Arrays.asList(3,2,4,1,2), 2},
            {Arrays.asList(4,1,1,0,2,3), 8}
        };

    }

    @Test(dataProvider = "Stairs")
    public void testFindAllShapesWithWater(List<Integer> stairs, int amountOfWater) throws Exception {

        final List<UShaped> allShapesWithWater = StairwaysManager.findAllShapesWithWater(stairs);

        final int sumShapes = allShapesWithWater.stream()
            .mapToInt(UShaped::calculateAmountOfWaterInside).sum();

        assertEquals(amountOfWater, sumShapes, "Amount of water calculated is not similar");
    }

}
