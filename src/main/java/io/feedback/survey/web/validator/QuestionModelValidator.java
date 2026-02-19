package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;
import io.feedback.survey.web.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

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
        int countValidAnswers = 0;
        HashMap<Long, Integer> countMapAnsweredRows = new HashMap<>();
        for (int i = 0; i < questionModel.getResults().size(); i++) {
            Result result = questionModel.getResults().get(i);
            if (!question.isMatrix()) {
                if (!getResultValidator().isValid(result)) {
                    errors.rejectValue("questionModels[" + question.getId() + "].results[" + i + "]",
                            "error.answer_is_invalid");
                } else if (isAnswerSelected(result)) {
                    countValidAnswers++;
                }
            } else {
                if (isAnswerSelected(result)) {
                    Integer countAnswersForRow = countMapAnsweredRows.get(result.getAnswerRow().getId());
                    if (countAnswersForRow == null) {
                        countAnswersForRow = 0;
                    }
                    countMapAnsweredRows.put(
                            result.getAnswerRow().getId(),
                            countAnswersForRow + 1
                    );
                }
            }
        }
        if (!question.isMatrix() && countValidAnswers < 1) {
            errors.rejectValue(
                    "questionModels[" + question.getId() + "]", "error.question_not_answered"
            );
        } else if (question.isMatrix() && !hasOneOrMoreAnswersForEveryRow(question, countMapAnsweredRows)) {
            errors.rejectValue(
                    "questionModels[" + question.getId() + "]", "error.question_not_answered"
            );
        }
    }

    private boolean isAnswerSelected(Result result) {
        return result.getAnswer() != null
                && result.getAnswer().getId() != null;
    }

    private boolean hasOneOrMoreAnswersForEveryRow(
            Question question,
            Map<Long, Integer> countMapAnsweredRows) {
        return countMapAnsweredRows.size() == question.getAnswerRows().size();
    }
}