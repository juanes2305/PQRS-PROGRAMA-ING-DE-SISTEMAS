package com.pqrs_backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String url = "https://pqrs-ufps.firebaseapp.com/?_gl=1*153cw15*_ga*MTIxNjE5MDQ5NC4xNjg2NjE4NTA0*_ga_CW55HF8NVT*MTY4NjYxODUwNS4xLjEuMTY4NjYxODY5NS4wLjAuMA.."; //SE CAMBIA EN PRODUCCION

        registry.addMapping("/login").allowedOrigins(url).allowedOrigins(url);

    }

}
