package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.ResultMockProvider;
import io.feedback.survey.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultValidatorTest {

    private static final ResultMockProvider MOCK_PROVIDER = new ResultMockProvider();

    private ResultValidator resultValidator;

    private static Object[] provideOneWithValidAndInvalidFreeText() {
        return MOCK_PROVIDER.provideOneWithValidAndInvalidFreeText();
    }

    private static Object[] provideOneWithSelectedAndUnselectedAnswer() {
        return MOCK_PROVIDER.provideOneWithSelectedAndUnselectedAnswer();
    }

    private static Object[] provideListWithoutAnswer() {
        return MOCK_PROVIDER.provideListWithoutAnswer();
    }

    @BeforeEach
    void setUp() {
        resultValidator = new ResultValidator();
        resultValidator.setAnswerRepository(mock(AnswerRepository.class));
    }

    @Test
    void setAnswerRepository_SomeAnswerRepository_SameValueIsReturnedByGetAnswerRepository() {
        AnswerRepository answerRepositoryMock = mock(AnswerRepository.class);

        resultValidator.setAnswerRepository(answerRepositoryMock);

        assertEquals(answerRepositoryMock, resultValidator.getAnswerRepository());
    }

    @ParameterizedTest
    @MethodSource("provideOneWithValidAndInvalidFreeText")
    void isValid_ValidAndInvalidFreeTextResults_ResultsAreValidatedCorrectly(Result resultMock,
                                                                             boolean expectedIsValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.FREE_TEXT);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(Optional.of(answerMock));

        boolean actualIsValid = resultValidator.isValid(resultMock);

        assertEquals(expectedIsValid, actualIsValid);
    }

    @ParameterizedTest
    @MethodSource("provideOneWithSelectedAndUnselectedAnswer")
    void isValid_SelectedAndUnselectedChoiceResults_AnyResultIsValid(Result resultMock, boolean expectedIsValid) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getValueType()).thenReturn(Answer.ValueType.CHOICE);
        when(resultValidator.getAnswerRepository().findById(resultMock.getAnswer().getId())).thenReturn(Optional.of(answerMock));

        boolean actualIsValid = resultValidator.isValid(resultMock);

        assertEquals(expectedIsValid, actualIsValid);
    }

    @ParameterizedTest
    @MethodSource("provideListWithoutAnswer")
    void isValid_ResultsWithoutAnswer_AnyResultIsValid(List<Result> resultMocks) {
        for (Result resultMock : resultMocks) {
            boolean isValid = resultValidator.isValid(resultMock);

            assertTrue(isValid);
        }
    }

    @Test
    void isValid_AnswerPresentWithNullId_ReturnsTrue() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);
        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);

        boolean isValid = resultValidator.isValid(resultMock);

        assertTrue(isValid);
    }

    @Test
    void isValid_AnswerIsNull_ReturnsTrue() {
        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(null);

        boolean isValid = resultValidator.isValid(resultMock);

        assertTrue(isValid);
    }
}