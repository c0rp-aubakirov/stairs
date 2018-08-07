package kz.c0rp.stairs.water;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class StairwaysManager {

    /**
     * This was the first solution
     *
     * Finds all UShapes by simple strategy in one pass
     *
     * Suppose that each stair height is an Y and stair index is X
     * Then we have a function y = f(x), where all water units
     * is a part of a function with local minimum inside. It will look like U shape
     *
     * Our strategy is to find all U shapes. We find it by detecting where
     * 1. function starts decreasing - point startDecreasingIdx
     * 2. then in the local minimum starts increasing - point setStartIncreasingIdx
     * 3. finally starts decreasing again - point finish IncreasingIdx
     *
     *
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


    public static List<Integer> findAllLocalMaximums(
        final List<Integer> stairs) {

        final int size = stairs.size();

        int previousIdx = 0;

        int startsDecrease = -1;

        boolean increasing = false;
        boolean decreasing = false;

        final List<Integer> localMaximumIdx = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            final Integer current = stairs.get(i);
            final Integer previous = stairs.get(previousIdx);

            if (previous > current) {

                if (nowValuesIncreasingButPreviouslyWasDecreasing(startsDecrease, increasing)) {
                    localMaximumIdx.add(previousIdx);
                }

                if (!decreasing) {
                    startsDecrease = previousIdx;
                    decreasing = true;
                    increasing = false;
                }
            }

            if (previous < current) {
                if (!increasing) {
                    decreasing = false;
                    increasing = true;
                }
            }

            // first stair is local maximum if next is decreasing
            if (previousIdx == 0 && decreasing) {
                 localMaximumIdx.add(previousIdx);
            }

            // last stair is local maximum if was increasing
            if (i == size - 1 && increasing) {
                 localMaximumIdx.add(i);
            }


            previousIdx = i;
        }

        return localMaximumIdx;
    }

    private static boolean nowValuesIncreasingButPreviouslyWasDecreasing(
        final int startsDecrease, final boolean increasing) {

        return increasing && startsDecrease != -1;
    }

    /**
     *
     * We calculate how much water between two local maximum in two steps
     * <p>
     * 1. Calculate square of rectangle = a*b, where
     * <p>
     * a = argmin(left_max, right_max) and
     * b = (how many stairs inside between local maximums) = @subArrayBetweenMaximums.size()-2
     *
     * <p>
     * 2. We substract sum of all stairs between local maximums from square of rectangle
     *
     * @param subArrayBetweenMaximums sub array between two local maximums
     * @return amount of water inside this U shape
     */
    public static Integer calculateWaterBetweenLocalMaximum(List<Integer> subArrayBetweenMaximums) {

        final int numberOfStairs = subArrayBetweenMaximums.size();
        final int firstStair = 0;
        final int lastStair = subArrayBetweenMaximums.size() - 1;
        final int numberOfStairsWithoutBounds = numberOfStairs - 2; // 2 bounds, left and right in U

        final int argmin = Math.min(subArrayBetweenMaximums.get(firstStair), subArrayBetweenMaximums.get(lastStair));

        final int sumWithoutBounds = subArrayBetweenMaximums
            .subList(firstStair + 1, lastStair) // exclude first and last elements (bounds)
            .stream().mapToInt(value -> value).sum();

        final int water = numberOfStairsWithoutBounds * argmin - sumWithoutBounds;
        return water;
    }
}
