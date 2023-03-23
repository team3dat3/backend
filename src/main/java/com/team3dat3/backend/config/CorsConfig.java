package com.team3dat3.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
  /*  Ville nedenstående være godt nok eller skal det være mere specifikt som det der ikke er udkommenteret
      registry.addMapping("/api/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app");*/

      registry.addMapping("/api/movies/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/movies");
      //.allowedMethods("GET"); måske ikke nødvendig..?

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

      registry.addMapping("/api/shows/theater/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/theater");

      registry.addMapping("/api/shows/seatrows/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/seatrows");

      registry.addMapping("/api/shows/seats/**")
          .allowedOrigins("https://urchin-app-9ivwp.ondigitalocean.app/seats");
  }
}
