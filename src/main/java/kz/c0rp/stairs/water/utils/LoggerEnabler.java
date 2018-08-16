package kz.c0rp.stairs.water.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Sanzhar Aubakirov
 * Date: 9/17/15
 */
public class LoggerEnabler {
    public static volatile boolean enableDebugMessage = true;


    public static void logIfEnabled(Logger logger, Level level, String msg) {
        if (enableDebugMessage) {
            logger.log(level, msg);
        }
    }

    public static void log(Logger logger, Level level, String msg) {
            logger.log(level, msg);
    }

    public boolean isEnableDebugMessage() {
        return enableDebugMessage;
    }

}
