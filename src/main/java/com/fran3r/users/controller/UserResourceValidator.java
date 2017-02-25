package com.fran3r.users.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Component
public class UserResourceValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return UserResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthDate", "error.messenger.shift.empty");
        UserResource resource = (UserResource) target;
    }
}
