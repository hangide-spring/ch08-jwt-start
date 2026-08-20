package com.metacoding.blog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.metacoding.blog.filter.JwtAuthFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter() {
        FilterRegistrationBean<JwtAuthFilter> bean = new FilterRegistrationBean<>(new JwtAuthFilter());
        // TODO
        return bean;
    }
}
