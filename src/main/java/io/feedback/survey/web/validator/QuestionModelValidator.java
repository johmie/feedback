package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;
import io.feedback.survey.validator.ResultValidator;
import io.feedback.survey.web.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class QuestionModelValidator {

    private ResultValidator resultValidator;

    public ResultValidator getResultValidator() {
        return resultValidator;
    }

    @Autowired
    public void setResultValidator(ResultValidator resultValidator) {
        this.resultValidator = resultValidator;
    }

    public void validate(QuestionModel questionModel, Errors errors, Question question) {
        int countSelectedValidAnswers = 0;
        for (int i = 0; i < questionModel.getResults().size(); i++) {
            Result result = questionModel.getResults().get(i);
            if (!resultValidator.isValid(result)) {
                errors.rejectValue("questionModels[" + question.getId() + "].results[" + i + "]", "", "Invalid answer");
            } else if (isAnswerSelected(result)) {
                countSelectedValidAnswers++;
            }
        }
        if (countSelectedValidAnswers < 1) {
            errors.rejectValue("questionModels[" + question.getId() + "]", "", "No answer selected");
        }
    }

    private boolean isAnswerSelected(Result result) {
        return result.getAnswer() != null && result.getAnswer().getId() != null;
    }
}