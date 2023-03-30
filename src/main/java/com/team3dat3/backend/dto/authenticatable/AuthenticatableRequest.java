package com.team3dat3.backend.dto.authenticatable;

import java.util.Arrays;
import java.util.List;

import com.team3dat3.backend.entity.Authenticatable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authenticatable request
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatableRequest {

    protected String username;
    protected String password;

    protected List<String> roles;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public AuthenticatableRequest(Authenticatable authenticatable) {
        username = authenticatable.getUsername();
        password = authenticatable.getPassword();
        roles = authenticatable.getRoles();
    }

    public Authenticatable toAuthenticatable() {
        Authenticatable authenticatable = new Authenticatable();
        copyTo(authenticatable);
        return authenticatable;
    }

    public void copyTo(Authenticatable authenticatable) {
        authenticatable.setUsername(username);
        authenticatable.setPassword(password);
        authenticatable.setAccountNonExpired(isAccountNonExpired);
        authenticatable.setAccountNonLocked(isAccountNonLocked);
        authenticatable.setCredentialsNonExpired(isCredentialsNonExpired);
        authenticatable.setEnabled(isEnabled);
        authenticatable.setRoles(roles);
    }
}
