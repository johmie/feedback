package io.feedback.survey.service;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.repository.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    
    public void saveAnswer(Answer answer) {
        getAnswerRepository().insertOrUpdate(answer);
    }
    
    public void deleteAnswer(Answer answer) {
        getAnswerRepository().delete(answer);
    }
}