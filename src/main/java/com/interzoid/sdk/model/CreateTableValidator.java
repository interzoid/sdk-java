package com.interzoid.sdk.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreateTableValidator implements ConstraintValidator<ValidCreateTableRequest, CloudWorkloadRequest> {

    @Override
    public boolean isValid(CloudWorkloadRequest request, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        boolean valid = true;

        if (request.getProcess() == Process.CREATE_TABLE && (request.getNewTable() == null || request.getNewTable().isBlank())) {
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



