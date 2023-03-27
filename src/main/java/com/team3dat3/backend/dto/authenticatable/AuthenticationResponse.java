package com.team3dat3.backend.dto.authenticatable;

import lombok.*;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Authentication response
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
}
