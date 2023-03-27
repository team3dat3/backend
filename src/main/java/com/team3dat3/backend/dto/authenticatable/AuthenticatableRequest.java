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
 * Description: Authenticatable request
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatableRequest {

    protected String username;
    protected String password;

    protected boolean isAccountNonExpired = true;
    protected boolean isAccountNonLocked = true;
    protected boolean isCredentialsNonExpired = true;
    protected boolean isEnabled = true;

    protected List<String> roles;

    public AuthenticatableRequest(Authenticatable authenticatable) {
        username = authenticatable.getUsername();
        password = authenticatable.getPassword();
        isAccountNonExpired = authenticatable.isAccountNonExpired();
        isAccountNonLocked = authenticatable.isAccountNonLocked();
        isCredentialsNonExpired = authenticatable.isCredentialsNonExpired();
        isEnabled = authenticatable.isEnabled();
        roles = authenticatable.getRoles();
    }

    public Authenticatable toAuthenticatable() {
        return new Authenticatable(username, password, isAccountNonExpired, isAccountNonLocked, 
            isCredentialsNonExpired, isEnabled, roles);
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
