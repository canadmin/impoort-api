package com.impoort.impoortapi.config;

import com.impoort.impoortapi.security.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JWTConfiguration {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthFilter filter = new JwtAuthFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }
}
