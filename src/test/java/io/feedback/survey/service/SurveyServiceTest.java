package io.feedback.survey.service;

import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.SurveyRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class SurveyServiceTest {

    private SurveyService surveyService;

    @Before
    public void setUp() {
        surveyService = new SurveyService();
        surveyService.setSurveyRepository(mock(SurveyRepository.class));
    }

    @Test
    public void setSurveyRepository_SomeSurveyRepository_SameValueIsReturnedByGetSurveyRepository() {
        SurveyRepository surveyRepositoryMock = mock(SurveyRepository.class);

        surveyService.setSurveyRepository(surveyRepositoryMock);

        assertEquals(surveyRepositoryMock, surveyService.getSurveyRepository());
    }

    @Test
    public void saveSurvey_SomeSurvey_SaveMethodOfSurveyRepositoryIsCalled() {
        Survey surveyMock = mock(Survey.class);

        surveyService.saveSurvey(surveyMock);

        verify(surveyService.getSurveyRepository()).save(surveyMock);
    }

    @Test
    public void findAll_ReturnsAllSurveys() {
        Survey survey1 = new Survey();
        Survey survey2 = new Survey();
        List<Survey> surveys = Arrays.asList(survey1, survey2);
        when(surveyService.getSurveyRepository().findAll()).thenReturn(surveys);

        List<Survey> result = surveyService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void findById_WhenExists_ReturnsSurvey() {
        Survey survey = new Survey();
        survey.setId(1L);
        when(surveyService.getSurveyRepository().findById(1L)).thenReturn(Optional.of(survey));

        Optional<Survey> result = surveyService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(Long.valueOf(1L), result.get().getId());
    }

    @Test
    public void findById_WhenNotExists_ReturnsEmpty() {
        when(surveyService.getSurveyRepository().findById(999L)).thenReturn(Optional.empty());

        Optional<Survey> result = surveyService.findById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    public void deleteSurvey_SomeSurvey_DeleteMethodOfSurveyRepositoryIsCalled() {
        Survey surveyMock = mock(Survey.class);

        surveyService.deleteSurvey(surveyMock);

        verify(surveyService.getSurveyRepository()).delete(surveyMock);
    }
}