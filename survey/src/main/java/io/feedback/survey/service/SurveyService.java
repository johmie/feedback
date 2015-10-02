package io.feedback.survey.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.PageRepository;
import io.feedback.survey.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    private SurveyRepository surveyRepository;
    private PageRepository pageRepository;

    public SurveyRepository getSurveyRepository() {
        return surveyRepository;
    }

    @Autowired
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }
    
    public PageRepository getPageRepository() {
        return pageRepository;
    }

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public void saveSurvey(Survey survey) {
        getSurveyRepository().insertOrUpdate(survey);
    }
    
    public Page loadSurveyPage(Long surveyId, Integer pageNumber) {
        return getPageRepository().findByPageNumberAndSurveyId(pageNumber, surveyId);
    }
}