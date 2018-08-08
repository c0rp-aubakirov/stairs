package kz.c0rp.stairs.water.spring;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import kz.c0rp.stairs.water.utils.StairwaysManager;
import static kz.c0rp.stairs.water.utils.StairwaysManager.calculateWaterBetweenLocalMaximum;
import static kz.c0rp.stairs.water.utils.StairwaysManager.findAllLocalMaximums;
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


//        return StairwaysManager.findAllShapesWithWater(stairs).stream()
//            .mapToInt(v -> calculateWaterBetweenLocalMaximum(v.getStairsInShape()))
//            .sum();

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
}
