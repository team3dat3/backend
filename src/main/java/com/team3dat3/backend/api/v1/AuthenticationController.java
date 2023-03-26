package com.team3dat3.backend.api.v1;

import org.springframework.web.bind.annotation.*;

import com.team3dat3.backend.dto.authenticatable.AuthenticationRequest;
import com.team3dat3.backend.dto.authenticatable.AuthenticationResponse;
import com.team3dat3.backend.service.AuthenticationService;

@RequestMapping("/v1/authentication")
@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {        
        return authenticationService.authenticate(authenticationRequest);
    }
}
