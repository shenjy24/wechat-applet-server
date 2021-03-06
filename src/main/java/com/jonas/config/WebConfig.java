package com.jonas.config;

import com.jonas.config.access.AccessInterceptor;
import com.jonas.config.request.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shenjy
 * @date 2020/9/1
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AccessInterceptor accessInterceptor;
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
        registry.addInterceptor(requestInterceptor);
    }
}
