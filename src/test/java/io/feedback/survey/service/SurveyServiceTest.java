package io.feedback.survey.service;

import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.SurveyRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
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
}