package code.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import code.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptorHandler implements HandlerInterceptor {

    @Value("${spring.application.api-key}")
    protected String apiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String key = request.getHeader("Api-Key");
        if (!this.apiKey.equals(key)) {
            throw new UnauthorizedException("You are not authorized");
        }
        return true;
    }

}