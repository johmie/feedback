package io.feedback.survey.web.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.ResultRepository;
import io.feedback.survey.web.dto.PageFormDto;
import io.feedback.survey.web.dto.ParticipationDto;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.PageModelProvider;
import io.feedback.survey.web.validator.PageModelValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ResultServiceTest {

    private ResultService resultService;

    @Before
    public void setUp() {
        resultService = new ResultService();
        resultService.setPageModelValidator(mock(PageModelValidator.class));
        resultService.setResultRepository(mock(ResultRepository.class));
    }

    @Test
    public void getAndSetPageModelValidator() {
        PageModelValidator pageModelValidatorMock = mock(PageModelValidator.class);
        resultService.setPageModelValidator(pageModelValidatorMock);
        assertEquals(pageModelValidatorMock, resultService.getPageModelValidator());
    }

    @Test
    public void getAndSetResultRepository() {
        ResultRepository resultRepositoryMock = mock(ResultRepository.class);
        resultService.setResultRepository(resultRepositoryMock);
        assertEquals(resultRepositoryMock, resultService.getResultRepository());
    }

    @Test
    @Parameters(method = "parametersForSaveResultsIfValid")
    public void saveResultsIfValidValidates(PageFormDto pageFormDtoMock, ParticipationDto participationDtoMock) {
        PageModel pageModelMock = pageFormDtoMock.getPageModel();
        BindingResult bindingResultMock = pageFormDtoMock.getBindingResult();
        Page pageMock = pageFormDtoMock.getPage();
        when(pageFormDtoMock.getBindingResult().hasErrors()).thenReturn(false);
        resultService.saveResultsIfValid(pageFormDtoMock, participationDtoMock);
        verify(resultService.getPageModelValidator()).validate(pageModelMock, bindingResultMock, pageMock);
    }

    @Test
    @Parameters(method = "parametersForSaveResultsIfValid")
    public void saveResultsIfValidSavesWithoutErrors(PageFormDto pageFormDtoMock,
                                                     ParticipationDto participationDtoMock) {
        BindingResult bindingResultMock = pageFormDtoMock.getBindingResult();
        when(bindingResultMock.hasErrors()).thenReturn(false);
        assertEquals(true, resultService.saveResultsIfValid(pageFormDtoMock, participationDtoMock));
    }

    @Test
    @Parameters(method = "parametersForSaveResultsIfValid")
    public void saveResultsIfValidSavesNotWithErrors(PageFormDto pageFormDtoMock,
                                                     ParticipationDto participationDtoMock) {
        BindingResult bindingResultMock = pageFormDtoMock.getBindingResult();
        when(bindingResultMock.hasErrors()).thenReturn(true);
        assertEquals(false, resultService.saveResultsIfValid(pageFormDtoMock, participationDtoMock));
    }

    private Object[] parametersForSaveResultsIfValid() {
        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        when(pageFormDtoMock.getPageModel()).thenReturn(mock(PageModel.class));
        when(pageFormDtoMock.getBindingResult()).thenReturn(mock(BindingResult.class));
        when(pageFormDtoMock.getPage()).thenReturn(mock(Page.class));
        return new Object[]{
                new Object[]{pageFormDtoMock, mock(ParticipationDto.class)},
        };
    }

    @Test
    public void saveResultsCallsSaveMethodInRepository() {
        Result resultMock = mock(Result.class);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        resultService.saveResultsWithParticipationData(resultMocks, mock(ParticipationDto.class));
        verify(resultService.getResultRepository()).saveResults(resultMocks);
    }

    @Test
    public void saveResultsSetsParticipationIdentifier() {
        String participationIdentifier = "Participation identifier";
        ParticipationDto participationDtoMock = mock(ParticipationDto.class);
        when(participationDtoMock.getIdentifier()).thenReturn(participationIdentifier);
        Result resultMock = mock(Result.class);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        resultService.saveResultsWithParticipationData(resultMocks, participationDtoMock);
        verify(resultMock).setParticipationIdentifier(participationIdentifier);
    }

    @Test
    public void saveResultsSetsRemoteAddress() {
        String remoteAddress = "127.0.0.1";
        ParticipationDto participationDtoMock = mock(ParticipationDto.class);
        when(participationDtoMock.getRemoteAddress()).thenReturn(remoteAddress);
        Result resultMock = mock(Result.class);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        resultService.saveResultsWithParticipationData(resultMocks, participationDtoMock);
        verify(resultMock).setRemoteAddress(remoteAddress);
    }

    @Test
    @Parameters(source = PageModelProvider.class, method = "provideForCountResults")
    public void extractResultsFromPageModelExtractsCorrectCount(PageModel pageModelMock, int countOfResults) {
        List<Result> results = resultService.extractResultsFromPageModel(pageModelMock);
        assertEquals(countOfResults, results.size());
    }

    @Test
    public void extractResultsFromPageModelExtractsEmptyListWhenNoQuestionIsAnswered() {
        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(null);
        List<Result> results = resultService.extractResultsFromPageModel(pageModelMock);
        assertEquals(0, results.size());
    }
}