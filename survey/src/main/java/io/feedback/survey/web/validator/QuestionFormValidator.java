package io.feedback.survey.web.validator;

import java.util.Iterator;

import io.feedback.core.validator.BeanValidator;
import io.feedback.survey.entity.Result;
import io.feedback.survey.web.model.QuestionFormModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class QuestionFormValidator {

    private BeanValidator beanValidator;

    public BeanValidator getBeanValidator() {
        return beanValidator;
    }

    @Autowired
    public void setBeanValidator(BeanValidator beanValidator) {
        this.beanValidator = beanValidator;
    }

    public void validateWithBeanValidator(Object target, Errors errors,
            String prefix) {
        beanValidator.validate(target, errors, prefix);
        QuestionFormModel questionFormModel = (QuestionFormModel) target;
        Iterator<Result> resultsIterator = questionFormModel.getResults()
                .iterator();
        int resultIndex = 0;
        while (resultsIterator.hasNext()) {
            Result result = resultsIterator.next();
            beanValidator.validate(result, errors, prefix + ".results["
                    + resultIndex + "]");
            resultIndex++;
        }
    }
}