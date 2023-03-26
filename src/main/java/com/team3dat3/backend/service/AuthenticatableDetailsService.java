package com.team3dat3.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team3dat3.backend.entity.Authenticatable;
import com.team3dat3.backend.repository.AuthenticatableRepository;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authenticatable details service
 */

@Service
public class AuthenticatableDetailsService implements UserDetailsService {
    private AuthenticatableRepository authenticatableRepository;

    public AuthenticatableDetailsService(AuthenticatableRepository authenticatableRepository) {
        this.authenticatableRepository = authenticatableRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {    
        Optional<Authenticatable> authenticatableOpt = authenticatableRepository.findById(username);
        if (authenticatableOpt.isEmpty())
            throw new UsernameNotFoundException("Authenticatable doesn't exist!");
        return authenticatableOpt.get();
    }
}
