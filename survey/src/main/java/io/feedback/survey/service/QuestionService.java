package io.feedback.survey.service;

import java.util.List;

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
    
    public void addQuestion(Question question) {
        getQuestionRepository().insert(question);
    }
    
    public List<Question> fetchAllQuestions() {
        return getQuestionRepository().fetchAll();
    }
}