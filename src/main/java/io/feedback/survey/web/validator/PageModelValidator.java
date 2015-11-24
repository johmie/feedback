package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
            validateQuestions(pageModel.getQuestionModels(), errors);
        }
    }

    private void handleNoQuestionAnswered(Page page, Errors errors) {
        for (Question question : page.getQuestions()) {
            errors.rejectValue("questionModels[" + question.getId() + "]", "error.question_not_answered");
        }
    }

    private void validateQuestions(Map<Long, QuestionModel> questionModels, Errors errors) {
        Iterator<Entry<Long, QuestionModel>> questionModelsIterator = questionModels.entrySet().iterator();
        while (questionModelsIterator.hasNext()) {
            Entry<Long, QuestionModel> questionModelEntry = questionModelsIterator.next();
            QuestionModel questionModel = questionModelEntry.getValue();
            Question question = questionRepository.findById(questionModelEntry.getKey());
            questionModelValidator.validate(questionModel, errors, question);
        }
    }
}