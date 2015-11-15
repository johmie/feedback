package io.feedback.survey.service;

import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
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
public class QuestionServiceTest {

    private QuestionService questionService;

    @Before
    public void setUp() {
        questionService = new QuestionService();
        questionService.setQuestionRepository(mock(QuestionRepository.class));
    }

    @Test
    public void getAndSetQuestionRepository() {
        QuestionRepository questionRepositoryMock = mock(QuestionRepository.class);
        questionService.setQuestionRepository(questionRepositoryMock);
        assertEquals(questionRepositoryMock, questionService.getQuestionRepository());
    }

    @Test
    public void saveQuestionCallsSaveMethodInRepository() {
        Question questionMock = mock(Question.class);
        questionService.saveQuestion(questionMock);
        verify(questionService.getQuestionRepository()).insertOrUpdate(questionMock);
    }
}