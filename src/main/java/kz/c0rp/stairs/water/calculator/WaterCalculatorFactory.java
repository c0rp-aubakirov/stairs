package kz.c0rp.stairs.water.calculator;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class WaterCalculatorFactory {

    public static WaterCalculator buildBy(final WaterCalculatorEnum calculator) {

        switch (calculator) {
            case USHAPE:
                return new UShapeWaterCalc();
            case LOCAL_MAXIMUM:
                return new LocalMaximumWaterCalc();
            case GEEKS:
                return new GeeksForGeeks();
            default:
                throw new IllegalStateException("Please create implementation for all new ENUMS");
        }
    }

}
