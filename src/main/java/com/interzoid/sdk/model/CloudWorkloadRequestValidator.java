package com.interzoid.sdk.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Interzoid
 * @version 1.1
 * Validates the CreateTableRequest object
 * @see CloudWorkloadRequest
 */
public class CloudWorkloadRequestValidator implements ConstraintValidator<ValidCloudWorkloadRequest, CloudWorkloadRequest> {

    /**
     * Checks if the request is valid
     * @param request object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return true if the request is valid, false otherwise
     */
    @Override
    public boolean isValid(CloudWorkloadRequest request, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        boolean valid = true;

        if (request.getProcess() == Process.CREATE_TABLE && (request.getNewTableName() == null || request.getNewTableName().isBlank())) {
            context.buildConstraintViolationWithTemplate("newTable is required when process is CREATE_TABLE")
                    .addConstraintViolation();
            valid = false;
        }

        if (request.getProcess() != Process.MATCH_REPORT && (request.isJson() || request.isHtml())) {
            context.buildConstraintViolationWithTemplate("Setting json or html to true is only valid when process is MATCH_REPORT")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}



