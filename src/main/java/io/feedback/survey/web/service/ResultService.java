package io.feedback.survey.web.service;

import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.ResultRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import io.feedback.survey.web.validator.PageModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private PageModelValidator pageModelValidator;

    private ResultRepository resultRepository;

    public PageModelValidator getPageModelValidator() {
        return pageModelValidator;
    }

    @Autowired
    public void setPageModelValidator(PageModelValidator pageModelValidator) {
        this.pageModelValidator = pageModelValidator;
    }

    public ResultRepository getResultRepository() {
        return resultRepository;
    }

    @Autowired
    public void setResultRepository(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public boolean saveResultsIfValid(PageModel pageModel, BindingResult bindingResult) {
        getPageModelValidator().validate(pageModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return false;
        } else {
            saveResults(extractResultsFromPageModel(pageModel));
            return true;
        }
    }

    public void saveResults(List<Result> results) {
        getResultRepository().saveResults(results);
    }

    public List<Result> extractResultsFromPageModel(PageModel pageModel) {
        List<Result> resultsFromPageModel = new ArrayList<>();
        for (QuestionModel questionModel : pageModel.getQuestionModels().values()) {
            for (Result result : questionModel.getResults()) {
                if (result.getAnswer().getId() != null) {
                    resultsFromPageModel.add(result);
                }
            }
        }
        return resultsFromPageModel;
    }
}