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
