package com.example.postsapi.common.validation;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidate<T> {
    private final Validator validator;

    protected SelfValidate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    protected void validateThis() {
        Set<ConstraintViolation<SelfValidate<T>>> violations = this.validator.validate(this, new Class[0]);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
