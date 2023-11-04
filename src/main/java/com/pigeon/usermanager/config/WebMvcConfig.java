package com.pigeon.usermanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("#{'${cors.registry.allowed-origins}'.split(', ')}")
    private String[] origins;

    @Value("#{'${cors.registry.methods}'.split(', ')}")
    private String[] methods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("CORS config: allowed-origins={}", Arrays.toString(origins));
        registry.addMapping("/**").allowedMethods(methods).allowedHeaders("*").allowedOrigins(origins);
    }
}
