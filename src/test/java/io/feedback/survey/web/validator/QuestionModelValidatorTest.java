package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.ResultMockProvider;
import io.feedback.survey.web.model.QuestionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.Errors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuestionModelValidatorTest {

    private static final ResultMockProvider RESULT_MOCK_PROVIDER = new ResultMockProvider();

    private QuestionModelValidator questionModelValidator;

    private QuestionModel questionModelMock;

    private Errors errorsMock;

    private Question questionMock;

    private static Object[] provideListWithoutId() {
        return RESULT_MOCK_PROVIDER.provideListWithoutId();
    }

    private static Object[] provideListWithoutAnswer() {
        return RESULT_MOCK_PROVIDER.provideListWithoutAnswer();
    }

    @BeforeEach
    void setUp() {
        questionModelValidator = new QuestionModelValidator();
        questionModelValidator.setResultValidator(mock(ResultValidator.class));
        questionModelMock = mock(QuestionModel.class);
        errorsMock = mock(Errors.class);
        questionMock = mock(Question.class);
    }

    @Test
    void setResultValidator_SomeResultValidator_SameValueIsReturnedByGetResultValidator() {
        ResultValidator resultValidatorMock = mock(ResultValidator.class);

        questionModelValidator.setResultValidator(resultValidatorMock);

        assertEquals(resultValidatorMock, questionModelValidator.getResultValidator());
    }

    @ParameterizedTest
    @MethodSource("provideListWithoutId")
    void validate_InvalidResults_InvalidAnswerErrorIsSetForAnyInvalidResult(List<Result> resultMocks) {
        when(questionModelMock.getResults()).thenReturn(resultMocks);
        for (Result resultMock : resultMocks) {
            when(questionModelValidator.getResultValidator().isValid(resultMock)).thenReturn(false);
        }

        questionModelValidator.validate(questionModelMock, errorsMock, questionMock);

        for (int i = 0; i < resultMocks.size(); i++) {
            verify(errorsMock).rejectValue("questionModels[" + questionMock.getId() + "].results[" + i + "]",
                    "error.answer_is_invalid");
        }
    }

    @ParameterizedTest
    @MethodSource("provideListWithoutAnswer")
    void validate_ValidResults_NotAnsweredErrorIsSetForQuestion(List<Result> resultMocks) {
        when(questionModelMock.getResults()).thenReturn(resultMocks);
        for (Result resultMock : resultMocks) {
            when(questionModelValidator.getResultValidator().isValid(resultMock)).thenReturn(true);
        }

        questionModelValidator.validate(questionModelMock, errorsMock, questionMock);

        verify(errorsMock).rejectValue("questionModels[" + questionMock.getId() + "]", "error.question_not_answered");
    }

    @ParameterizedTest
    @MethodSource("provideListWithoutId")
    void validate_ValidResults_NotAnsweredErrorIsNeverSet(List<Result> resultMocks) {
        when(questionModelMock.getResults()).thenReturn(resultMocks);
        for (Result resultMock : resultMocks) {
            when(questionModelValidator.getResultValidator().isValid(resultMock)).thenReturn(true);
        }

        questionModelValidator.validate(questionModelMock, errorsMock, questionMock);

        verify(errorsMock, never())
                .rejectValue("questionModels[" + questionMock.getId() + "]", "", "No answer selected");
    }

    @ParameterizedTest
    @MethodSource("provideListWithoutId")
    void validate_ValidResults_InvalidAnswerErrorIsNeverSet(List<Result> resultMocks) {
        when(questionModelMock.getResults()).thenReturn(resultMocks);
        for (Result resultMock : resultMocks) {
            when(questionModelValidator.getResultValidator().isValid(resultMock)).thenReturn(true);
        }

        questionModelValidator.validate(questionModelMock, errorsMock, questionMock);

        for (int i = 0; i < resultMocks.size(); i++) {
            verify(errorsMock, never())
                    .rejectValue("questionModels[" + questionMock.getId() + "].results[" + i + "]", "",
                            "Invalid answer");
        }
    }
}