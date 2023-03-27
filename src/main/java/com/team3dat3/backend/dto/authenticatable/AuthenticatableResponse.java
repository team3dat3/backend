package com.team3dat3.backend.dto.authenticatable;

import java.util.List;

import com.team3dat3.backend.entity.Authenticatable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authenticatable response
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatableResponse {

    private String username;
    private String password;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    private List<String> roles;

    public AuthenticatableResponse(Authenticatable authenticatableser) {
        username = authenticatableser.getUsername();
        password = authenticatableser.getPassword();
        isAccountNonExpired = authenticatableser.isAccountNonExpired();
        isAccountNonLocked = authenticatableser.isAccountNonLocked();
        isCredentialsNonExpired = authenticatableser.isCredentialsNonExpired();
        isEnabled = authenticatableser.isEnabled();
        roles = authenticatableser.getRoles();
    }
}
