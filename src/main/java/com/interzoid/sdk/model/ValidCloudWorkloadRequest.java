package com.interzoid.sdk.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Represents a custom annotation for validating Cloud Workload API requests.
 */
@Constraint(validatedBy = CloudWorkloadRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCloudWorkloadRequest {
    /**
     * Sets the message.
     * @return The message to set.
     */
    String message() default "newTable is required when process is CREATE_TABLE";

    /**
     * Sets the groups.
     * @return The groups to set.
     */
    Class<?>[] groups() default {};

    /**
     * Sets the payload.
     * @return The payload to set.
     */
    Class<? extends Payload>[] payload() default {};
}
