package kz.c0rp.stairs.water.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kz.c0rp.stairs.water.model.UShaped;

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

        boolean increasing = false;
        boolean decreasing = false;

        final List<UShaped> shapedList = new ArrayList<>();
        UShaped uShaped = new UShaped();

        for (int i = 0; i < stairs.size(); i++) {

            final Integer current = stairs.get(i);
            final Integer previous = stairs.get(previousIdx);

            if (previous > current) {

                if (nowValuesIncreasingButPreviouslyWasDecreasing(increasing)) {
                    uShaped.setFinishIncreasingIdx(previousIdx);
                    shapedList.add(uShaped);
                    uShaped = new UShaped();
                    uShaped.getStairsInShape().add(previous);
                }

                if (!decreasing) {
                    decreasing = true;
                    increasing = false;
                    uShaped.setStartDecreasingIdx(previousIdx);
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
        if (nowValuesIncreasingButPreviouslyWasDecreasing(increasing)) {
            uShaped.setFinishIncreasingIdx(previousIdx);
            shapedList.add(uShaped);
        }
        return shapedList;
    }

    /**
     * Suppose that each stair height is an Y and stair index is X
     * Then we have a function y = f(x), where all water units
     * is a part of a function with local minimum inside.
     *
     * Finds all indexes of local maximums in stairs as a function
     * @param stairs list of stairs
     * @return indexes of stairs that are local maximum
     */
    public static List<Integer> findAllLocalMaximums(
        final List<Integer> stairs) {

        if (stairs.isEmpty()) {
            return new ArrayList<>();
        }

        final List<Integer> localMaximumIdx = extractLocalMaximum(stairs, false);

        final List<Integer> consistsOfLocalMaximums = localMaximumIdx.stream()
            .map(stairs::get)
            .collect(Collectors.toList());

        final List<Integer> reduced = reduceLocalMaximums(consistsOfLocalMaximums);
        if (reduced.isEmpty()) {
            return localMaximumIdx;
        } else {
            return reduced;
        }
    }

    /**
     * Remove local maximums between bigger local maximums
     * @param localMaximums list of local maximums
     * @return reduced local maximums or empty list if reduce does not make sense
     */
    public static List<Integer> reduceLocalMaximums(
        final List<Integer> localMaximums) {

        if (localMaximums.isEmpty()) {
            return new ArrayList<>();
        }

        final List<Integer> localMaximumIdx = extractLocalMaximum(localMaximums, true);

        final List<Integer> consistsOfLocalMaximums = localMaximumIdx.stream()
            .map(localMaximums::get)
            .collect(Collectors.toList());

        final List<Integer> reduced = reduceLocalMaximums(consistsOfLocalMaximums);
        if (reduced.isEmpty()) {
            return new ArrayList<>();
        }

        return consistsOfLocalMaximums;
    }

    /**
     *
     * Suppose that each stair height is an Y and stair index is X
     * Then we have a function y = f(x), where all water units
     * is a part of a function with local minimum inside.
     *
     * Extracts indexes of local maximums in stairs as a function
     * @param stairs list of stairs
     * @param isReducing
     * @return indexes of stairs that are local maximum
     */
    public static List<Integer> extractLocalMaximum(final List<Integer> stairs,
                                                     final boolean isReducing) {

        final int size = stairs.size();

        int previousIdx = 0;

        boolean increasing = false;
        boolean decreasing = false;

        final List<Integer> localMaximumIdx = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            final Integer current = stairs.get(i);
            final Integer previous = stairs.get(previousIdx);

            if (previous > current) {

                if (nowValuesIncreasingButPreviouslyWasDecreasing(increasing)) {
                    localMaximumIdx.add(previousIdx);
                }

                if (!decreasing) {
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

            if (i != 0 && Objects.equals(previous, current) && isReducing) {
                localMaximumIdx.add(i);
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

    /**
     *
     * Extracting all local maximums from stairs and calculate water
     *
     * Takes all consecutive local maximum pair and calculate amount
     * of water between them
     * @param stairs
     * @return
     */
    public static int calculateWaterByLocalMaximum(final List<Integer> stairs) {

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
        return water;
    }

    private static boolean nowValuesIncreasingButPreviouslyWasDecreasing(
        final boolean increasing) {

        return increasing;
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
