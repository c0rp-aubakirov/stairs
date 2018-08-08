package kz.c0rp.stairs.water.spring;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import kz.c0rp.stairs.water.calculator.WaterCalculator;
import kz.c0rp.stairs.water.calculator.WaterCalculatorEnum;
import kz.c0rp.stairs.water.calculator.WaterCalculatorFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = Logger.getLogger("API");

    @ResponseBody
    @RequestMapping(value = "/stairsArray", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Integer message(@RequestBody List<Integer> stairs, @RequestParam("type")
                                         WaterCalculatorEnum type) {

        if (stairs==null || stairs.isEmpty() || stairs.stream().anyMatch(Objects::isNull)) return 0;

        final WaterCalculator calculator = WaterCalculatorFactory.buildBy(type);

        return calculator.calculateWaterInStairs(stairs);
    }
}
