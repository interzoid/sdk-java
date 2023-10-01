package com.interzoid.sdk.model;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CreateTableValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCreateTableRequest {
    String message() default "newTable is required when process is CREATE_TABLE";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
