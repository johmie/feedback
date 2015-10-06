package io.feedback.survey.web.validator;

import java.util.Iterator;

import io.feedback.survey.web.model.PageFormModel;
import io.feedback.survey.web.model.QuestionFormModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PageFormValidator implements Validator {

    @Autowired
    QuestionFormValidator questionFormValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return PageFormModel.class.equals(clazz);
        // return PageModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PageFormModel pageFormModel = (PageFormModel) target;
        Iterator<QuestionFormModel> questionsIterator = pageFormModel
                .getQuestions().iterator();
        int questionIndex = 0;
        while (questionsIterator.hasNext()) {
            QuestionFormModel questionFormModel = questionsIterator.next();
            questionFormValidator.validateWithBeanValidator(questionFormModel,
                    errors, "questions[" + questionIndex + "]");
            questionIndex++;
        }
    }
}