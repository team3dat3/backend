package com.team3dat3.backend.dto.error;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Error response
 */

import lombok.*;
 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String type;
    private String message;
}
