package com.jonas.config;

import com.jonas.config.access.AccessInterceptor;
import com.jonas.config.request.RequestInterceptor;
import com.jonas.config.request.UserArgResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author shenjy
 * @date 2020/9/1
 * @description
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AccessInterceptor accessInterceptor;

    private final RequestInterceptor requestInterceptor;

    private final UserArgResolver userArgResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
        registry.addInterceptor(requestInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgResolver);
    }
}
