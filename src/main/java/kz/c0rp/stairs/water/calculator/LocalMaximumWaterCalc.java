package kz.c0rp.stairs.water.calculator;

import java.util.List;
import kz.c0rp.stairs.water.utils.StairwaysManager;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class LocalMaximumWaterCalc implements WaterCalculator {

    @Override
    public Integer calculateWaterInStairs(final List<Integer> stairs) {

       return StairwaysManager.calculateWaterByLocalMaximum(stairs);

    }
}
