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

    public AuthenticatableRequest(Authenticatable authenticatable) {
        username = authenticatable.getUsername();
        password = authenticatable.getPassword();
        roles = authenticatable.getRoles();
    }

    public Authenticatable toAuthenticatable() {
        return new Authenticatable(username, password, true, true, true, true, roles);
    }

    public void copyTo(Authenticatable authenticatable) {
        authenticatable.setUsername(username);
        authenticatable.setPassword(password);
        authenticatable.setAccountNonExpired(true);
        authenticatable.setAccountNonLocked(true);
        authenticatable.setCredentialsNonExpired(true);
        authenticatable.setEnabled(true);
        authenticatable.setRoles(roles);
    }
}
