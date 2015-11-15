package io.feedback.survey.service;

import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveQuestion(Question question) {
        getQuestionRepository().insertOrUpdate(question);
    }
}