package io.feedback.survey.service;

import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.PageRepository;
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
        surveyService.setPageRepository(mock(PageRepository.class));
    }

    @Test
    public void getAndSetSurveyRepository() {
        SurveyRepository surveyRepositoryMock = mock(SurveyRepository.class);
        surveyService.setSurveyRepository(surveyRepositoryMock);
        assertEquals(surveyRepositoryMock, surveyService.getSurveyRepository());
    }

    @Test
    public void getAndSetPageRepository() {
        PageRepository pageRepositoryMock = mock(PageRepository.class);
        surveyService.setPageRepository(pageRepositoryMock);
        assertEquals(pageRepositoryMock, surveyService.getPageRepository());
    }

    @Test
    public void saveSurveyCallsSaveMethodInRepository() {
        Survey surveyMock = mock(Survey.class);
        surveyService.saveSurvey(surveyMock);
        verify(surveyService.getSurveyRepository()).insertOrUpdate(surveyMock);
    }
}