package com.team3dat3.backend.dto.authenticatable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authentication request
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;
}
