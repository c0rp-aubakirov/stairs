package kz.c0rp.stairs.water;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 *
 * We are trying to find all u-shaped figures built by stairs
 * U shape is something like a parabola
 *
 *
 *
 *
 *      this is {@link #startDecreasingIdx} point
 *     \/
 *
 *     d       b < this is {@link #finishIncreasingIdx} point
 *     S       S
 *     S       S
 *     S       S
 *     S       S
 *      S     S
 *       "sss"
 *
 *         /\
 *          this is {@link #startIncreasingIdx} point
 *
 */
public class UShaped {

    public UShaped(final Integer startDecreasingIdx,
                   final Integer startIncreasingIdx,
                   final Integer finishIncreasingIdx,
                   final List<Integer> valuesInsideBowl) {

        this.startDecreasingIdx = startDecreasingIdx;
        this.startIncreasingIdx = startIncreasingIdx;
        this.finishIncreasingIdx = finishIncreasingIdx;
        this.valuesInsideBowl = valuesInsideBowl;
    }

    public UShaped() {

    }

    /**
     * This is the point where U shape starts
     * it is looks like Local Maximum and values starts decreasing
     */
    private Integer startDecreasingIdx;

    /**
     * This is the point where U shapes minimum
     * it is looks like Local Minimum and values starts increasing
     */
    private Integer startIncreasingIdx;

    /**
     * This is the point where U shape finishes
     * it is looks like Local Maximum and values starts decreasing again
     */
    private Integer finishIncreasingIdx;

    /**
     * Here we store all values between @startDecreasingIdx and @finishIncreasingIdx inclusive
     */
    private List<Integer> valuesInsideBowl = new ArrayList<>();

    /**
     *
     * We calculate how much water in this shape in two steps
     * <p>
     * 1. Calculate square of rectangle a*b, where
     * <p>
     * a = argmin(left_bounds, right_bound) and
     * b = (how many stairs inside U shape without bounds) or @valuesInsideBowl.size()-2
     *
     * <p>
     * 2. We substract sum(@valuesInsideBowl) from square of rectangle
     *
     * @return amount of water inside this U shape
     */
    public Integer calculateAmountOfWaterInside() {

        final int numberOfStairs = valuesInsideBowl.size();
        final int firstStair = 0;
        final int lastStair = valuesInsideBowl.size() - 1;
        final int numberOfStairsWithoutBounds = numberOfStairs - 2; // 2 bounds, left and right in U

        final int argmin = Math.min(valuesInsideBowl.get(firstStair), valuesInsideBowl.get(lastStair));

        final int sumWithoutBounds = valuesInsideBowl
            .subList(firstStair + 1, lastStair) // exclude first and last elements (bounds)
            .stream().mapToInt(value -> value).sum();

        final int water = numberOfStairsWithoutBounds * argmin - sumWithoutBounds;
        return water;
    }

    public Integer getFinishIncreasingIdx() {

        return finishIncreasingIdx;
    }

    public void setFinishIncreasingIdx(final Integer finishIncreasingIdx) {

        this.finishIncreasingIdx = finishIncreasingIdx;
    }

    public Integer getStartDecreasingIdx() {

        return startDecreasingIdx;
    }

    public void setStartDecreasingIdx(final Integer startDecreasingIdx) {

        this.startDecreasingIdx = startDecreasingIdx;
    }

    public Integer getStartIncreasingIdx() {

        return startIncreasingIdx;
    }

    public void setStartIncreasingIdx(final Integer startIncreasingIdx) {

        this.startIncreasingIdx = startIncreasingIdx;
    }

    public List<Integer> getStairsInShape() {

        return valuesInsideBowl;
    }

    public void setValuesInsideBowl(final List<Integer> valuesInsideBowl) {

        this.valuesInsideBowl = valuesInsideBowl;
    }
}
