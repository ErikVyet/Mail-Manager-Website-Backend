package code.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import code.handlers.ApiKeyInterceptorHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private ApiKeyInterceptorHandler apiKeyInterceptorHandler;

    public WebConfig(ApiKeyInterceptorHandler apiKeyInterceptorHandler) {
        this.apiKeyInterceptorHandler = apiKeyInterceptorHandler;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT", "OPTIONS");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.apiKeyInterceptorHandler)
                .addPathPatterns("/vletter/api/v1/**")
                .excludePathPatterns("/vletter/api/v1/health/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    

}