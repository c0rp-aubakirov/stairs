package kz.c0rp.stairs.water.calculator;

import java.util.List;
import kz.c0rp.stairs.water.utils.StairwaysManager;
import static kz.c0rp.stairs.water.utils.StairwaysManager.calculateWaterBetweenLocalMaximum;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class UShapeWaterCalc implements WaterCalculator{

    @Override
    public Integer calculateWaterInStairs(final List<Integer> stairs) {

        return StairwaysManager.findAllShapesWithWater(stairs).stream()
            .mapToInt(v -> calculateWaterBetweenLocalMaximum(v.getStairsInShape()))
            .sum();

    }
}
