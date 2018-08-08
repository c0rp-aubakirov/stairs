package kz.c0rp.stairs.water.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * Created by ruslan.babich on 22.01.2018.
 */

@Component
public class AllRequestLoggingFilter extends AbstractRequestLoggingFilter {
    private static final Logger logger = Logger.getLogger("API");

    private String host;
    private String port;

    @Autowired
    public AllRequestLoggingFilter(Environment environment) {
        this.setIncludeQueryString(true);
        this.setIncludePayload(true);

        try {
            this.host = InetAddress.getLocalHost().getHostAddress();
            this.port = environment.getProperty("server.port");
        } catch (UnknownHostException e) {
            logger.log(Level.SEVERE,"Error inside MdcInterceptor constructor", e);
        }
    }


    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    /**
     * Writes a log message before the request is processed.
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        MDC.put("api.host", this.host);
        MDC.put("api.port", this.port);
        MDC.put("user.name", request.getUserPrincipal()!=null?request.getUserPrincipal().getName():null);
        MDC.put("user.url", request.getHeader("referer"));
        MDC.put("api.uri", request.getRequestURI());
        MDC.put("api.method", request.getMethod());
        MDC.put("user.browser", request.getHeader("user-agent"));

        logger.info(message);
    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}
