package com.interzoid.sdk.api.exceptions;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

/**
 * Exception thrown when request validation fails.
 */
public class ValidationException extends RuntimeException {
    /**
     * The set of violations.
     */
    private final Set<? extends ConstraintViolation<?>> violations;

    /**
     * Constructs a new ValidationException with the specified message and set of violations.
     *
     * @param message    The message to set.
     * @param violations The set of violations to set.
     */
    public ValidationException(String message, Set<? extends ConstraintViolation<?>> violations) {
        super(message);
        this.violations = violations;
    }

    /**
     * Gets the set of violations.
     *
     * @return The set of violations.
     */
    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}
