package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Component
public class PageModelValidator {

    private QuestionRepository questionRepository;

    private QuestionModelValidator questionModelValidator;

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionModelValidator getQuestionModelValidator() {
        return questionModelValidator;
    }

    @Autowired
    public void setQuestionModelValidator(QuestionModelValidator questionModelValidator) {
        this.questionModelValidator = questionModelValidator;
    }

    public void validate(PageModel pageModel, Errors errors, Page page) {
        if (pageModel.getQuestionModels() == null) {
            handleNoQuestionAnswered(page, errors);
        } else {
            validateQuestions(pageModel.getQuestionModels(), errors, page);
        }
    }

    private void handleNoQuestionAnswered(Page page, Errors errors) {
        for (Question question : page.getQuestions()) {
            errors.rejectValue(
                    "questionModels[" + question.getId() + "]", "error.question_not_answered"
            );
        }
    }

    private void validateQuestions(Map<Long, QuestionModel> questionModels, Errors errors, Page page) {
        for (Entry<Long, QuestionModel> questionModelEntry : questionModels.entrySet()) {
            QuestionModel questionModel = questionModelEntry.getValue();
            Optional<Question> question = getQuestionRepository().findById(questionModelEntry.getKey());
            getQuestionModelValidator().validate(questionModel, errors, question.get());
        }
        validateMissingQuestions(questionModels, errors, page);
    }

    private void validateMissingQuestions(Map<Long, QuestionModel> questionModels, Errors errors, Page page) {
        if (page.getQuestions() != null) {
            for (Question question : page.getQuestions()) {
                if (!questionModels.containsKey(question.getId())) {
                    errors.rejectValue(
                            "questionModels[" + question.getId() + "]", "error.question_not_answered"
                    );
                }
            }
        }
    }
}