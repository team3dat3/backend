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
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/");
  }
}
