package kz.c0rp.stairs.water;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kz.c0rp.stairs.water.calculator.WaterCalculatorEnum;
import kz.c0rp.stairs.water.calculator.WaterCalculatorFactory;
import kz.c0rp.stairs.water.utils.ExecutionTimer;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class TimerTest {
    private static final Logger logger = Logger.getLogger("TimerTest");

    @DataProvider(name = "Stairs")

    public static Object[][] credentials() {


        final List<Integer> randArray = new ArrayList<>();
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < 100000; i++) {
            final BigInteger e = new BigInteger(8, random);
            randArray.add(e.intValue());
        }

        return new Object[][] {
            {randArray},
        };

    }

    @Test(dataProvider = "Stairs")
    public void stressTest(List<Integer> stairs) throws Exception {

        final WaterCalculatorEnum geeks = WaterCalculatorEnum.GEEKS;
        final WaterCalculatorEnum localMaximum = WaterCalculatorEnum.LOCAL_MAXIMUM;

        final Integer geeksWater = executeWithTimer(stairs, geeks);
        final Integer localMaximumWater = executeWithTimer(stairs, localMaximum);

//        assertEquals(geeksWater, localMaximumWater, "Different algos water should be equal");
    }

    private Integer executeWithTimer(final List<Integer> stairs,
                                     final WaterCalculatorEnum geeks) throws Exception {

        logger.info("Start measure " + geeks.name() + " algorithm");

        final Integer water = ExecutionTimer
            .executeFunctionWithTimer(() -> WaterCalculatorFactory.buildBy(geeks).calculateWaterInStairs(stairs));

        logger.info("Amount of " +geeks.name()+" water is " + water);

        return water;
    }
}
