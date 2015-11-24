package io.feedback.survey.web.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.ResultRepository;
import io.feedback.survey.web.dto.PageFormDto;
import io.feedback.survey.web.dto.ParticipationDto;
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

    public boolean saveResultsIfValid(PageFormDto pageFormDto, ParticipationDto participationDto) {
        PageModel pageModel = pageFormDto.getPageModel();
        BindingResult bindingResult = pageFormDto.getBindingResult();
        Page page = pageFormDto.getPage();
        getPageModelValidator().validate(pageModel, bindingResult, page);
        if (bindingResult.hasErrors()) {
            return false;
        } else {
            List<Result> resultsFromPage = extractResultsFromPageModel(pageModel);
            saveResultsWithParticipationData(resultsFromPage, participationDto);
            return true;
        }
    }

    public void saveResultsWithParticipationData(List<Result> results, ParticipationDto participationDto) {
        for (Result result : results) {
            result.setParticipationIdentifier(participationDto.getIdentifier());
            result.setRemoteAddress(participationDto.getRemoteAddress());
        }
        getResultRepository().saveResults(results);
    }

    public List<Result> extractResultsFromPageModel(PageModel pageModel) {
        List<Result> resultsFromPageModel = new ArrayList<>();
        if (pageModel.getQuestionModels() == null) {
            return resultsFromPageModel;
        }
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