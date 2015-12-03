package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.ResultMockProvider;
import io.feedback.survey.repository.AnswerRepository;
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
    public void setAnswerRepository_SomeAnswerRepository_SameValueIsReturnedByGetAnswerRepository() {
        AnswerRepository answerRepositoryMock = mock(AnswerRepository.class);

        resultValidator.setAnswerRepository(answerRepositoryMock);

        assertEquals(answerRepositoryMock, resultValidator.getAnswerRepository());
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideOneWithValidAndInvalidFreeText")
    public void isValid_ValidAndInvalidFreeTextResults_ResultsAreValidatedCorrectly(Result resultMock,
                                                                                    boolean expectedIsValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.FREE_TEXT);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(answerMock);

        boolean actualIsValid = resultValidator.isValid(resultMock);

        assertEquals(expectedIsValid, actualIsValid);
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideOneWithSelectedAndUnselectedAnswer")
    public void isValid_SelectedAndUnselectedChoiceResults_AnyResultIsValid(Result resultMock, boolean expectedIsValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.CHOICE);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(answerMock);

        boolean actualIsValid = resultValidator.isValid(resultMock);

        assertEquals(expectedIsValid, actualIsValid);
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideListWithoutAnswer")
    public void isValid_ResultsWithoutAnswer_AnyResultIsValid(List<Result> resultMocks) {
        for (Result resultMock : resultMocks) {
            boolean isValid = resultValidator.isValid(resultMock);

            assertEquals(true, isValid);
        }
    }
}