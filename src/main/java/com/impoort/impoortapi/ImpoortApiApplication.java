package com.impoort.impoortapi;

import com.impoort.impoortapi.security.JwtAuthFilter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImpoortApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImpoortApiApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    //Jwt Bean api güvenliği için
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthFilter filter = new JwtAuthFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }
}
