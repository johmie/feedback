package io.feedback.core.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class BeanValidator implements org.springframework.validation.Validator,
        InitializingBean {

    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation
                .buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    public void validate(Object target, Errors errors, String prefix) {
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath()
                    .toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(prefix + "." + propertyPath, "", message);
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, "");
    }
}