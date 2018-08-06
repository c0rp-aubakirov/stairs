package kz.c0rp.stairs.water;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class StairwaysManager {

    /**
     *
     * Finds all UShapes by simple strategy in one pass
     * Computational complexity is O(n)
     * Memory complexity is O(n)
     *
     *
     *
     * @param stairs array as an input
     * @return ArrayList of #UShaped that was found
     */
    public static List<UShaped> findAllShapesWithWater(
        final List<Integer> stairs) {

        int previousIdx = 0;

        int startsDecrease = -1;

        boolean increasing = false;
        boolean decreasing = false;

        final List<UShaped> shapedList = new ArrayList<>();
        UShaped uShaped = new UShaped();

        for (int i = 0; i < stairs.size(); i++) {

            final Integer current = stairs.get(i);
            final Integer previous = stairs.get(previousIdx);

            if (previous > current) {

                if (nowValuesIncreasingButPreviouslyWasDecreasing(startsDecrease, increasing)) {
                    uShaped.setFinishIncreasingIdx(previousIdx);
                    shapedList.add(uShaped);
                    uShaped = new UShaped();
                    uShaped.getStairsInShape().add(previous);
                }

                if (!decreasing) {
                    startsDecrease = previousIdx;
                    decreasing = true;
                    increasing = false;
                    uShaped.setStartDecreasingIdx(startsDecrease);
                }
            }

            if (previous < current) {
                if (!increasing) {
                    decreasing = false;
                    increasing = true;
                    uShaped.setStartIncreasingIdx(previousIdx);
                }
            }

            uShaped.getStairsInShape().add(current);

            previousIdx = i;
        }

        // check if last UShape is also with water
        if (nowValuesIncreasingButPreviouslyWasDecreasing(startsDecrease, increasing)) {
            uShaped.setFinishIncreasingIdx(previousIdx);
            shapedList.add(uShaped);
        }
        return shapedList;
    }

    private static boolean nowValuesIncreasingButPreviouslyWasDecreasing(
        final int startsDecrease, final boolean increasing) {

        return increasing && startsDecrease != -1;
    }

}
