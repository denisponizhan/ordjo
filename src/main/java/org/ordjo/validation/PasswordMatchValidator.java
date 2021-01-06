package org.ordjo.validation;

import org.ordjo.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, User> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        String plainPassword = user.getPlainPassword();
        String repeatPassword = user.getRepeatPassword();

        if(plainPassword == null || plainPassword.length() == 0) {
            return true;
        }

        if(plainPassword == null || !plainPassword.equals(repeatPassword)) {
            return false;
        }

        return true;
    }
}
