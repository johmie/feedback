package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.ResultProvider;
import io.feedback.survey.repository.AnswerRepository;
import io.feedback.survey.validator.ResultValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ResultValidatorTest {

    private ResultValidator resultValidator;

    @Before
    public void setUp() {
        resultValidator = new ResultValidator();
        resultValidator.setAnswerRepository(mock(AnswerRepository.class));
    }

    @Test
    public void getAndSetAnswerRepository() {
        AnswerRepository answerRepositoryMock = mock(AnswerRepository.class);
        resultValidator.setAnswerRepository(answerRepositoryMock);
        assertEquals(answerRepositoryMock, resultValidator.getAnswerRepository());
    }

    @Test
    @Parameters(source = ResultProvider.class, method = "provideForFreeTextValidator")
    public void isValidWithFreeTextAnswers(Result resultMock, boolean isValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.FREE_TEXT);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(answerMock);
        assertEquals(isValid, resultValidator.isValid(resultMock));
    }

    @Test
    @Parameters(source = ResultProvider.class, method = "provideForChoiceValidator")
    public void isValidWithChoiceAnswers(Result resultMock, boolean isValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.CHOICE);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(answerMock);
        assertEquals(isValid, resultValidator.isValid(resultMock));
    }

    @Test
    @Parameters(source = ResultProvider.class, method = "provideWithoutAnswer")
    public void isValidWithUnselectedAnswers(List<Result> resultMocks) {
        for (Result resultMock : resultMocks) {
            assertEquals(true, resultValidator.isValid(resultMock));
        }
    }
}