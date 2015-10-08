package io.feedback.survey.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.PageRepository;
import io.feedback.survey.repository.ResultRepository;
import io.feedback.survey.repository.SurveyRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import io.feedback.survey.web.validator.PageModelValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SurveyService {

    private SurveyRepository surveyRepository;

    private PageRepository pageRepository;

    private PageModelValidator pageModelValidator;

    private ResultRepository resultRepository;

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
    public void setPageModelValidator(PageModelValidator pageModelValidator) {
        this.pageModelValidator = pageModelValidator;
    }

    public PageModelValidator getPageModelValidator() {
        return pageModelValidator;
    }

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public ResultRepository getResultRepository() {
        return resultRepository;
    }

    @Autowired
    public void setResultRepository(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void saveSurvey(Survey survey) {
        getSurveyRepository().insertOrUpdate(survey);
    }

    public Page loadPage(Long surveyId, Integer pageNumber) {
        return getPageRepository().findBySurveyIdAndPageNumber(surveyId, pageNumber);
    }

    public boolean saveResultsIfValid(PageModel pageModel, BindingResult bindingResult) {
        getPageModelValidator().validate(pageModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return false;
        } else {
            List<Result> results = extractResultsFromPageModel(pageModel);
            resultRepository.saveResults(results);
            return true;
        }
    }

    private List<Result> extractResultsFromPageModel(PageModel pageModel) {
        List<Result> resultsFromPageModel = new ArrayList<Result>();

        Map<Long, QuestionModel> questionModels = pageModel.getQuestionModels();
        Iterator<Entry<Long, QuestionModel>> questionModelsIterator = questionModels.entrySet().iterator();
        while (questionModelsIterator.hasNext()) {
            Entry<Long, QuestionModel> questionModelEntry = questionModelsIterator.next();
            QuestionModel questionModel = questionModelEntry.getValue();
            List<Result> resultsFromQuestionModel = questionModel.getResults();
            Iterator<Result> resultsIterator = resultsFromQuestionModel.iterator();
            while (resultsIterator.hasNext()) {
                Result result = resultsIterator.next();
                if (result.getAnswer().getId() != null) {
                    resultsFromPageModel.add(result);
                }
            }
        }
        return resultsFromPageModel;
    }
}