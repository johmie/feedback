package io.feedback.survey.service;

import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveSurvey(Survey survey) {
        getSurveyRepository().insertOrUpdate(survey);
    }
}