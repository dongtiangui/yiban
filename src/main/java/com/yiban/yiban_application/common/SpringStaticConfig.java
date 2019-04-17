package com.yiban.yiban_application.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringStaticConfig implements WebMvcConfigurer {

    private final YbHandlerInterceptor ybHandlerInterceptor;

    @Autowired
    public SpringStaticConfig(YbHandlerInterceptor ybHandlerInterceptor) {
        this.ybHandlerInterceptor = ybHandlerInterceptor;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/META-INF/resources/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(ybHandlerInterceptor).addPathPatterns("/admin/**");

    }
}
