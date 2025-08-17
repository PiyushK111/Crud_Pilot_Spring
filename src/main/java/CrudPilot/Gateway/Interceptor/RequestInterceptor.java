package CrudPilot.Gateway.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    This part can be used to add Authentication add for reference.
 */

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final int TIMEOUT = 2000; // Timeout in milliseconds (5 seconds)

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.setAttribute("org.apache.catalina.ASYNC_TIMEOUT", TIMEOUT);
        return true;
    }
}
