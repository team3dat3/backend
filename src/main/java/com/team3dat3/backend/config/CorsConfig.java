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
        "https://urchin-app-9ivwp.ondigitalocean.app/movies%22",
        "https://www.urchin-app-9ivwp.ondigitalocean.app/movies%22",
        "http://localhost"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
          .allowedOrigins(ALLOWED_ORIGINS);
    }
  }
