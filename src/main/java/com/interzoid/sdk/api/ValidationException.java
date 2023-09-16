package com.interzoid.sdk.api;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ValidationException extends RuntimeException {
    private Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(String message, Set<? extends ConstraintViolation<?>> violations) {
        super(message);
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}
