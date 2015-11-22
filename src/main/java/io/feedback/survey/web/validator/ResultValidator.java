package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultValidator {

    private AnswerRepository answerRepository;

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public boolean isValid(Result result) {
        if (isAnswerSelected(result)) {
            Answer answer = answerRepository.findById(result.getAnswer().getId());
            if (isChoice(answer) || isFreeTextAndNotEmpty(answer, result)) {
                return true;
            }
        } else if (isAnswerNotSelected(result)) {
            return true;
        }
        return false;
    }

    private boolean isAnswerSelected(Result result) {
        return result.getAnswer() != null && result.getAnswer().getId() != null;
    }

    private boolean isChoice(Answer answer) {
        return answer.getValueType() == Answer.ValueType.CHOICE;
    }

    private boolean isFreeTextAndNotEmpty(Answer answer, Result result) {
        return answer.getValueType() == Answer.ValueType.FREE_TEXT
                && result.getFreeText() != "" && result.getFreeText() != null;
    }

    private boolean isAnswerNotSelected(Result result) {
        return result.getAnswer() != null && result.getAnswer().getId() == null
                || result.getAnswer() == null;
    }
}