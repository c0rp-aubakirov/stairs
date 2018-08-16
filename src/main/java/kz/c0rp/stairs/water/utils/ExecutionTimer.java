package kz.c0rp.stairs.water.utils;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by admin230 on 9/17/15.
 */
public class ExecutionTimer {
    public static final Logger logger = Logger.getLogger(ExecutionTimer.class.getName());

    public static <R> R executeFileTimer(Callable<R> f, String name) throws Exception {
        final Stopwatch timer = Stopwatch.createUnstarted();
        timer.start();
        R r = f.call();
        timer.stop();
        LoggerEnabler.logIfEnabled(logger, Level.INFO, "Time for file " + name + " - " + timer.toString());
        return r;
    }

    public static <R> R executeFunctionWithTimer(Callable<R> f) throws Exception {
        final Stopwatch timer = Stopwatch.createUnstarted();
        timer.start();
        R r = f.call();
        timer.stop();
        LoggerEnabler.logIfEnabled(logger, Level.INFO, timer.toString());
        return r;
    }

    public static <R> long executeVoidFunctionWithTimer(Callable<R> f) throws Exception {
        final Stopwatch timer = Stopwatch.createUnstarted();
        timer.start();
        R r = f.call();
        timer.stop();
        LoggerEnabler.logIfEnabled(logger, Level.INFO, timer.toString());
        return timer.elapsed(TimeUnit.MICROSECONDS);
    }
}
