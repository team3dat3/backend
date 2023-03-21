package com.team3dat3.backend.api.v1;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation response
 */

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3dat3.backend.dto.error.ErrorResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    
    @GetMapping
    public ErrorResponse handleError(HttpServletRequest request) {
        Integer statusCode = getStatus(request);
        // Convert to Spring HttpStatus to get name and reason
        org.springframework.http.HttpStatus status = org.springframework.http.HttpStatus.valueOf(statusCode);

        return new ErrorResponse(status.value(), status.name(), status.getReasonPhrase());
    }

    private Integer getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null) {
            try {
                return statusCode;
            } catch (IllegalArgumentException ex) {
                // do nothing
            }
        }
        return HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }
}
