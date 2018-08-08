package kz.c0rp.stairs.water.spring;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import kz.c0rp.stairs.water.utils.StairwaysManager;
import static kz.c0rp.stairs.water.utils.StairwaysManager.calculateWaterBetweenLocalMaximum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = Logger.getLogger("API");

    @RequestMapping(value = "/stairsArray", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public @ResponseBody Integer message(@RequestBody List<Integer> stairs) {

        if (stairs==null || stairs.isEmpty() || stairs.stream().anyMatch(Objects::isNull)) return 0;

        logger.info("RequestBody=" + stairs.toString());


        return StairwaysManager.findAllShapesWithWater(stairs).stream()
            .mapToInt(v -> calculateWaterBetweenLocalMaximum(v.getStairsInShape()))
            .sum();
    }
}
