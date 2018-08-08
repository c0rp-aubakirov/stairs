package kz.c0rp.stairs.water.spring;

import java.util.logging.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
@Component
public class StartIndicator implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = Logger.getLogger("API");


    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        final String hello = "\n****\n\n" +
            "** Hello dear user. To start application open this link in browser:\n" +
            "** Default link is http://localhost:8080/" +
            "\n\n****";
        logger.info(hello);
    }
}
