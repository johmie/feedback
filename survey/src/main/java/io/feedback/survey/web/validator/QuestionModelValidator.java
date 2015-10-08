package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;
import io.feedback.survey.web.model.QuestionModel;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class QuestionModelValidator {

    @Autowired
    private ResultValidator resultValidator;

    public void validate(QuestionModel questionModel, Errors errors, Question question) {
        boolean valid = false;
        List<Result> results = questionModel.getResults();
        Iterator<Result> resultsIterator = results.iterator();
        while (resultsIterator.hasNext()) {
            Result result = resultsIterator.next();
            if (resultValidator.isValid(result)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            errors.rejectValue("questionModels[" + question.getId() + "]", "", "No answer selected");
        }
    }
}