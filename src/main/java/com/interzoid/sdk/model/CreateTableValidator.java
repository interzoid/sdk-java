package com.interzoid.sdk.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreateTableValidator implements ConstraintValidator<ValidCreateTableRequest, CloudWorkloadRequest> {

    @Override
    public void initialize(ValidCreateTableRequest constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(CloudWorkloadRequest request, ConstraintValidatorContext context) {
        // If process is CREATE_TABLE, newTable must not be blank
        return request.getProcess() != Process.CREATE_TABLE || (request.getNewTable() != null && !request.getNewTable().isBlank());
    }
}

