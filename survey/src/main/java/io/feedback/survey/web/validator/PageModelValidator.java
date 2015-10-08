package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PageModelValidator {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionModelValidator questionModelValidator;

    public void validate(PageModel pageModel, Errors errors) {
        Map<Long, QuestionModel> questionModels = pageModel.getQuestionModels();
        Iterator<Entry<Long, QuestionModel>> questionModelsIterator = questionModels.entrySet().iterator();
        while (questionModelsIterator.hasNext()) {
            Entry<Long, QuestionModel> questionModelEntry = questionModelsIterator.next();
            QuestionModel questionModel = questionModelEntry.getValue();
            Question question = questionRepository.findById(questionModelEntry.getKey());
            questionModelValidator.validate(questionModel, errors, question);
        }
    }
}