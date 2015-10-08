package io.feedback.survey.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.AnswerRepository;

@Service
public class ResultValidator {

    @Autowired
    private AnswerRepository answerRepository;

    public Boolean isValid(Result result) {
        if (result.getAnswer() != null && result.getAnswer().getId() != null) {
            Answer answer = answerRepository.findById(result.getAnswer()
                    .getId());
            if (answer.getValueType() == Answer.ValueType.CHOICE
                    || answer.getValueType() == Answer.ValueType.FREE_TEXT
                    && result.getFreeText().length() != 0) {
                return true;
            }
        }
        return false;
    }
}