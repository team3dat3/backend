package com.team3dat3.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

//Not done
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app");

      registry.addMapping("/api/movies/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/movies");

      registry.addMapping("/api/v1/reservations/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/reservations");

      registry.addMapping("/api/v1/users/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/users");

      registry.addMapping("/api/v1/errors")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/errors");

      registry.addMapping("/api/v1/coupons/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/coupons");

      registry.addMapping("/api/shows/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/shows");
  }
}
