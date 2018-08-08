package kz.c0rp.stairs.water;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kz.c0rp.stairs.water.model.UShaped;
import static kz.c0rp.stairs.water.utils.StairwaysManager.*;
import static kz.c0rp.stairs.water.utils.StairwaysManager.calculateWaterBetweenLocalMaximum;
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
            {Arrays.asList(4,1,1,0,2,3), 8},
            {Arrays.asList(1,2,5,7,1,2,3,12),15},
//            {Arrays.asList(5,3,1,3,1,4,1,7,4,3,2,1,0,2,5,7), 49}
        };

    }

    @Test(dataProvider = "Stairs")
    public void testFindAllShapesWithWater(List<Integer> stairs, int amountOfWater) throws Exception {

        final List<UShaped> allShapesWithWater = findAllShapesWithWater(stairs);

        final int sumShapes = allShapesWithWater.stream()
            .mapToInt(v -> calculateWaterBetweenLocalMaximum(v.getStairsInShape()))
            .sum();

        assertEquals(sumShapes, amountOfWater, "Amount of water calculated is not similar");
    }

    @Test(dataProvider = "Stairs")
    public void testFindAllLocalMaximums(List<Integer> stairs, int amountOfWater) throws Exception {

        final List<Integer> allLocalMaximumIdx = findAllLocalMaximums(stairs)
            .stream().sorted().collect(Collectors.toList());

        final int size = allLocalMaximumIdx.size();

        int water = 0;
        for (int i = 0; i < size; i++) {
            if (i + 1 < size) {
                final Integer idxL = allLocalMaximumIdx.get(i);
                final Integer idxR = allLocalMaximumIdx.get(i+1);

                water += calculateWaterBetweenLocalMaximum(stairs.subList(idxL, idxR+1));
            }
        }

        assertEquals(water, amountOfWater, "Amount of water calculated is not similar");
    }

}
