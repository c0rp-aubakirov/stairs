package kz.c0rp.stairs.water;

import java.util.List;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 *
 * We trying to find all u-shaped figures built by stairs
 * U shape is like a bowl
 *
 *
 *
 *
 *      this is startDecreasingIdx point
 *     \/
 *
 *     d       b < this is finishIncreasingIdx point
 *     S       S
 *     S       S
 *     S       S
 *     S       S
 *      S     S
 *       "sss"
 *
 *         /\
 *          this is startIncreasingIdx point
 *
 */
public class UShaped {

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
     * Here we store all values between U shape bounds, all except bounds
     */
    private List<Integer> valuesInsideBowl; // values that between @startDecreasingIdx and @startIncreasingIdx


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

    public List<Integer> getValuesInsideBowl() {

        return valuesInsideBowl;
    }

    public void setValuesInsideBowl(final List<Integer> valuesInsideBowl) {

        this.valuesInsideBowl = valuesInsideBowl;
    }
}
