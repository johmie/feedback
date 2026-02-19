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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResultServiceTest {

    private ResultService resultService;
    private ResultRepository resultRepositoryMock;
    private AnswerRepository answerRepositoryMock;
    private AnswerRowRepository answerRowRepositoryMock;

    @BeforeEach
    public void setUp() {
        resultService = new ResultService();
        PageModelValidator pageModelValidatorMock = mock(PageModelValidator.class);
        resultRepositoryMock = mock(ResultRepository.class);
        answerRepositoryMock = mock(AnswerRepository.class);
        answerRowRepositoryMock = mock(AnswerRowRepository.class);

        resultService.setPageModelValidator(pageModelValidatorMock);
        resultService.setResultRepository(resultRepositoryMock);
        resultService.setAnswerRepository(answerRepositoryMock);
        resultService.setAnswerRowRepository(answerRowRepositoryMock);
    }

    @Test
    public void saveResultsIfValid_WhenValidationHasErrors_ReturnsFalse() {
        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        PageModel pageModelMock = mock(PageModel.class);

        when(pageFormDtoMock.getPageModel()).thenReturn(pageModelMock);
        when(pageFormDtoMock.getBindingResult()).thenReturn(bindingResultMock);
        when(bindingResultMock.hasErrors()).thenReturn(true);

        boolean result = resultService.saveResultsIfValid(pageFormDtoMock, mock(ParticipationDto.class));

        assertFalse(result);
        verify(resultRepositoryMock, never()).save(any(Result.class));
    }

    @Test
    public void saveResultsIfValid_WhenValidationSucceeds_ReturnsTrue() {
        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        PageModel pageModelMock = mock(PageModel.class);
        Page pageMock = mock(Page.class);
        ParticipationDto participationDtoMock = mock(ParticipationDto.class);

        when(pageFormDtoMock.getPageModel()).thenReturn(pageModelMock);
        when(pageFormDtoMock.getBindingResult()).thenReturn(bindingResultMock);
        when(pageFormDtoMock.getPage()).thenReturn(pageMock);
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(pageModelMock.getQuestionModels()).thenReturn(new HashMap<>());

        boolean result = resultService.saveResultsIfValid(pageFormDtoMock, participationDtoMock);

        assertTrue(result);
    }

    @Test
    public void extractResultsFromPageModel_WhenQuestionModelsIsNull_ReturnsEmptyList() {
        PageModel pageModel = new PageModel();

        List<Result> results = resultService.extractResultsFromPageModel(pageModel);

        assertTrue(results.isEmpty());
    }

    @Test
    public void extractResultsFromPageModel_WhenResultHasNoAnswer_ReturnsEmptyList() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel = new QuestionModel();

        List<Result> resultsWithoutAnswer = new ArrayList<>();
        Result resultWithoutAnswer = new Result();
        resultWithoutAnswer.setAnswer(null);
        resultsWithoutAnswer.add(resultWithoutAnswer);
        questionModel.setResults(resultsWithoutAnswer);

        questionModels.put(1L, questionModel);
        pageModel.setQuestionModels(questionModels);

        List<Result> extractedResults = resultService.extractResultsFromPageModel(pageModel);

        assertTrue(extractedResults.isEmpty());
    }

    @Test
    public void extractResultsFromPageModel_WhenResultHasAnswerWithId_ReturnsResult() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel = new QuestionModel();

        List<Result> resultsWithAnswer = new ArrayList<>();
        Result resultWithAnswer = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        resultWithAnswer.setAnswer(answer);
        resultsWithAnswer.add(resultWithAnswer);
        questionModel.setResults(resultsWithAnswer);

        questionModels.put(1L, questionModel);
        pageModel.setQuestionModels(questionModels);

        List<Result> extractedResults = resultService.extractResultsFromPageModel(pageModel);

        assertEquals(1, extractedResults.size());
        assertEquals(answer, extractedResults.get(0).getAnswer());
    }

    @Test
    public void extractResultsFromPageModel_WhenResultHasAnswerWithoutId_ReturnsEmptyList() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel = new QuestionModel();

        List<Result> resultsWithAnswerWithoutId = new ArrayList<>();
        Result resultWithAnswerWithoutId = new Result();
        Answer answerWithoutId = new Answer();
        resultWithAnswerWithoutId.setAnswer(answerWithoutId);
        resultsWithAnswerWithoutId.add(resultWithAnswerWithoutId);
        questionModel.setResults(resultsWithAnswerWithoutId);

        questionModels.put(1L, questionModel);
        pageModel.setQuestionModels(questionModels);

        List<Result> extractedResults = resultService.extractResultsFromPageModel(pageModel);

        assertTrue(extractedResults.isEmpty());
    }

    @Test
    public void saveResultsWithParticipationData_SetsParticipationDataOnResults() {
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        results.add(result);

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setIdentifier("test-identifier");
        participationDto.setRemoteAddress("192.168.1.1");

        Answer answerRef = new Answer();
        when(answerRepositoryMock.getReferenceById(1L)).thenReturn(answerRef);

        resultService.saveResultsWithParticipationData(results, participationDto);

        assertEquals("test-identifier", result.getParticipationIdentifier());
        assertEquals("192.168.1.1", result.getRemoteAddress());
        verify(resultRepositoryMock).save(result);
    }

    @Test
    public void saveResultsWithParticipationData_WithAnswerRow_SetsAnswerRowReference() {
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        AnswerRow answerRow = new AnswerRow();
        answerRow.setId(2L);
        result.setAnswerRow(answerRow);

        results.add(result);

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setIdentifier("test-identifier");

        Answer answerRef = new Answer();
        AnswerRow answerRowRef = new AnswerRow();

        when(answerRepositoryMock.getReferenceById(1L)).thenReturn(answerRef);
        when(answerRowRepositoryMock.getReferenceById(2L)).thenReturn(answerRowRef);

        resultService.saveResultsWithParticipationData(results, participationDto);

        verify(answerRepositoryMock).getReferenceById(1L);
        verify(answerRowRepositoryMock).getReferenceById(2L);
    }

    @Test
    public void saveResultsWithParticipationData_WithoutAnswerRow_DoesNotCallAnswerRowRepository() {
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        results.add(result);

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setIdentifier("test-identifier");

        Answer answerRef = new Answer();

        when(answerRepositoryMock.getReferenceById(1L)).thenReturn(answerRef);

        resultService.saveResultsWithParticipationData(results, participationDto);

        verify(answerRepositoryMock).getReferenceById(1L);
        verify(answerRowRepositoryMock, never()).getReferenceById(any());
    }

    @Test
    public void saveResultsWithParticipationData_WithAnswerRowWithoutId_DoesNotCallAnswerRowRepository() {
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        AnswerRow answerRowWithoutId = new AnswerRow();
        result.setAnswerRow(answerRowWithoutId);

        results.add(result);

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setIdentifier("test-identifier");

        Answer answerRef = new Answer();

        when(answerRepositoryMock.getReferenceById(1L)).thenReturn(answerRef);

        resultService.saveResultsWithParticipationData(results, participationDto);

        verify(answerRepositoryMock).getReferenceById(1L);
        verify(answerRowRepositoryMock, never()).getReferenceById(any());
    }

    @Test
    public void saveResultsWithParticipationData_SetsCreatedTimestamp() {
        List<Result> results = new ArrayList<>();
        Result result = new Result();

        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        results.add(result);

        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setIdentifier("test-identifier");

        Answer answerRef = new Answer();

        when(answerRepositoryMock.getReferenceById(1L)).thenReturn(answerRef);

        resultService.saveResultsWithParticipationData(results, participationDto);

        assertNotNull(result.getCreated());
    }

    @Test
    public void setPageModelValidator_SomePageModelValidator_SameValueIsReturnedByGetPageModelValidator() {
        PageModelValidator validatorMock = mock(PageModelValidator.class);

        resultService.setPageModelValidator(validatorMock);

        assertEquals(validatorMock, resultService.getPageModelValidator());
    }

    @Test
    public void setResultRepository_SomeResultRepository_SameValueIsReturnedByGetResultRepository() {
        ResultRepository repositoryMock = mock(ResultRepository.class);

        resultService.setResultRepository(repositoryMock);

        assertEquals(repositoryMock, resultService.getResultRepository());
    }

    @Test
    public void setAnswerRepository_SomeAnswerRepository_SameValueIsReturnedByGetAnswerRepository() {
        AnswerRepository repositoryMock = mock(AnswerRepository.class);

        resultService.setAnswerRepository(repositoryMock);

        assertEquals(repositoryMock, resultService.getAnswerRepository());
    }

    @Test
    public void setAnswerRowRepository_SomeAnswerRowRepository_SameValueIsReturnedByGetAnswerRowRepository() {
        AnswerRowRepository repositoryMock = mock(AnswerRowRepository.class);

        resultService.setAnswerRowRepository(repositoryMock);

        assertEquals(repositoryMock, resultService.getAnswerRowRepository());
    }
}