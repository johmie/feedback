package io.feedback.survey.web.service;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.AnswerRow;
import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.AnswerRepository;
import io.feedback.survey.repository.AnswerRowRepository;
import io.feedback.survey.repository.ResultRepository;
import io.feedback.survey.web.dto.PageFormDto;
import io.feedback.survey.web.dto.ParticipationDto;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import io.feedback.survey.web.validator.PageModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private PageModelValidator pageModelValidator;

    private ResultRepository resultRepository;

    private AnswerRepository answerRepository;

    private AnswerRowRepository answerRowRepository;

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

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public AnswerRowRepository getAnswerRowRepository() {
        return answerRowRepository;
    }

    @Autowired
    public void setAnswerRowRepository(AnswerRowRepository answerRowRepository) {
        this.answerRowRepository = answerRowRepository;
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

    public List<Result> extractResultsFromPageModel(PageModel pageModel) {
        List<Result> resultsFromPageModel = new ArrayList<>();
        if (pageModel.getQuestionModels() == null) {
            return resultsFromPageModel;
        }
        for (QuestionModel questionModel : pageModel.getQuestionModels().values()) {
            for (Result result : questionModel.getResults()) {
                if (resultHasAnswer(result)) {
                    resultsFromPageModel.add(result);
                }
            }
        }
        return resultsFromPageModel;
    }

    private boolean resultHasAnswer(Result result) {
        return result.getAnswer() != null
                && result.getAnswer().getId() != null;
    }

    public void saveResultsWithParticipationData(List<Result> results, ParticipationDto participationDto) {
        for (Result result : results) {
            result.setParticipationIdentifier(participationDto.getIdentifier());
            result.setRemoteAddress(participationDto.getRemoteAddress());
        }
        saveResults(results);
    }

    private void saveResults(List<Result> results) {
        for (Result result : results) {
            Answer answer = getAnswerRepository().getReferenceById(
                    result.getAnswer().getId()
            );
            result.setAnswer(answer);
            if (resultHasAnswerRow(result)) {
                AnswerRow answerRow = getAnswerRowRepository().getReferenceById(
                        result.getAnswerRow().getId()
                );
                result.setAnswerRow(answerRow);
            }
            result.setCreated(new Timestamp(System.currentTimeMillis()));
            getResultRepository().save(result);
        }
    }

    private boolean resultHasAnswerRow(Result result) {
        return result.getAnswerRow() != null
                && result.getAnswerRow().getId() != null;
    }
}