package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.PageModelMockProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PageModelValidatorTest {

    private static final PageModelMockProvider MOCK_PROVIDER = new PageModelMockProvider();

    private PageModelValidator pageModelValidator;

    private static Object[] provideOneWithEmptyResults() {
        return MOCK_PROVIDER.provideOneWithEmptyResults();
    }

    private static Object[] provideOneWithCountOfQuestions() {
        return MOCK_PROVIDER.provideOneWithCountOfQuestions();
    }

    @BeforeEach
    void setUp() {
        pageModelValidator = new PageModelValidator();
        pageModelValidator.setQuestionModelValidator(mock(QuestionModelValidator.class));
        pageModelValidator.setQuestionRepository(mock(QuestionRepository.class));
    }

    @Test
    void setQuestionRepository_SomeQuestionRepository_SameValueIsReturnedByGetQuestionRepository() {
        QuestionRepository questionRepository = mock(QuestionRepository.class);

        pageModelValidator.setQuestionRepository(questionRepository);

        assertEquals(questionRepository, pageModelValidator.getQuestionRepository());
    }

    @Test
    void setQuestionModelValidator_SomeQuestionModelValidator_SameValueIReturnedByGetQuestionModelValidator() {
        QuestionModelValidator questionModelValidator = mock(QuestionModelValidator.class);

        pageModelValidator.setQuestionModelValidator(questionModelValidator);

        assertEquals(questionModelValidator, pageModelValidator.getQuestionModelValidator());
    }

    @ParameterizedTest
    @MethodSource("provideOneWithEmptyResults")
    void validate_PageModelWithEmptyResults_NotAnsweredErrorIsSetForAnyQuestion(PageModel pageModelMock,
                                                                                Page pageMock) {
        Errors errorsMock = mock(Errors.class);
        when(pageModelMock.getQuestionModels()).thenReturn(null);

        pageModelValidator.validate(pageModelMock, errorsMock, pageMock);

        for (Question questionMock : pageMock.getQuestions()) {
            verify(errorsMock, times(1))
                    .rejectValue("questionModels[" + questionMock.getId() + "]", "error.question_not_answered");
        }
    }

    @ParameterizedTest
    @MethodSource("provideOneWithCountOfQuestions")
    void validate_PageModelWithResults_AnyQuestionFromPageIsLoaded(PageModel pageModelMock,
                                                                   int countOfQuestions) {
        Errors errorsMock = mock(Errors.class);
        for (long i = 1; i <= countOfQuestions; i++) {
            Question questionMock = mock(Question.class);
            when(pageModelValidator.getQuestionRepository().findById(i)).thenReturn(Optional.of(questionMock));
        }
        pageModelValidator.validate(pageModelMock, errorsMock, mock(Page.class));

        for (long i = 1; i <= countOfQuestions; i++) {
            verify(pageModelValidator.getQuestionRepository()).findById(i);
        }
    }

    @ParameterizedTest
    @MethodSource("provideOneWithCountOfQuestions")
    void validate_PageModelWithResults_AnyQuestionFromPageIsValidated(PageModel pageModelMock,
                                                                      int countOfQuestions) {
        Errors errorsMock = mock(Errors.class);
        Map<Long, Question> questionMocks = new HashMap<>();
        for (long i = 1; i <= countOfQuestions; i++) {
            Question questionMock = mock(Question.class);
            questionMocks.put(i, questionMock);
            when(pageModelValidator.getQuestionRepository().findById(i)).thenReturn(Optional.of(questionMock));
        }

        pageModelValidator.validate(pageModelMock, errorsMock, mock(Page.class));

        for (long i = 1; i <= countOfQuestions; i++) {
            verify(pageModelValidator.getQuestionModelValidator())
                    .validate(pageModelMock.getQuestionModels().get(i), errorsMock, questionMocks.get(i));
        }
    }
}