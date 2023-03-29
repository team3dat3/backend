package com.team3dat3.backend.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import com.team3dat3.backend.error.CustomOAuth2AccessDeniedHandler;
import com.team3dat3.backend.error.CustomOAuth2AuthenticationEntryPoint;
import com.team3dat3.backend.service.AuthenticatableDetailsService;

import java.util.Collections;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Security config
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private AuthenticatableDetailsService authenticatableDetailsService;

    @Value("${app.secret-key}")
    private String tokenSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //This line is added to make the h2-console work (if needed)
        http.headers().frameOptions().disable();
        http
                .cors().and().csrf().disable()

                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //REF: https://mflash.dev/post/2021/01/19/error-handling-for-spring-security-resource-server/
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler())
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(authenticationConverter());

        http.authorizeHttpRequests((authorize) -> authorize
                //Required in order to use the h2-console
                .requestMatchers("/h2*/**").permitAll()
                .requestMatchers("/error").permitAll()

                .requestMatchers(HttpMethod.POST, "/v1/authentication").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/anonymous/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/v1/members/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/v1/member/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/v1/member/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/v1/member/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/v1/member/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(HttpMethod.OPTIONS, "/v1/member/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(HttpMethod.GET, "/v1/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/v1/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/v1/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/v1/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/v1/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.OPTIONS, "/v1/admin/**").hasRole("ADMIN")
                
        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
        jwtGrantedAuthoritiesConverter
        );
        return jwtAuthenticationConverter;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth
        .userDetailsService(authenticatableDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(
        Collections.singletonList(authenticationProvider())
        );
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authenticatableDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(secretKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
        new ImmutableSecret<SecurityContext>(secretKey())
        );
    }
}
