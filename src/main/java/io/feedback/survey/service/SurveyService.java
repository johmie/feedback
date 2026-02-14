package io.feedback.survey.service;

import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private SurveyRepository surveyRepository;

    public SurveyRepository getSurveyRepository() {
        return surveyRepository;
    }

    @Autowired
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> findAll() {
        return getSurveyRepository().findAll();
    }

    public Optional<Survey> findById(Long id) {
        return getSurveyRepository().findById(id);
    }

    public void saveSurvey(Survey survey) {
        getSurveyRepository().save(survey);
    }

    public void deleteSurvey(Survey survey) {
        getSurveyRepository().delete(survey);
    }
}