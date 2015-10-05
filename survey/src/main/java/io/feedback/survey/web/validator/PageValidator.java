package io.feedback.survey.web.validator;

import io.feedback.survey.web.model.PageFormModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PageValidator implements Validator {

    @Autowired
    BeanValidator beanValidator;

    @Override
    public boolean supports(Class<?> clazz) {
     return PageFormModel.class.equals(clazz);
     //return PageModel.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate(Object target, Errors errors)
    {
        PageFormModel pageModel = (PageFormModel) target;
        beanValidator.validate(pageModel.getQuestions(), errors, "questions");
    }
}