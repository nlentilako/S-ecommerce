package com.ecommerce.ecommerceapp.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse() {}

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    // Getters and setters
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}