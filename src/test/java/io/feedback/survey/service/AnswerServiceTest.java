package io.feedback.survey.service;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.repository.AnswerRepository;
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
public class AnswerServiceTest {

    private AnswerService answerService;

    @Before
    public void setUp() {
        answerService = new AnswerService();
        answerService.setAnswerRepository(mock(AnswerRepository.class));
    }

    @Test
    public void getAndSetAnswerRepositoryWorks() {
        AnswerRepository answerRepositoryMock = mock(AnswerRepository.class);
        answerService.setAnswerRepository(answerRepositoryMock);
        assertEquals(answerRepositoryMock, answerService.getAnswerRepository());
    }

    @Test
    public void saveAnswerCallsSaveMethodInRepository() {
        Answer answerMock = mock(Answer.class);
        answerService.saveAnswer(answerMock);
        verify(answerService.getAnswerRepository()).insertOrUpdate(answerMock);
    }

    @Test
    public void deleteAnswerCallsDeleteMethodInRepository() {
        Answer answerMock = mock(Answer.class);
        answerService.deleteAnswer(answerMock);
        verify(answerService.getAnswerRepository()).delete(answerMock);
    }
}