package com.team3dat3.backend.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.team3dat3.backend.dto.authenticatable.AuthenticationRequest;
import com.team3dat3.backend.dto.authenticatable.AuthenticationResponse;
import com.team3dat3.backend.entity.Authenticatable;

import static java.util.stream.Collectors.joining;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authentication service
 */

@Service
public class AuthenticationService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtEncoder jwtEncoder;

    @Value("${app.token-issuer}")
    private String tokenIssuer;

    @Value("${app.token-expiration}")
    private long tokenExpiration;
    
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()); 
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        
        Authenticatable user = (Authenticatable) authentication.getPrincipal();
        String token = getToken(authentication, user);

        return new AuthenticationResponse(token);
    }

    private String getToken(Authentication authentication, Authenticatable user) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(tokenIssuer)  //Only this for simplicity
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenExpiration))
                .subject(user.getUsername())
                .claim("roles",scope)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

}
