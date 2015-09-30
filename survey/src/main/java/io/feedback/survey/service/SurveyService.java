package io.feedback.survey.service;

import java.util.List;

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

    public void addSurvey(Survey survey) {
        getSurveyRepository().insert(survey);
    }
    
    public List<Survey> fetchAllSurveys() {
        return getSurveyRepository().fetchAll();
    }
    
    public Survey fetchSurveyById(Long id) {
        return getSurveyRepository().fetchById(id);
    }
    
    public Page fetchPageOfSurveyByPageNumber(Long surveyId, Integer number) {
        return getPageRepository().fetchOfSurveyByPageNumber(surveyId, number);
    }
}