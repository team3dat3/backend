package com.team3dat3.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 * Author: Thomas S. Andersen
 * Date: 23/03/2023
 * Description: CORS config
 */

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    private static final String[] ALLOWED_ORIGINS = new String[] {
        "https://bergandersen.com",
        "https://www.bergandersen.com",
        "http://localhost:5500",
        "http://127.0.0.1:5500"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
          .allowedOrigins(ALLOWED_ORIGINS);
    }
  }
